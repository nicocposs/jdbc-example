package com.formation.jdbc;

import java.sql.Date;
import java.util.List;

public class Client {

	private int id;
	private String prenom;
	private String nom;
	private Date dateEntree;
	private List<Facture> factures;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}
	
	public boolean addFacture(Facture f) {
		return factures.add(f);
	}
	
	public List<Facture> getFactures() {
		return factures;
	}
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", dateEntree=" + dateEntree + "]";
	}
	
	
}
