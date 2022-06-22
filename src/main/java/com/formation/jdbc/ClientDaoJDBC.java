package com.formation.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientDaoJDBC implements IClientDao{

	private DbConnection db = new DbConnection();
	
	@Override
	public void afficherSomme(int id) {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT sum(f.montant) as somme, concat(c.nom,' ',c.prenom) as personne from facture f  inner join client c on f.id_client = c.id where c.id = ? group by c.nom, c.prenom");
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Somme des factures de " + rs.getString("personne")+ " : " + rs.getDouble("somme"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void afficherListeFactures() {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT sum(f.montant) as somme, concat(c.nom,' ',c.prenom) as personne from facture f  inner join client c on f.id_client = c.id group by c.nom, c.prenom");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Somme des factures de " + rs.getString("personne")+ " : " + rs.getDouble("somme"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
