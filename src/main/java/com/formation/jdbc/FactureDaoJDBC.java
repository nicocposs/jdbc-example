package com.formation.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FactureDaoJDBC implements IFactureDao {

	private DbConnection db = new DbConnection();
	
	@Override
	public boolean ajouterFacture(Facture f) {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO facture(id_client,date,montant)"
					+ "VALUES(?,?,?)");
			
			stmt.setInt(1, f.getIdClient());
			stmt.setDate(2, f.getDate());
			stmt.setDouble(3, f.getMontant());
			
			int nbAjouts = stmt.executeUpdate();
			
			return nbAjouts > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void afficherListeFactures() {
		try (Connection conn = db.getConnection()){
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM facture");
			
			while(rs.next()) {
				Facture f = new Facture();
				f.setId(rs.getInt("id"));
				f.setDate(rs.getDate("date"));
				f.setIdClient(rs.getInt("id_client"));
				f.setMontant(rs.getDouble("montant"));
				
				System.out.println(f);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean modifierFacture(Facture f) {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE facture SET id_client = ?, date=?, montant=? WHERE id=?");
			
			
			stmt.setInt(1, f.getIdClient());
			stmt.setDate(2, f.getDate());
			stmt.setDouble(3, f.getMontant());
			stmt.setInt(4,f.getId());
			
			int nbModifs = stmt.executeUpdate();
			
			return nbModifs > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerFacture(int id) {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(
					"DELETE FROM facture WHERE id=?");
			
			
			stmt.setInt(1, id);

			int nbSuppressions = stmt.executeUpdate();
			
			return nbSuppressions > 0;
		} catch(Exception e) {
			System.out.println("wtf");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void afficherFacture(int id) {
		try (Connection conn = db.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM facture WHERE id=?");
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Facture f = new Facture();
				f.setId(rs.getInt("id"));
				f.setDate(rs.getDate("date"));
				f.setIdClient(rs.getInt("id_client"));
				f.setMontant(rs.getDouble("montant"));
				
				System.out.println(f);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
