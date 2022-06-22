package com.formation.jdbc;

import java.sql.Date;

public class Facture {
	private int id;
	private int idClient;
	private Date date;
	private double montant;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Facture [id=" + id + ", idClient=" + idClient + ", date=" + date + ", montant=" + montant + "]";
	}
}
