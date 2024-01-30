package vue;

import java.util.InputMismatchException;
import java.util.Scanner;

import entites.Db;

public class Main {
	static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args) {
		Db.connect();


		// Menu
		int choix = -1;
		while (choix != 0) {
			choix = menu();
			if (choix == 1) {
				// listeDesProduits();
			} else if (choix == 2) {
				// ajouterUnProduit();
			} else if (choix == 3) {
				// modifierUnProduit();
			} else if (choix == 4) {
				// supprimerUnProduit();
			} else if (choix == 5) {
				// rechercherUnProduit();
			} else if (choix == 6) {
				// listeDesClients();
			} else if (choix == 7) {
				// ajouterUnClient();
			} else if (choix == 8) {
				// modifierUnClient();
			} else if (choix == 9) {
				// supprimerUnClient();
			} else if (choix == 10) {
				// rechercherUnClient();
			} else if (choix == 11) {
				// listeDesCatégories();
			} else if (choix == 12) {
				// ajouterUneCatégorie();
			} else if (choix == 13) {
				// modifierUneCatégorie();
			} else if (choix == 14) {
				// supprimerUneCatégorie();
			} else if (choix == 15) {
				// listeDesCommandes();
			} else if (choix == 16) {
				// passerUneCommande();
			} else if (choix == 17) {
				// supprimerUneCommande();
			} else if (choix == 18) {
				// listeDesFournisseurs();
			} else if (choix == 19) {
				// ajouterUnFournisseur();
			} else if (choix == 20) {
				// modifierUnFournisseur();
			} else if (choix == 21) {
				// supprimerUnFournisseur();
			} else if (choix == 22) {
				// rechercherUnFournisseur();
			} else if (choix == 23) {
				// listeDesEntréesEnStock();
			} else if (choix == 24) {
				// ajouterUneEntréeEnStock();
			} else if (choix == 25) {
				// supprimerUneEntréeEnStock();
			} else if (choix == 26) {
				// listeDesPaiements();
			} else if (choix == 27) {
				// effectuerUnPaiement();
			} else if (choix == 28) {
				// modifierUnPaiement();
			} else if (choix == 29) {
				// supprimerUnPaiement();
			}

		}


	}

		public static int menu() {
		try {
			// Type text-block => String multiligne
			System.out.println(
					"""
								#################################### MENU ################################################
								|| 1- ListeDesReservations    | 11- ListeDesChambres      | 21- AjouterUnHotel	 		||
								|| 2- EffectuerUneReservation | 12- AjouterUneChambre     | 22- ListerLesHotels   		||
								|| 3- ModifierUneReservation  | 13- ModifierUneChambre    | 23- ModifierUnHotel    		||
								|| 4- SupprimerUneReservation | 14- SupprimerUneChambre   | 24- SupprimerUnHotel   		||
								|| 5- RechercherUnReservation | 15- RechrcherUneChambre   | 25-RechercherUnHotel 		||
								|| 6- ListeDesClients         | 16- ListerLesPaiements    | 26- ListeLesSocietes        ||
								|| 7- AjouterUnClient 	      | 17- EffectuerUnPaiement   | 27- AjouterUneSociete       ||
								|| 8  ModifierUnClient        | 18- ModifierUnPaiement    | 28- ModifiUneSociete        ||
								|| 9- SupprimerUnClient       | 19- SupprimerUnPaiement   | 29- SupprimerUneSociete     ||
								|| 10-RechercherUnClient      | 20- RechercherUnPaiement  | 30- RechercherUneSociete    ||
																							0 -Quitter                  ||     
								#################################### CHOIX ###############################################
							""");
			int c = clavier.nextInt();
			return c;

		} catch (InputMismatchException inp) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			return 0;
		}
	}


}
