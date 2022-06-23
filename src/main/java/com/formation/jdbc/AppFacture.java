package com.formation.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class AppFacture {
	public static void main(String[] args) {
		IFactureDao dao = new FactureDaoJDBC();
		IClientDao daoClient = new ClientDaoJDBC();

		Scanner input = new Scanner(System.in);

		boolean isRunning = true;

		while (isRunning) {
			afficherMenu();
			System.out.println("Entrez un choix : ");
			String line = input.next();

			if (line.toLowerCase().equals("exit")) {
				isRunning = false;
				break;
			}


			if (line.toLowerCase().equals("1")) {
				dao.afficherListeFactures();
			} else if (line.toLowerCase().equals("2")) {
				int idFacture = demanderId(input);
				dao.afficherFacture(idFacture);
			} else if (line.toLowerCase().equals("3")) {
				Facture f = demanderAjoutFacture(input);
				if (dao.ajouterFacture(f)) {
					System.out.println("Insertion facture OK");
				} else {
					System.out.println("Echec insertion facture");
				}
			}
			else if (line.toLowerCase().equals("4")) {
				int idFacture = demanderId(input);
				Facture f = demanderAjoutFacture(input);
				f.setId(idFacture);
				
				if (dao.modifierFacture(f)) {
					System.out.println("Modif facture OK");
				} else {
					System.out.println("Echec Modif facture");
				}
			} else if (line.toLowerCase().equals("5")){
				int idFacture = demanderId(input);
				if (dao.supprimerFacture(idFacture)) {
					System.out.println("Suppression facture OK");
				} else {
					System.out.println("Echec Suppression facture");
				}
			} else if(line.toLowerCase().equals("6")){
				int idClient = demanderId(input);
				daoClient.afficherSomme(idClient);
			} else if(line.toLowerCase().equals("7")){
				daoClient.afficherListeFactures();
			} else if(line.toLowerCase().equals("8")){
				int choix = demanderDao(input);
				if(choix == 1){
					dao = new FactureDaoMongoDB();
					System.out.println("Base Mongo choisie");
				}else {
					dao = new FactureDaoJDBC();
					System.out.println("Base PostgreSQL choisie");
				}
			}

		}

	}

	private static Facture demanderAjoutFacture(Scanner input) {
		Facture f = new Facture();
		System.out.println("Entrez une date (YYYY-MM-JJ): ");
		f.setDate(Date.valueOf(LocalDate.parse(input.next())));
		System.out.println("Entrez un id de client : ");
		f.setIdClient(input.nextInt());
		System.out.println("Entrez un montant : ");
		f.setMontant(input.nextDouble());
		return f;
	}

	private static int demanderId(Scanner input) {
		System.out.println("Entrez un id : ");
		return input.nextInt();
	}

	private static int demanderDao(Scanner input){
		System.out.println("Choisissez votre base entre Mongo (1) et PostgreSQL(2)");
		return input.nextInt();
	}

	private static void afficherMenu() {
		System.out.println("Choisir dans le menu : ");
		System.out.println("1. Afficher la liste des factures : ");
		System.out.println("2. Afficher une facture (id) : ");
		System.out.println("3. Ajouter une facture : ");
		System.out.println("4. Modifier une facture : ");
		System.out.println("5. Supprimer une facture : ");
		System.out.println("6. Afficher la somme des factures d'un client (id) : ");
		System.out.println("7. Afficher la somme des factures de tous les clients : ");
		System.out.println("8. Choisir une base de donnees :");
		System.out.println("EXIT");
	}

}
