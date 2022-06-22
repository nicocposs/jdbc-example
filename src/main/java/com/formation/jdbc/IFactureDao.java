package com.formation.jdbc;

public interface IFactureDao {
	boolean ajouterFacture(Facture f);
	
	void afficherListeFactures();
	
	boolean modifierFacture(Facture f);

	boolean supprimerFacture(int id);
	
	void afficherFacture(int id);
}
