package com.formation.jdbc;

import java.sql.Date;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class FactureDaoMongoDB implements IFactureDao{

    
    
    @Override
    public boolean ajouterFacture(Facture f) {
        try(DbConnectionMongo conn = new DbConnectionMongo()){
            Document facture = new Document("_id", new ObjectId());

            facture.append("id", f.getId())
                    .append("id_client", f.getIdClient())
                    .append("montant", f.getMontant())
                    .append("date_facture", f.getDate());

            conn.getCollection().insertOne(facture);
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void afficherListeFactures() {
        
        try(DbConnectionMongo conn = new DbConnectionMongo()){

            FindIterable<Document> factures = conn.getCollection().find();

            for(Document d : factures){
                Facture f = new Facture();
                f.setId(d.getInteger("id"));
                f.setDate(new Date(d.getDate("date_facture").getTime()));
                f.setMontant(d.getDouble("montant"));
                f.setIdClient(d.getInteger("id_client"));

                System.out.println(f);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean modifierFacture(Facture f) {
        try(DbConnectionMongo conn = new DbConnectionMongo()){
            Bson filter = Filters.eq("id", f.getId());
            Bson update1 = Updates.set("id_client",f.getIdClient());
            Bson update2 = Updates.set("date_facture",f.getDate());
            Bson update3 = Updates.set("montant",f.getMontant());
            Bson updates = Updates.combine(update1,update2,update3);

            conn.getCollection().findOneAndUpdate(filter, updates);

            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean supprimerFacture(int id) {
        try(DbConnectionMongo conn = new DbConnectionMongo()){

            Bson filter = Filters.eq("id", id);

            conn.getCollection().findOneAndDelete(filter);
            
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void afficherFacture(int id) {
        try(DbConnectionMongo conn = new DbConnectionMongo()){

            Bson filter = Filters.eq("id", id);
            FindIterable<Document> factures = conn.getCollection().find(filter);

            for(Document d : factures){
                Facture f = new Facture();
                f.setId(d.getInteger("id"));
                f.setDate(new Date(d.getDate("date_facture").getTime()));
                f.setMontant(d.getDouble("montant"));
                f.setIdClient(d.getInteger("id_client"));

                System.out.println(f);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
