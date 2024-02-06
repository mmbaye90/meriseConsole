package vue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.ChambreDao;
import dao.ClientDao;
import dao.HotelDao;
import dao.PaiementDao;
import dao.ReservDao;
import dao.SocieteDao;
import entites.Chambre;
import entites.Client;
import entites.Db;
import entites.Hotel;
import entites.Paiement;
import entites.Reservation;
import entites.Societe;

public class Main {
	static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args) {
		Db.connect();


		// Menu
		int choix = -1;
		while (choix != 0) {
			choix = menu();
			if (choix == 1) {
				menuAjouter();
			} 
			else if (choix == 2) {
				menuMdifier();
			} 
			else if (choix == 3) {
				menuRecherche();
			} 
			else if (choix == 4) {
				menuSupprimer();
			} 
			else if (choix == 5) {
				menuLister();
			} 
			// else if (choix == 6) {
			// 	listeDesClients();
			// } else if (choix == 7) {
			// 	ajouterUnClient();
			// } 
			// else if (choix == 8) {
			// 	modifierUnClient();
			// } 
			// else if (choix == 9) {
			// 	supprimerUnClient();
			// } 
			// else if (choix == 10) {
			// 	rechercherUnClient();
			// } 
			// else if (choix == 11) {
			// 	listeDesChambres();
			// } 
			// else if (choix == 12) {
			// 	ajouterUneChambre();
			// } 
			// else if (choix == 13) {
			// 	rechercherUneChambre();
			// } 
			// else if (choix == 14) {
			// 	supprimerUneChambre();
			// } 
			// else if (choix == 15) {
			// 	modifierUneChambre();
			// } 
			// else if (choix == 16) {
			// 	listeLesPaiements();
			// } 
			// else if (choix == 17) {
			// 	effectuerUnPaiement();
			// } 
			// else if (choix == 18) {
			// 	modifierUnPaiement();
			// } 
			// else if (choix == 19) {
			// 	supprimerUnPaiement();
			// } 
			// else if (choix == 20) {
			// 	rechercherUnPaiement();
			// } else if (choix == 21) {
			// 	ajouterUnHotel();
			// } else if (choix == 22) {
			// 	listerLesHotels();
			// } 
			// else if (choix == 23) {
			// 	modifierUnHotel();
			// } 
			// else if (choix == 24) {
			// 	supprimerUnHotel();
			// } 
			// else if (choix == 25) {
			// 	rechercherUnHotel();
			// } 
			// else if (choix == 26) {
			// 	listeLesSocietes();
			// } 
			// else if (choix == 27) {
			// 	ajouterUneSociete();
			// } 
			// else if (choix == 28) {
			// 	modifiUneSociete();
			// } 
			// else if (choix == 29) {
			// 	supprimerUneSociete();
			// } 
			// else if (choix == 30) {
			// 	rechercherUneSociete();
			// }

		}


	}
//=======================================   MENU    ================================
//------------------------------------ MENU GENERAL --------------------------------    
	public static int menu() {
		try {
			// Type text-block => String multiligne
			System.out.println(
				""" 
======================================== MENU ==============================================
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||                                                                                   	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||   *** 1- AJOUTER       ***     *** 2-  MODIFIER     ***  |||||||||||||||||||||| 	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||----------------------------------------------------------||||||||||||||||||||||
||||||||||||   ########################     ########################  |||||||||||||||||||||| 	
||||||||||||   *** 3- RECHERCHER    ***     *** 4- SUPPRIMER     ***  ||||||||||||||||||||||	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||----------------------------------------------------------||||||||||||||||||||||
||||||||||||   ########################     ########################  |||||||||||||||||||||| 	
||||||||||||   *** 5-  LISTER       ***     *** 0- QUITTER       ***  ||||||||||||||||||||||	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
======================================== CHOIX ============================================= 

				""");
			int c = clavier.nextInt();
			return c;

		} catch (InputMismatchException inp) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			return 0;
		}
	}

//-------------------------------------- MENU AJOUT ----------------------------------
	public static void menuAjouter(){
		System.out.println(
			""" 
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   ***1-AJOUTER SOCIETE ***     *** 2- AJOUTER HOTEL ***   	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   	
   *** 3-AJOUTER CHAMBRE***     *** 4-AJOUTER CLIENT *** 	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++ 

   ++++++++++++++++++++++++++++++   +++++++++++++++++++++++++++++	
   *** 5- AJOUTER RESERVATION *** 	*** 6- EFFECTUER PAIEMENT *** 
   ++++++++++++++++++++++++++++++   +++++++++++++++++++++++++++++
   ++++++++++++++++++++++++     	
   ***   7-   RETOUR    ***      
   ++++++++++++++++++++++++
			""");
	clavier.nextLine();
	int resp = clavier.nextInt();
	if (resp==1) {ajouterUneSociete();}
	else if(resp==2){ajouterUnHotel();}
	else if(resp==3){ajouterUneChambre();}
	else if(resp==4){ajouterUnClient();}
	else if(resp==5){effectuerUneReservation();}
	else if(resp==6){effectuerUnPaiement();}
	else if(resp==7){menu();}

}

//-------------------------------------- MENU MODIFIER ----------------------------------
	public static void menuMdifier(){
		System.out.println(
			""" 
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   ***1-MODIFIER SOCIETE***     *** 2- MODIFIER HOTEL***   	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   	
   ***3-MODIFIER CHAMBRE***     ***4-MODIFIER CLIENT *** 	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++ 

   ++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++	
   *** 5-MODIFIER RESERVATION *** 	*** 6- MODIFIER PAIEMENT *** 
   ++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++
   ++++++++++++++++++++++++     	
   ***   7-   RETOUR    ***      
   ++++++++++++++++++++++++
			""");
	clavier.nextLine();
	int resp = clavier.nextInt();
	if (resp==1) {modifiUneSociete();}
	else if(resp==2){modifierUnHotel();}
	else if(resp==3){modifierUneChambre();}
	else if(resp==4){modifierUnClient();}
	else if(resp==5){modifierUneReservation();}
	else if(resp==6){modifierUnPaiement();}
	else if(resp==7){menu();}

	}
//-------------------------------------- MENU RECHERCHER ----------------------------------
	public static void menuRecherche(){
		System.out.println(
			""" 
			============ RECHERCHER ============
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   *** 1- UNE SOCIETE   ***     ***  2 - UN HOTEL    ***   	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   
   
   ++++++++++++++++++++++++     ++++++++++++++++++++++++   	
   *** 3- UNE CHAMBRE   ***     *** 4-  UN CLIENT    *** 	
   ++++++++++++++++++++++++     ++++++++++++++++++++++++ 

   ++++++++++++++++++++++++     ++++++++++++++++++++++++	
   ***5-UNE RESERVATION ***     *** 6- UN PAIEMENT   *** 
   ++++++++++++++++++++++++     ++++++++++++++++++++++++
   ++++++++++++++++++++++++     	
   ***   7-   RETOUR    ***      
   ++++++++++++++++++++++++     
			""");
	clavier.nextLine();
	int resp = clavier.nextInt();

	if (resp ==1) {rechercherUneSociete();} 
	else if(resp ==2){rechercherUnHotel();}
	else if(resp ==3){rechercherUneChambre();}
	else if(resp ==4){rechercherUnClient();}
	else if(resp ==5){rechercherUnReservation();}
	else if(resp ==6){rechercherUnPaiement();}
	else if(resp ==7){menu();}
}

//-------------------------------------- MENU SUPPRIMER ----------------------------------
	public static void menuSupprimer(){
	System.out.println(
		""" 
++++++++++++++++++++++++++     +++++++++++++++++++++++++   
***1-SUPPRIMER SOCIETE ***     *** 2- SUPPRIMER HOTEL***   	
++++++++++++++++++++++++++     +++++++++++++++++++++++++   

++++++++++++++++++++++++++     ++++++++++++++++++++++++++   	
***3-SUPPRIMER CHAMBRE ***     *** 4-SUPPRIMER CLIENT *** 	
++++++++++++++++++++++++++     ++++++++++++++++++++++++++ 

+++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++	
*** 5-SUPPRIMER RESERVATION ***   *** 6- SUPPRIMER PAIEMENT *** 
+++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++
++++++++++++++++++++++++     	
***   7-   RETOUR    ***      
++++++++++++++++++++++++
		""");
clavier.nextLine();
int resp = clavier.nextInt();
if (resp==1) {supprimerUneSociete();}
else if(resp==2){supprimerUnHotel();}
else if(resp==3){supprimerUneChambre();}
else if(resp==4){supprimerUnClient();}
else if(resp==5){supprimerUneReservation();}
else if(resp==6){supprimerUnPaiement();}
else if(resp==7){menu();}

}


//-------------------------------------- MENU LISTE ----------------------------------
	public static void menuLister(){
		//flash
		clavier.nextLine();
		System.out.println(
			""" 
	++++++++++++++++++++++++++     +++++++++++++++++++++++++   
	*** 1-  LISTER SOCIETE ***     ***  2- LISTER HOTEL  ***   	
	++++++++++++++++++++++++++     +++++++++++++++++++++++++   

	++++++++++++++++++++++++++     ++++++++++++++++++++++++++   	
	*** 3- LISTER CHAMBRE  ***     *** 4- LISTER CLIENT   *** 	
	++++++++++++++++++++++++++     ++++++++++++++++++++++++++ 

	+++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++	
	*** 5-  LISTER RESERVATION  ***   *** 6- LISTER PAIEMENT   *** 
	+++++++++++++++++++++++++++++++   ++++++++++++++++++++++++++++
	++++++++++++++++++++++++     	
	***   7-   RETOUR    ***      
	++++++++++++++++++++++++
			""");
	clavier.nextLine();
	int resp = clavier.nextInt();
	if (resp==1) {listeLesSocietes();}
	else if(resp==2){listerLesHotels();}
	else if(resp==3){listeDesChambres();}
	else if(resp==4){listeDesClients();}
	else if(resp==5){listeDesReservations();}
	else if(resp==6){listeLesPaiements();}
	else if(resp==7){menu();}

	}


// ====================================== METHODESS D'AFFICHAGE ======================	
	
//-------------------- LISTE DES RESERVATIONS ----------------------
	public static void listeDesReservations(){
		System.out.println(
		"""
			============ L I S T E D E S R E S E R V A T I O N S ==============
		""");
		//remplacement des clefs étrangeres par un de ses propriétés(id-ch par n°ch id_client par son prénom)
		System.out.print(" idR\tdateDeb\t\tdateF  nbP   n°ch    Pcl");
		System.out.println();
		System.out.println("-------------------------------------------------");

		//utilisation de la méthode foreach(boucle) sur l'arraylist retourné par la méthode getAll 
		//de l'objet ReservDao,instancié directement avec le mot clef NEW pour afficher la liste de réservation
		new ReservDao().getAll().forEach((res)->{ 
			System.out.println("| "+res.getId_reserv()+ " | " +res.getDateDebRes()
			+" | "+
			res.getDateFinR()+ " | "+res.getNbPerso()+" | "
			+			
			//instanciation directe de l'objet Chambre pour faire appel à la méthode getByid
			//en lui passant la clef etrangere enfin d'acceder aux propriétés de
			new ChambreDao().getById(res.getId_ch()).getNumCh()
			+
			//utilisation de la methode substring(extraire 0/3 caract) et de concat(...) pour  un souci d'affichage
			" | " +new ClientDao().getById(res.getId_client()).getPrenomC().substring(0,3).concat("...")+" |");
			System.out.println("-------------------------------------------------");
		}
		);

	}

	//-------------------- LISTE DES CLIENTS ----------------------
	public static void listeDesClients(){
		System.out.println(
			"""
				============ L I S T E  D E S  C L I E N T S ==============
			""");
	
		//utilisation de la méthode foreach(boucle) sur l'arraylist retourné par la méthode getAll 
		//de l'objet ClientDao,instancié directement avec le mot clef NEW pour afficher la liste des clients
		System.out.println("| id |prenomC|adC    | ageC | ville | mailC    | sexeC | telC           |pays | nomC   |");
		System.out.println("----------------------------------------------------------------------------------------");
		new ClientDao().getAll().forEach((c)->{
			System.out.println("| "+c.getId_client()+"  |"+c.getPrenomC().substring(0,3).concat("...")+" |" + c.getAdC().substring(0,3).concat("...")+" | " +c.getAgeC()+"   | " +c.getVilleC().substring(0,4)+"  |" +c.getMailC().substring(0,9)+" |   " +c.getSexeC()+"   | " +c.getTelC()+" | " +c.getPaysC().substring(0,2)+"  | " +c.getNomC().substring(0,4).concat("...")+"|");
			System.out.println("----------------------------------------------------------------------------------------");
		});
	}

	//-------------------- LISTE DES CHAMBRE ----------------------
	public static void listeDesChambres(){
		System.out.println(
			"""
				============ L I S T E  D E S  C H A M B R E S ==============
			""");

		//Stats sur les Chambres
		float tauxOccup = new ChambreDao().getTotalChbre() * new ChambreDao().getChbrOccup();
		float adr = new ChambreDao().getPrixTotChOccup()/new ChambreDao().getChbrOccup();

		//Affichage des satats
		System.out.println("      STATS ");
		System.out.println("-------------------");
		System.out.println("| Taux occup | ADR |");
		System.out.println("--------------------");
		System.out.println("|  "+(tauxOccup/100 )+"      |"+adr + " |");
		System.out.println("--------------------");

		//utilisation de la méthode foreach(boucle) sur l'arraylist retourné par la méthode getAll 
		//de l'objet ChambreDao,instancié directement avec le mot clef NEW pour afficher la liste des Chambres
		System.out.println("| id | n°ch | nbLS | nbLD | sup | Sb | tv | bal | ref | bai | iso | prix | nomHot  |");		
		System.out.println("------------------------------------------------------------------------------------");		
		new ChambreDao().getAll().forEach((c)->{
			System.out.println("| "+c.getId_ch()+"  |  "+c.getNumCh()+" |  " + c.getNbLitChSimp()+"  | " +c.getNbLitChDoub()+"   | " +c.getSupCh()+"  | " +c.getSalleBainP()+" |" +c.getTvCh()+" | " +c.getBalconCh()+" | " +c.getRefrigerCh()+" | " +c.getBaignCh()+" | "+c.getInsonoCh()+" |"+c.getPrixNtCh()+"  |"+
			//appel de la methode getById()de la classe Hotel en lui passant en argument la propriété
			//id_Hotel qui me retourne un objet,me permettant d'acceder à ses propriétés
			new HotelDao().getById(c.getId_hotel()).getNomH()
			);
			System.out.println("------------------------------------------------------------------------------------");		

		});
	}


	//-------------------- LISTE DES PAIEMENTS ----------------------
	public static void listeLesPaiements(){
		System.out.println(
			"""
				============ L I S T E  D E S  P A I E M E N T S ==============
			""");

		System.out.print("| id | dateDeb Res  | dateFin Res  | methP  | Mont Pai | Date Paiemt |");
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		new PaiementDao().getAll().forEach((p)->{ 
			System.out.println("|  "+p.getId_p()+ " | " +

			//ajout des propriétés de l'objet ReservDao dans l'objet Paiement pour affichage
			new ReservDao().getById(p.getId_p()).getDateDebRes()
			+"   | "+new ReservDao().getById(p.getId_p()).getDateFinR()
			+ "   | "+p.getMethP().substring(0,2).concat("...")+"  |"+p.getMontP()+"  | " +p.getDateP()+" |");
			System.out.println("--------------------------------------------------------------------");
		}
		);
	}
	

	//-------------------- LISTE DES HOTELS ----------------------
	public static void listerLesHotels(){
		System.out.println(
			"""
				============ L I S T E  D E S  H O T E L S ==============
			""");
		System.out.println("| id | nom | adresse | ville | description | park | wifi | HrArr | HrDep | pisc | navt |animal| catH  | nomS  |");		
		System.out.println("---------------------------------------------------------------------------------------------------------------");						
		new HotelDao().getAll().forEach((h)->{
			//Utilisation de switch case pour gérer l'affichage des étoiles selon la valeur de la catégorie
			switch (h.getCatH()) {
				case 1:
					System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"*".toUpperCase()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom().substring(0,3).concat("...")+" |  "
					);
					System.out.println("---------------------------------------------------------------------------------------------------------------");						
	
					break;
				case 2:
					System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"**".toUpperCase()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom().substring(0,3).concat("...")+" |  "
					);
					System.out.println("---------------------------------------------------------------------------------------------------------------");						
					break;
				case 3:
					System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"***".toUpperCase()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom().substring(0,3).concat("...")+" |  "
					);
					System.out.println("---------------------------------------------------------------------------------------------------------------");						
					break;
				case 4:
					System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"****".toUpperCase()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom().substring(0,3).concat("...")+" |  "
					);
					System.out.println("---------------------------------------------------------------------------------------------------------------");						
					break;
				case 5:
					System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"*****".toUpperCase()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom().substring(0,3).concat("...")+" |  "
					);
					System.out.println("---------------------------------------------------------------------------------------------------------------");						
					break;

				default:
					System.out.println("| "+h.getId_hotel()+"  |  "+h.getNomH()+" |  " + h.getAdresseH()+"  | " +h.getVilleH()+"   | " +h.getDescriptionH()+"  | " +h.getParkingH()+" |" +h.getWifiH()+" | " +h.getHeureAaH()+" | " +h.getHeureDepH()+" | " +h.getPiscineH()+" | "+h.getNavetteH()+" |"+h.getPresenceAniH()+"  |"+h.getCatH()+" |"
					+new SocieteDao().getById(h.getId_soc()).getNom()+" |  "
					);				
					break;
			}
		});
	}
	

	//-------------------- LISTE DES SOCIETES ----------------------
	public static void listeLesSocietes(){
		System.out.println(
			"""
				============ L I S T E  D E S  S O C I E T E S ==============
			""");
		//Données de stats
		System.out.println("| id_soc | numSiret          | nom          | adresseS      |");
		System.out.println("-------------------------------------------------------------");
		new SocieteDao().getAll().forEach((s)->{
			System.out.println("| "+s.getId_soc()+ "      |"+ s.getNumSiret()+ "  |" +s.getNom()+ "   |" +s.getAdresseS().substring(0,5).concat("...")
			+ "|");
			System.out.println("---------------------------------------------------------");

		});
	}

// ====================================== METHODESS D'AJOUT ===================a	

 public static void ajouterUnClient() {
	System.out.println(
		"""
			============ A J O U T  D E S  C L I E N T S ==============
		""");
		//je déclare un boolean que j'initialise à false dans le try pour éxécuter ce dernier
		//au moins une fois.Il me permet aussi de reexecuter ce bloc si l'utilisateur entre un type
		//de donné inattendu et de ne pas crasher l'app
		boolean err;
		do {
			try {
				err = false;

				//j'instancie un objet Client que je stocke dans une variable de type Client
				Client clt = new Client();
				System.out.println("Saisir nom du client à ajouter");
				// flash
				clavier.nextLine();

				//je modifie la propriété Nom de l'objet Client avec la saisie de l'utilisateur
				clt.setNomC(clavier.nextLine());

				//idem
				System.out.println("Ville du client à ajouter");
				clt.setVilleC(clavier.nextLine());
				//idem
				System.out.println("Age du client à ajouter");
				clt.setAgeC(clavier.nextInt());


				// flash
				clavier.nextLine();

				//idem
				System.out.println("Prenom du client à ajouter");
				clt.setPrenomC(clavier.nextLine());

				//idem
				System.out.println("adresse du client");
				clt.setAdC(clavier.nextLine());

				//je déclare une variable de type Str 
				String iPut;

				//Je demande au mois une fois à l'utilisateur son mail
				do {
					System.out.println("mail du client");
					//j'initialise la variable pareil dans les autres boucles whiles
					iPut = clavier.nextLine();
				} while (!isValidMail(iPut));//je redemande à chaque fois que l'uti entre un format de mail inattedu

				//je modifie le mail avec le bon format de mail saisi
				clt.setMailC(iPut);

				//je déclare une variable de type Str nommée sexe
				String sexe;

				//j'utilise la méthode equalsIgnoreCase() pour comparer la saisie de l'utilisateur 
				//à un format donné; Je redemande à l'uti tant qu'il ne saisie pas le bon format
				do {
					System.out.println("Sexe du client F(femme)/H(homme)");
					sexe =clavier.nextLine();		
				} while (!sexe.equalsIgnoreCase("F") && !sexe.equalsIgnoreCase("M") );
				
				//je sette la propriété Sexe
				clt.setSexeC(sexe);

				//je déclare une variable de type Str nommée tel
				String tel;

				//j'utilise la méthode isValidTel() pour comparer la saisie de l'utilisateur 
				//à un format donné; Je redemande à l'uti tant qu'il ne saisie pas le bon format
				do {
					System.out.println("tél du client");
					tel= clavier.nextLine();
				} while (!isValidTel(tel));

				//je sette le tél du client
				clt.setTelC(tel);

				System.out.println("pays du client");
				//je sette le pays du client
				clt.setPaysC(clavier.nextLine());
				// System.out.println(clt);

				// insertion
				new ClientDao().save(clt);

			} catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			}

		} while (err);
	}

public static void ajouterUneChambre(){
	System.out.println(
		"""
			============ A J O U T  D E S  C H A M B R E S ==============
		""");

	//idem pour la méthode ajouterUnClien() sauf l'objet qui change
	boolean err;
	do {
		try {
			err = false;
			Chambre ch = new Chambre();

			// flash
			clavier.nextLine();

			new ChambreDao().getAll().forEach((c)->{
				System.out.println("{n°ch : " +c.getNumCh()+" }");
			});

			//déclaration variable
			int numCh;

			//j'utilise la boucle do while pour redemander à l'utili de resaisir tant que la
			//le num de la chambre n'est pas différent de celui affiché ci dessus et que la
			//saisie ne soit une valeur négative
			do {
				System.out.println("Saisir numéro chambre à ajouter, différent de ceux proposés ci desuus");
				numCh = clavier.nextInt();
			} while (numCh <0 && new ChambreDao().getById(numCh).getNumCh()==numCh);
			
			ch.setNumCh(numCh);

			//déclaration var
			int litS;
			//redemande à l'uti tant que la val saisie est négative
			do {
				System.out.println("Saisir nombre de lits Simples à ajouter");
				litS = clavier.nextInt();
			} while (litS <0);
			
			//sette lit Simp
			ch.setNbLitChSimp(litS);

			//idem pour lit Simp
			int litD;
			do {
				System.out.println("Saisir nombre de lits doubles à ajouter");
				litD = clavier.nextInt();
			} while (litD <0);
			
			ch.setNbLitChDoub(litD);

			// flash
			clavier.nextLine();

			int sup;
			do {
				System.out.println("Saisir la superficie à ajouter");
				sup = clavier.nextInt();
			} while (sup <0);
			
			ch.setSupCh(sup);

			//flash
			clavier.nextLine();

			//déclaration d'une var de type Str
			String slB;

			//redemande à l'uti de resaisir tant que le format saisi n'est pas conforme à celui
			//attendu avec la méthoode equalsIgnoreCase()
			do {
				System.out.println("disponibilité de salle de bain(oui/non) ?");
				slB = clavier.nextLine();
			} while (!slB.equalsIgnoreCase("OUI") && !slB.equalsIgnoreCase("NON"));

			//sette
			ch.setSalleBainP(slB);

			String tv;
			do {
				System.out.println("disponibilité de TV (OUI/NON) ?");
				tv =clavier.nextLine();		
			} while (!tv.equalsIgnoreCase("oui") && !tv.equalsIgnoreCase("non") );
			ch.setTvCh(tv);

			String bal;
			do {
				System.out.println("disponibilité de balcon (OUI/NON) ?");
				bal =clavier.nextLine();		
			} while (!bal.equalsIgnoreCase("oui") && !bal.equalsIgnoreCase("non") );
			
			ch.setBalconCh(bal);

			String ref;
			do {
				System.out.println("disponibilité de réfrigérateur (OUI/NON) ?");
				ref =clavier.nextLine();		
			} while (!ref.equalsIgnoreCase("oui") && !ref.equalsIgnoreCase("non") );
			
			ch.setRefrigerCh(ref);

			String baign;
			do {
				System.out.println("disponibilité de baignoire (OUI/NON) ?");
				baign =clavier.nextLine();		
			} while (!baign.equalsIgnoreCase("oui") && !baign.equalsIgnoreCase("non") );
			
			ch.setBaignCh(baign);

			String son;
			do {
				System.out.println("disponibilité de insonoriasation (OUI/NON) ?");
				son =clavier.nextLine();		
			} while (!son.equalsIgnoreCase("oui") && !son.equalsIgnoreCase("non") );
			
			ch.setInsonoCh(son);

			int prixN;
			do {
				System.out.println("prix par nuit à ajouter");
				prixN =clavier.nextInt();		
			} while (prixN <0);
			
			ch.setPrixNtCh(prixN);

			//Affichage du nom de l'hotel et de son id et demande à l'utili de saisir
			//un hotel existant.
			new HotelDao().getAll().forEach((h)->{
				System.out.println("{ id : "+h.getId_hotel()+" ,nomHotel : " +h.getNomH()+" }");
			});

			//dec var de type int
			int idH;

			
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idH = clavier.nextInt();
			} while (new HotelDao().getById(idH)==null);//redemandé à l'uti tant que l'id ne correspond pas

			//sette
			ch.setId_hotel(idH);
			// System.out.println(ch);

			// insertion
			new ChambreDao().save(ch);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void effectuerUnPaiement(){
	System.out.println(
		"""
			============ A J O U T  D E S  P A I E M E N T S ==============
		""");

	//idem pour la méthode ajouterUnClien() sauf l'objet qui change
	boolean err;
	do {
		try {
				err = false;

				Paiement p = new Paiement();

				//Affichage des réservations
				listeDesReservations();
				
				//décl var de type int
				int idR;
				do {
					System.out.println("choisir la réservation à régler en tapant l'ID");
					idR = clavier.nextInt();
				} while (new ReservDao().getById(idR) ==null);//redemande à l'util tant qu'il ne saisie pas le bon ID

				//sette
				p.setId_reserv(idR);

				//je récupére la date de début réservation en cours dans une var date1 de type Str
				String date1 = new ReservDao().getById(idR).getDateDebRes();
				
				//je récupére la date de fin réservation en cours dans une var date2 de type Str
				String date2 = new ReservDao().getById(idR).getDateFinR();

				//je récupére l'ID de la chambre correspondante à la reservation choisie par l'utilisateur
				//avec la méthode getIdChambreByReser() de l'objet Reservation que je stocke dans une var de type int
				int idch = new ReservDao().getIdChambreByReser(idR);

				//je récupére le prix de la chambre correspondante à la réservation choisie que je stocke dans une var de type float
				float priUCh = new ChambreDao().getById(idch).getPrixNtCh();

				//je calcule le nombre de jour passé à l'hotel avec la méthode nbJourRes() et le stocke 
				//dans une var de type float
				float nbTnuit = nbJourRes(date1, date2);
				
				//calcule montant total à payer que je stocke dans une var de type float
				float mtTotal = nbTnuit*priUCh;

				//affichage du montant total à l'utilisateur
				System.out.println("+--------------------------------------------------+");
				System.out.println("  |      RESTE A PAYER    : " + mtTotal+ "E"+"       |");
				System.out.println("+--------------------------------------------------+");

				//flash
				clavier.nextLine();

				//dec var
				String moyP;

				//redemande à l'uti tant qu'il ne saisie pas le bon format(cb/esp/ch)
				//avec la méthode equalsIgnoreCase()
				do {
					System.out.println("comment reglez vous (cb/esp/ch) ?");
					moyP =clavier.nextLine();		
				} while (!moyP.equalsIgnoreCase("cb") && !moyP.equalsIgnoreCase("esp") && !moyP.equalsIgnoreCase("ch"));
				
				//sette methode de paiement
				p.setMethP(moyP);

				//dec var de type int
				int versmt;
				do {
					System.out.println("Votre premier versement ?");
					versmt = clavier.nextInt();
				} while (versmt <0);//redemande si la saisie est négative

				System.out.println("+--------------------------------------------------+");
				System.out.println("  |      RESTE A PAYER    : " + (mtTotal-versmt)+ "E"+"       |");
				System.out.println("+--------------------------------------------------+");

				// System.out.println("Reste à payer :"+ (mtTotal-versmt) +" E");
				
				//sette du montant avec la modification
				p.setMontP(versmt);

				clavier.nextLine();

				//décl var 
				String datePaiement;

				//redemande à l'utili tant que le format de la date n'est pas valide
				//avec la méthode isValidDate()
				do {
					System.out.println("Saisir une date de paiement au format (YYYY-MM-DD)");
					datePaiement = clavier.nextLine();
				} while (!isValidDate(datePaiement));

				//sette date
				p.setDateP(datePaiement);
			
				System.out.println(p);
				//insertion
				new PaiementDao().save(p);
			} 
			catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

} while (err);

}

public static void ajouterUnHotel(){
	System.out.println(
		"""
			============ A J O U T  D E S  H O T E L S ==============
		""");

	//idem pour ajouterhotel()
	boolean err;
	do {
		try {
			err = false;
			Hotel htl = new Hotel();
			// flash
			clavier.nextLine();

			System.out.println("Saisir nom Hotel à ajouter");
			String nomH = clavier.nextLine();
			
			htl.setNomH(nomH);

			System.out.println("Saisir adresse Hotel à ajouter");
			String adH = clavier.nextLine();
			
			htl.setAdresseH(adH);
			System.out.println("Saisir ville Hotel à ajouter");
			String villeH = clavier.nextLine();
			
			htl.setVilleH(villeH);

			System.out.println("Saisir la description(MAX 500 caract) à ajouter");
			String des = clavier.nextLine();

			//Verifie la long de la chaine saisie à 500 caracteres
			//une petit warning à 499 caractere
			if (des.length()==490) {
				System.out.println("il vous reste 10 carateres au maximum");
			} else {
				htl.setDescriptionH(des);
			}

			String pkg;
			do {
				System.out.println("disponibilité de parking(oui/non) ?");
				pkg = clavier.nextLine();
			} while (!pkg.equalsIgnoreCase("OUI") && !pkg.equalsIgnoreCase("NON"));

			htl.setParkingH(pkg);

			String wifi;
			do {
				System.out.println("disponibilité de wifi (OUI/NON) ?");
				wifi =clavier.nextLine();		
			} while (!wifi.equalsIgnoreCase("oui") && !wifi.equalsIgnoreCase("non") );
			
			htl.setWifiH(wifi);

//========================== Vérification si hr arr < ou > à hr dép avnt insertion ds la  BD ===================
			//decl var des var int
			int hArr;
			int hDep;
			do {
				do {
					System.out.println("saisir heure arrivéé à l'hotel");
					hArr =clavier.nextInt();			
				} while (hArr <0);//redemande tant que la val sasie est négative
	
				//decl var de type int
				do {
					System.out.println("saisir heure départ de l'hotel");
					hDep =clavier.nextInt();			
			   } while (hDep <0);//redemande tant que la val sasie est négative
	
			} while (hArr>hDep);//redemande à l'uti tant que Heure arr est sup à hr départ			

			//je parse les variables de type int en String et le concaténe avec une ch
			//avant de l'inserer dans la bd
			htl.setHeureAaH(String.valueOf(hArr).concat(" H"));
			htl.setHeureDepH(String.valueOf(hDep).concat(" H"));

			//dec var
			String pisc;
			//redemande tant que la saisie ne correspond pas à la réponse attendue
			do {
				System.out.println("disponibilité de piscine (OUI/NON) ?");
				pisc =clavier.nextLine();		
			} while (!pisc.equalsIgnoreCase("oui") && !pisc.equalsIgnoreCase("non") );
			//sette 
			htl.setPiscineH(pisc);

			//dec var
			String nav;

			//redemande tant que la saisie ne correspond pas à la réponse attendue
			do {
				System.out.println("disponibilité de navette (OUI/NON) ?");
				nav =clavier.nextLine();		
			} while (!nav.equalsIgnoreCase("oui") && !nav.equalsIgnoreCase("non") );
			
			htl.setNavetteH(nav);

			String pAni;
			do {
				System.out.println("Acceptaion animal (OUI/NON) ?");
				pAni =clavier.nextLine();		
			} while (!pAni.equalsIgnoreCase("admis") && !pAni.equalsIgnoreCase("non admis") );
			
			htl.setPresenceAniH(pAni);

			int catH;
			do {
				System.out.println("Catégorie de l'hotel");
				catH =clavier.nextInt();		
			} while (catH <0 && catH >6);
			
			htl.setCatH(catH);

			//affichage des ID de la société ainsi que leur noms enfin de ne pas ajouter un hotel sans société
			new SocieteDao().getAll().forEach((s)->{
				System.out.println("{ id : "+s.getId_soc()+" ,nomSociete : " +s.getNom()+" }");
			});


			int idS;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idS = clavier.nextInt();
			} while (new HotelDao().getById(idS)==null);//redemande tant que l'ID de la société n'a pas été selectionné

			//sette
			htl.setId_soc(idS);

			// insertion
			new HotelDao().save(htl);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void ajouterUneSociete(){
	System.out.println(
		"""
			============ A J O U T  D E S  S O C I E T E S ==============
		""");

	//pas de controle de saisie
	clavier.nextLine();
	String ste; 
	do {
		System.out.println("numero de Siret au format (602 036 444 94227) à mod ");
		ste =clavier.nextLine();
		
	} while (ste.length()!=17);

	System.out.println("Donnez un nom de societe");
	String nmSoc = clavier.nextLine();
	System.out.println("Donnez une adresse  de societe");
	String adS = clavier.nextLine();

	//instanciation puis sette les val avec un constructeur avec parametre de l'objet Societe
	Societe s = new Societe(ste,nmSoc,adS);
	
	//insertion
	new SocieteDao().save(s);
}

public static void effectuerUneReservation(){
	System.out.println(
		"""
			============ A J O U T  D E S  R E S E R V A T I O N S ==============
		""");

	boolean err;
	do {
		try {
			err = false;

	//affichage des réservation à l'util
	System.out.println(
			"""
				======================================================
				****************** RESERVATIONS EN COURS *************
				======================================================
			""");
	listeDesReservations();

	//instaciatio de l'objet Reservation
	Reservation res = new Reservation();

	clavier.nextLine();
	String dateDeb;
	do {
		System.out.println("Saisir une date de début de reservation au format (YYYY-MM-DD)");
		dateDeb = clavier.nextLine();
	} while (!isValidDate(dateDeb));//redemande tant que le format ne correspond pas

	String dateFin;
	do {
		System.out.println("Saisir une date de fin de reservation au format (YYYY-MM-DD)");
		dateFin = clavier.nextLine();
	} while (!isValidDate(dateFin));

//========================== Comparaison des dates avant insertion ======================
	//instanciation de SimpleDateFormat en lui passant en argument le format voulu
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

	//je passe la méthode setLenient() de SimpleDateFormat à false 
	sdf.setLenient(false);

	//bloc try catch pour lever l'exception renvoyée par SimpleDateFormat 
		try {
			//je parse les saisies de l'uti avec la méthode parse()
			Date date1 = sdf.parse(dateDeb);
			Date date2 = sdf.parse(dateFin);

			//je vérifie avec la méthode before()=> bool de l'objet String avant de setter 
			//les propriétés de l'objet Reservation
			if (date1.before(date2)) {
				res.setDateDebRes(dateDeb);
				res.setDateFinR(dateFin);
			}else{
			System.out.println("Date dé&but de réservation doit etre inferieure \nà la date de fin de reservation");
			effectuerUneReservation();
		}
	} catch (ParseException e) {
		System.out.println("erreur parse Comparaison");
	}


	int nbPer;
	//on accepte pas plus de 5 personnes par chambre
	do {
		System.out.println("indiquer le nombre de personnes pour la réservation");
		nbPer = clavier.nextInt();
	} while (nbPer <0 && nbPer >5);//redemander tant que la saisie est négative ou >5

	//sette
	res.setNbPerso(nbPer);

	//Affichage de la liste des chambre à l'uti
	listeDesChambres();
	int idCh;
	do {
		System.out.println("Veuillez choisir la chambre à réserver en tapant l'ID");
		idCh = clavier.nextInt();
	} while (new ChambreDao().getById(idCh)==null);//redemde tt que l'id dmdé n'est pas choisi

	//sette
	res.setId_ch(idCh);

	//affichage des clients à l'uti
	listeDesClients();
	int idClt;
	do {
		System.out.println("Veuillez choisir le client effectuant la réservation en tapant l'ID");
		idClt = clavier.nextInt();
	} while (new ClientDao().getById(idClt)==null);//redemde tt que l'id dmdé n'est pas choisi

	//sette
	res.setId_client(idCh);

	//Insertion
	new ReservDao().save(res);
} catch (InputMismatchException e) {
	err = true;
	System.err.println("/!\\ Saisir le bon type de donnée demandé !");
}

} while (err);


}



// ===================================== SUP FONCTIONS ====================
public static void supprimerUnClient() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  C L I E N T S ==============
		""");

	//je declare un boolean 
	boolean err;

	do {
		//je l'initialise à false pour executer le bloc try au moins une fois et/ou si la saie de l'uti ne corr
		err = false;
		try {

			//afficha à l'uti liste des clients à supp
			listeDesClients();

			int idC;
			do {
				System.out.println("Saisir l'ID du client à sup");
				idC = clavier.nextInt();
			} while (new ClientDao().getById(idC) == null);//red tt que id dmdé n'est pas fourni

			//je récupére l'objet en question avec l'id fourni par l'uti
			Client c = new ClientDao().getById(idC);

			//petite info à l'uti avant supp
			System.out.println("ATTENTION les réservations du client seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				//si id trouvé je supprime
				new ClientDao().delete(c.getId_client());
			}
			else if (resp == 0) {
				//si annulation je retourne au menu
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}

public static void supprimerUneChambre() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  C H A M B R E S ==============
		""");

	//idem pour la meth supprimerUnClient() pour les comm
	boolean err;

	do {
		err = false;
		try {

			listeDesChambres();

			int idC;
			do {
				System.out.println("Saisir l'ID du chambre à sup");
				idC = clavier.nextInt();
			} while (new ChambreDao().getById(idC) == null);

			Chambre chbre = new ChambreDao().getById(idC);

			System.out.println("ATTENTION les réservations liées à la chambre seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				new ChambreDao().delete(chbre.getId_ch());
			}
			else if (resp == 0) {
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}

public static void supprimerUneReservation() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  R E S E R V A T I O N S ==============
		""");

	//idem pour la meth supprimerUnClient() pour les comm
	boolean err;

	do {
		err = false;
		try {

			listeDesReservations();

			int idR;
			do {
				System.out.println("Saisir l'ID de la reservation à sup");
				idR = clavier.nextInt();
			} while (new ReservDao().getById(idR) == null);

			Reservation res = new ReservDao().getById(idR);

			System.out.println("/!\\ les paiements liés à la réservation seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				new ReservDao().delete(res.getId_reserv());
			}
			else if (resp == 0) {
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}

public static void supprimerUnHotel() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  H O T E L S ==============
		""");


	//idem pour la meth supprimerUnClient() pour les comm
	boolean err;

	do {
		err = false;
		try {

			listerLesHotels();

			int idH;
			do {
				System.out.println("Saisir l'ID de l'hotel à sup");
				idH = clavier.nextInt();
			} while (new HotelDao().getById(idH) == null);

			Hotel hot = new HotelDao().getById(idH);

			System.out.println("/!\\ les chambres liés à l'hotel' seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				new HotelDao().delete(hot.getId_hotel());
			}
			else if (resp == 0) {
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}

public static void supprimerUneSociete() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  S O C I E T E S ==============
		""");

	//idem pour la meth supprimerUnClient() pour les comm

	boolean err;

	do {
		err = false;
		try {

			listeLesSocietes();

			int idS;
			do {
				System.out.println("Saisir l'ID de la société à sup");
				idS = clavier.nextInt();
			} while (new SocieteDao().getById(idS) == null);

			Societe soc = new SocieteDao().getById(idS);

			System.out.println("/!\\ les hotels liés à la societé seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				new SocieteDao().delete(soc.getId_soc());
			}
			else if (resp == 0) {
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}

public static void supprimerUnPaiement() {
	System.out.println(
		"""
			============ S U P P R E S  D E S  P A I E M E N T S ==============
		""");

	//idem pour la meth supprimerUnClient() pour les comm
	
	boolean err;

	do {
		err = false;
		try {

			listeLesPaiements();

			int idP;
			do {
				System.out.println("Saisir l'ID du paiement à sup");
				idP = clavier.nextInt();
			} while (new PaiementDao().getById(idP) == null);

			Paiement pymt = new PaiementDao().getById(idP);


			int resp = clavier.nextInt();
			if (resp == 1){
				new PaiementDao().delete(idP);;
				System.out.println(pymt.getId_p());
			}
			else if (resp == 0) {
				System.out.println("NON SUPP");
				menu();
			}

		} catch (InputMismatchException e) {
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);

}


// ===================================== MODIF FONCTIONS ====================
public static void modifierUnClient() {
	System.out.println(
		"""
			============ M O D I F  D E S  C L I E N T S ==============
		""");

	//déclaratio d'un boolean 
	boolean err;
	do {
		err = false;
		try {

			// Affichage à l'utilisateur ID Client à modifier
			listeDesClients();

			int idCl;
			do {
				System.out.println("Saisir l'ID du client à modifier");
				idCl = clavier.nextInt();
			} while (new ClientDao().getById(idCl) == null);//redemande tt que l'id ne correspond pas

			//instanciation de l'objet enfin de setter ses propriétés
			Client clt = new ClientDao().getById(idCl);

			//j'affiche à chaque fois la valeur à modifier avant modif
			System.out.println("Saisir Nom à modifier : " +clt.getNomC());

			clavier.nextLine();
			clt.setNomC(clavier.nextLine());

			System.out.println("Saisir Ville à modifier : " +clt.getVilleC());
			clt.setVilleC(clavier.nextLine());

			System.out.println("Saisir Age à modifier : " +clt.getAgeC());
			clt.setAgeC(clavier.nextInt());

			System.out.println("Saisir Prenom à modifier : " +clt.getPrenomC());
			clavier.nextLine();
			clt.setPrenomC(clavier.nextLine());

			String tel;
			do {
				System.out.println("Saisir tél à modifier : " +clt.getTelC());
				tel = clavier.nextLine();
			} while (!isValidTel(tel));

			clt.setTelC(tel);

			String mail;
			do {
				System.out.println("Saisir mail à modifier : " + clt.getMailC());
				mail = clavier.nextLine();
			} while (!isValidMail(mail));

			clt.setMailC(mail);

			//Modification
			new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);
}

public static void modifierUnHotel(){
	System.out.println(
		"""
			============ M O D I F  D E S  H O T E L S ==============
		""");

	//idem pour la méthode modifierUnClient()
	boolean err;
	do {
		try {
			err = false;

			listerLesHotels();

			int idHtl;
			do {
				System.out.println("Saisir l'ID de l'hotel à modifier");
				idHtl = clavier.nextInt();
			} while (new HotelDao().getById(idHtl) == null);

			Hotel htl = new HotelDao().getById(idHtl);


			// flash
			clavier.nextLine();

			System.out.println("Saisir nom Hotel à mod " + htl.getNomH());
			String nomH = clavier.nextLine();
			
			htl.setNomH(nomH);

			System.out.println("Saisir adresse Hotel à mod : " +htl.getAdresseH());
			String adH = clavier.nextLine();
			
			htl.setAdresseH(adH);
			System.out.println("Saisir ville Hotel à modifier : " +htl.getVilleH());
			String villeH = clavier.nextLine();
			
			htl.setVilleH(villeH);


			System.out.println("Saisir la description(MAX 500 caract) à mod " +htl.getDescriptionH());
			String des = clavier.nextLine();

			if (des.length()==490) {
				System.out.println("il vous reste 10 carateres au maximum");
			} else {
				htl.setDescriptionH(des);
			}


			String pkg;
			do {
				System.out.println("disponibilité de parking à modifier" +htl.getParkingH());
				pkg = clavier.nextLine();
			} while (!pkg.equalsIgnoreCase("OUI") && !pkg.equalsIgnoreCase("NON"));

			htl.setParkingH(pkg);

			String wifi;
			do {
				System.out.println("disponibilité de wifi à modifier "+ htl.getWifiH());
				wifi =clavier.nextLine();		
			} while (!wifi.equalsIgnoreCase("oui") && !wifi.equalsIgnoreCase("non") );
			
			htl.setWifiH(wifi);

//========================== Vérification si hr arr < ou > à hr dép avnt insertion ds la  BD ===================
			//decl var des var int
			int hArr;
			int hDep;
			do {
				do {
					System.out.println("saisir heure arrivéé à l'hotel");
					hArr =clavier.nextInt();			
				} while (hArr <0);//redemande tant que la val sasie est négative
	
				//decl var de type int
				do {
					System.out.println("saisir heure départ de l'hotel");
					hDep =clavier.nextInt();			
			   } while (hDep <0);//redemande tant que la val sasie est négative
	
			} while (hArr>hDep);//redemande à l'uti tant que Heure arr est sup à hr départ			

			//je parse les variables de type int en String et le concaténe avec une ch
			//avant de l'inserer dans la bd
			htl.setHeureAaH(String.valueOf(hArr).concat(" H"));
			htl.setHeureDepH(String.valueOf(hDep).concat(" H"));

			//modif piscine
			String pisc;
			do {
				System.out.println("disponibilité de piscine à mod " +htl.getPiscineH());
				pisc =clavier.nextLine();		
			} while (!pisc.equalsIgnoreCase("oui") && !pisc.equalsIgnoreCase("non") );
			
			htl.setPiscineH(pisc);

			String nav;
			do {
				System.out.println("disponibilité de navette à mod "+htl.getNavetteH());
				nav =clavier.nextLine();		
			} while (!nav.equalsIgnoreCase("oui") && !nav.equalsIgnoreCase("non") );
			
			htl.setNavetteH(nav);

			String pAni;
			do {
				System.out.println("Acceptaion animal à mod ?"+ htl.getPresenceAniH());
				pAni =clavier.nextLine();		
			} while (!pAni.equalsIgnoreCase("admis") && !pAni.equalsIgnoreCase("non admis") );
			
			htl.setPresenceAniH(pAni);

			int catH;
			do {
				System.out.println("Catégorie de l'hotel à mod " +htl.getCatH());
				catH =clavier.nextInt();		
			} while (catH <0 && catH >6);
			
			htl.setCatH(catH);

			//affichage des sociétés
			listeLesSocietes();

			int idS;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idS = clavier.nextInt();
			} while (new HotelDao().getById(idS)==null);//rdme tt que l'id ne correspond pas

			htl.setId_soc(idS);

			// Modification
			new HotelDao().save(htl);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void modifierUneChambre(){
	System.out.println(
		"""
			============ M O D I F  D E S  C H A M B R E S ==============
		""");

	//idem pour Modifier Un hotel
	boolean err;
	do {
		try {
			err = false;

			listeDesChambres();
			int idC;
			do {
				System.out.println("Saisir l'ID de la chambre à modifier");
				idC = clavier.nextInt();
			} while (new ChambreDao().getById(idC)==null);

			Chambre ch = new ChambreDao().getById(idC);
			// flash
			clavier.nextLine();
			int numCh;
			do {
				System.out.println("Saisir numéro chambre à mod " + ch.getNumCh());
				numCh = clavier.nextInt();
			} while (numCh <0);


			ch.setNumCh(numCh);

			int litS;
			do {
				System.out.println("Saisir nombre de lits Simples à mod " + ch.getNbLitChSimp());
				litS = clavier.nextInt();
			} while (litS <0);
			
			ch.setNbLitChSimp(litS);

			int litD;
			do {
				System.out.println("Saisir nombre de lits doubles à mod " +ch.getNbLitChDoub());
				litD = clavier.nextInt();
			} while (litD <0);
			
			ch.setNbLitChDoub(litD);

			// flash
			clavier.nextLine();

			int sup;
			do {
				System.out.println("Saisir la superficie à modifier "+ch.getSupCh());
				sup = clavier.nextInt();
			} while (sup <0);
			
			ch.setSupCh(sup);

			clavier.nextLine();
			String slB;
			do {
				System.out.println("disponibilité de salle da mod " +ch.getSalleBainP());
				slB = clavier.nextLine();
			} while (!slB.equalsIgnoreCase("OUI") && !slB.equalsIgnoreCase("NON"));

			ch.setSalleBainP(slB);

			String tv;
			do {
				System.out.println("disponibilité de TV à mod " +ch.getTvCh());
				tv =clavier.nextLine();		
			} while (!tv.equalsIgnoreCase("oui") && !tv.equalsIgnoreCase("non") );
			ch.setTvCh(tv);

			String bal;
			do {
				System.out.println("disponibilité de balcon (à mod " +ch.getBalconCh());
				bal =clavier.nextLine();		
			} while (!bal.equalsIgnoreCase("oui") && !bal.equalsIgnoreCase("non") );
			
			ch.setBalconCh(bal);

			String ref;
			do {
				System.out.println("disponibilité de réfrigérateur à mod "+ch.getRefrigerCh());
				ref =clavier.nextLine();		
			} while (!ref.equalsIgnoreCase("oui") && !ref.equalsIgnoreCase("non") );
			
			ch.setRefrigerCh(ref);

			String baign;
			do {
				System.out.println("disponibilité de baignoire à mod " +ch.getBaignCh());
				baign =clavier.nextLine();		
			} while (!baign.equalsIgnoreCase("oui") && !baign.equalsIgnoreCase("non") );
			
			ch.setBaignCh(baign);

			String son;
			do {
				System.out.println("disponibilité d'insonoriasation à mod "+ch.getInsonoCh());
				son =clavier.nextLine();		
			} while (!son.equalsIgnoreCase("oui") && !son.equalsIgnoreCase("non") );
			
			ch.setInsonoCh(son);

			int prixN;
			do {
				System.out.println("prix par nuit à mod" +ch.getPrixNtCh());
				prixN =clavier.nextInt();		
			} while (prixN <0);
			
			ch.setPrixNtCh(prixN);

			//affichage des hotels
			listerLesHotels();

			int idH;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idH = clavier.nextInt();
			} while (new HotelDao().getById(idH)==null);

			ch.setId_hotel(idH);
			System.out.println(ch);

			new ChambreDao().save(ch);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void modifierUnPaiement(){
	System.out.println(
		"""
			============ M O D I F  D E S  P A I E M E N T S ==============
		""");

	//idem pour Modifier un hotel
	boolean err;
	do {
		try {
				err = false;

				listeLesPaiements();

				int idP;
				do {
					System.out.println("Saisir l'ID de du paiement à modifier");
					idP = clavier.nextInt();
				} while (new PaiementDao().getById(idP)==null);

				Paiement p = new PaiementDao().getById(idP);

				listeDesReservations();
				
				int idR;
				do {
					System.out.println("choisir la réservation à modifier en tapant l'ID");
					idR = clavier.nextInt();
				} while (new ReservDao().getById(idR) ==null);

				p.setId_reserv(idR);

				clavier.nextLine();
				String moyP;
				do {
					System.out.println("modification mode de paiement " +p.getMethP());
					moyP =clavier.nextLine();		
				} while (!moyP.equalsIgnoreCase("cb") && !moyP.equalsIgnoreCase("esp") && !moyP.equalsIgnoreCase("ch"));
				
				p.setMethP(moyP);


				int versmt;
				do {
					System.out.println("modification de verssement " +p.getMethP());
					versmt = clavier.nextInt();
				} while (versmt <0);

				p.setMontP(versmt);

				//flash
				clavier.nextLine();

				String datePaiement;
				do {
					System.out.println("modification date du paiement au format (YYYY-MM-DD) "+p.getDateP());
					datePaiement = clavier.nextLine();
				} while (!isValidDate(datePaiement));

				p.setDateP(datePaiement);
			
				//Modification
				new PaiementDao().save(p);
			} 
			catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

} while (err);

}

public static void modifiUneSociete(){
	System.out.println(
		"""
			============ M O D I F  D E S S O C I E T E S ==============
		""");

	//idem pour les autres modif

	//affichage des sociétés
	System.out.println(
		"""
			============+++ LISTE DES SOCIETES A MODIFIER +++=========
		""");

	listeLesSocietes();

	int idS;
	do {
		System.out.println("Saisir l'ID de la Société à modifier");
		idS = clavier.nextInt();
	} while (new SocieteDao().getById(idS)==null);

	Societe socte = new SocieteDao().getById(idS);

	clavier.nextLine();
	String ste; 
	do {
		System.out.println("numero de Siret(meme format) à mod " +socte.getNumSiret());
		ste =clavier.nextLine();
		
	} while (ste.length()!=17);

	socte.setNumSiret(ste);


	System.out.println(" nom de societe à mod " +socte.getNom());
	socte.setNom(clavier.nextLine());

	System.out.println("adresse  de societe à mod " +socte.getAdresseS());
	socte.setAdresseS(clavier.nextLine());

	new SocieteDao().save(socte);
}

public static void modifierUneReservation(){
	System.out.println(
		"""
			============ M O D I F  D E S R E S E R V A T I O N S ==============
		""");

	//idem pour les meth Modifs
	boolean err;
	do {
		try {
			err = false;

	System.out.println(
"""
======================================================
****************** RESERVATIONS EN COURS *************
======================================================
""");

	listeDesReservations();

	int idRes;
	do {
		System.out.println("Saisir l'ID de la réservation à modifier");
		idRes = clavier.nextInt();
	} while (new ReservDao().getById(idRes) == null);


	Reservation res = new ReservDao().getById(idRes);

	clavier.nextLine();
	String dateDeb;
	do {
		System.out.println("Saisir une date de début de reservation à mod au format (YYYY-MM-DD) "+res.getDateDebRes());
		dateDeb = clavier.nextLine();
	} while (!isValidDate(dateDeb));

	String dateFin;
	do {
		System.out.println("Saisir une date de fin de reservation à mod format (YYYY-MM-DD) "+res.getDateFinR());
		dateFin = clavier.nextLine();
	} while (!isValidDate(dateFin));


	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	sdf.setLenient(false);
		try {
		Date date1 = sdf.parse(dateDeb);
		Date date2 = sdf.parse(dateFin);
		if (date1.before(date2)) {
			res.setDateDebRes(dateDeb);
			res.setDateFinR(dateFin);
		}else{
			System.out.println("Date dé&but de réservation doit etre inferieure \nà la date de fin de reservation");
			effectuerUneReservation();
		}
	} catch (ParseException e) {
		System.out.println("erreur parse Comparaison");
	}


	int nbPer;
	do {
		System.out.println("indiquer le nombre de personnes pour la réservation à mod " +res.getNbPerso());
		nbPer = clavier.nextInt();
	} while (nbPer <0 && nbPer >6);

	res.setNbPerso(nbPer);

	listeDesChambres();
	int idCh;
	do {
		System.out.println("Veuillez choisir la chambre à réserver en tapant l'ID à mod "+res.getId_ch());
		idCh = clavier.nextInt();
	} while (new ChambreDao().getById(idCh)==null);

	res.setId_ch(idCh);

	listeDesClients();
	int idClt;
	do {
		System.out.println("Veuillez choisir le client effectuant la réservation en tapant l'ID à mod "+res.getId_client());
		idClt = clavier.nextInt();
	} while (new ClientDao().getById(idClt)==null);

	res.setId_client(idCh);

	new ReservDao().save(res);

} catch (InputMismatchException e) {
	err = true;
	System.err.println("/!\\ Saisir le bon type de donnée demandé !");
}

} while (err);


}


// ===================================== RECHERCHEs FONCTIONS ====================
public static void rechercherUnClient(){
	System.out.println(
		"""
			============ R E C H E R C H E R  D E S  C L I E N T S ==============
		""");

	// flash
	clavier.nextLine();

	//demande un caract ou un mot à rechercher
	System.out.println("Saisir le prénom du Client à cherecher");
	String mot = clavier.nextLine();

	//aff c
	System.out.println(
	"""
	##################################
	*** C L I E N T  T R O U V E S *** 	
	##################################
	"""
			
	);
	//Afficher la liste trouvée
	System.out.println("----------------------------------------------------------------------------------------");
	new ClientDao().recherche(mot).forEach((c) -> {
		System.out.println("| "+c.getId_client()+"  |"+c.getPrenomC().substring(0,3).concat("...")+" |" + c.getAdC().substring(0,3).concat("...")+" | " +c.getAgeC()+"   | " +c.getVilleC().substring(0,4)+"  |" +c.getMailC().substring(0,9)+" |   " +c.getSexeC()+"   | " +c.getTelC()+" | " +c.getPaysC().substring(0,2)+"  | " +c.getNomC().substring(0,4).concat("...")+"|");
		System.out.println("----------------------------------------------------------------------------------------");
	});

}

public static void rechercherUneSociete(){
	System.out.println(
		"""
			============ R E C H E R C H E R  D E S  S O C I E T E S ==============
		""");

	// flash
	clavier.nextLine();

	//demande un caract ou un mot à rechercher
	System.out.println("Saisir le nom de la société à cherecher");
	String mot = clavier.nextLine();

	//aff c
	System.out.println(
	"""
	######################################
	*** S O C I E T E  T R O U V E E S *** 	
	######################################
	"""
			
	);
	//Afficher la liste trouvée
	System.out.println("------------------------------------------------------------------------------------------------");
	new SocieteDao().recherche(mot).forEach((s)->{
		System.out.println("| "+s.getId_soc()+ "      |"+ s.getNumSiret()+ "  |" +s.getNom()+ "   |" +s.getAdresseS()+ "|");
		System.out.println("------------------------------------------------------------------------------------------------");
	});

}

public static void rechercherUnReservation(){
	System.out.println(
	"""
	============ R E C H E R C H E R  D E S  R E S E R V A T I O N S ==============
	""");

	// flash
	clavier.nextLine();
	//demande à l'uti un mot à rechercher
	String mot;
	do {
		System.out.println("Saisir la date de début ou date de fin de réservation à rechercher");
		 mot = clavier.nextLine();
		} while (!isValidDate(mot));//redemande tant que le format ne correspond pas


	//j'instancie mesChambreDao/ClientDao/HotelDao pour pouvoir pointer sur leur méthodes getById()
	//simplification d'écriture
	ChambreDao objCh = new ChambreDao();
	ClientDao objCl = new ClientDao();
	HotelDao objHot = new HotelDao();

	//j'affiche un message dans le cs où on a aucun résultat avec la méthode isEmpty
	if (new ReservDao().recherche(mot).isEmpty()) {
		System.out.println("Auncun résultat".toUpperCase());
	}else{
		System.out.println(
		"""
		################################################
		*** R E S E R V A T I O N S  T R O U V E E S *** 	
		################################################
		""");

		//affichage
		System.out.println("---------------------------------------------------------------");
		System.out.print("| id | dateDebRes | dateFinR   | nbP | n°ch | nomHot | prenCl |");
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		
		//appel de la méthode recherche 
		new ReservDao().recherche(mot).forEach((res)->{

			//Je récupérer les ID de l'objet ReservDao() en appelant les méthodes associées
			//(req sql) et je les stockent  dans des var de type int.ce qui me permet d'avoir
			//aussi accés à leur propriétés.
			int id_ch = new ReservDao().getIdChambreByReser(res.getId_reserv());
			int id_client = new ReservDao().getIdChambreByReser(res.getId_reserv());

			//Je récupérer les ID de l'objet ChambreDao().Ici je n'ai pas eu besoin de
			//passer par une req car sur deux tables dans la BD
			int id_hotel = objCh.getById(id_ch).getId_ch();

			System.out.println("|  "+res.getId_reserv()+ " | " +res.getDateDebRes()
			+" | "+
			res.getDateFinR()+ " | "+res.getNbPerso()+"   | "
			+
			//je remplace id_chambre par son numéro de chambre			
			objCh.getById(id_ch).getNumCh()
			+
			"  | " 
			//je remplace l'id_hotel de l'objet Chambre par le nom de l'hotel et j'extraie 
			//trois caract que je concaténe avec des ...
			+objHot.getById(id_hotel).getNomH().substring(0,3).concat("...")+" |"


			//je remplace l'id_hotel de l'objet Chambre par le nom de l'hotel et j'extraie 
			//trois caract que je concaténe avec des ...
			+objCl.getById(id_client).getPrenomC().substring(0,3).concat("...")+"  |"

			);
			System.out.println("--------------------------------------------------------------|");


			
		});
	
	}
}

public static void rechercherUnPaiement(){
	System.out.println(
	"""
	============ R E C H E R C H E R  D E S  P A I E M E N T S ==============
	""");

	// flash
	clavier.nextLine();

	String mot;
	do {
		System.out.println("Saisir la date de paiement à rechercher");
		 mot = clavier.nextLine();
		} while (!isValidDate(mot));//redemande tant que le format ne correspond pas

		ChambreDao objCh = new ChambreDao();
		ClientDao objCl = new ClientDao();	

	//j'affiche un message dans le cs où on a aucun résultat avec la méthode isEmpty
	if (new PaiementDao().recherche(mot).isEmpty()) {
		System.out.println("Auncun résultat".toUpperCase());
	}else{
		System.out.println(
		"""
		################################################
		***    P A I E M E N T S  T R O U V E S      *** 	
		################################################
		""");

		//affichage  
		System.out.print("| id | dateDeb Res  | dateFin Res  | methP  | Mont Pai | Date Paiemt | numCh | PrnCl |");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------");
		
		new PaiementDao().recherche(mot).forEach((p)->{ 
			
			//je récupere l'id de la clef etrangere dans la table Paiement avec la meth getIdResByPaiement
			int id_reserv = new PaiementDao().getIdResByPaiement(p.getId_p());
			
			//je récupere l'id de la clef etrangere dans la table Reservation avec la meth getIdChambreByReser
			int id_ch = new ReservDao().getIdChambreByReser(id_reserv);

			//je récupere l'id de la clef etrangere dans la table Reservation avec la meth getIdChambreByReser
			int id_client = new ReservDao().getIdChambreByReser(id_reserv);

			//affichage de l'objet trouvé
			System.out.println("|  "+p.getId_p()+ " | " +

			//je remplace l'id_reserv par par deux de ses propriétés getDateDebRes/getDateFinR
			new ReservDao().getById(p.getId_p()).getDateDebRes()
			+"   | "+new ReservDao().getById(p.getId_p()).getDateFinR()
			+ "   | "+p.getMethP().substring(0,2).concat("...")+"  |"+p.getMontP()+"    | " +p.getDateP()
			//je remplace l'id_ch de la table reserv par le numéro de la chbre corr
			+"  | "+objCh.getById(id_ch).getNumCh()+"  |"
			+objCl.getById(id_client).getPrenomC()+"  |"
			);
			System.out.println("--------------------------------------------------------------------------------------");
		}
		);

	
	}
}

public static void rechercherUneChambre(){
	System.out.println(
	"""
	============ R E C H E R C H E R  D E S  C H A M B R E S ==============
	""");

	// flash
	clavier.nextLine();

	int mot;
	do {
		System.out.println("Saisir le numéro de la chambre à rechercher");
		mot = clavier.nextInt();
	} while (mot<0);

	//instanciation de l'hotel pour acceder à ses propriétés avec la méthode getById
	HotelDao objHtl = new HotelDao();

	System.out.println("| id | n°ch | nbLS | nbLD | sup | Sb | tv | bal | ref | bai | iso | prix | nomHot  |");		
	System.out.println("------------------------------------------------------------------------------------");		
	
	//je convertie la var saisie par l'uti en String avant d'effectuer la recherche
	new ChambreDao().recherche(	String.valueOf(mot)).forEach((c)->{

		//Récupération de l'id_hotel
		int id_hotel = new ChambreDao().getIdHotelByChambre(c.getId_ch());

		System.out.println("| "+c.getId_ch()+"  |  "+c.getNumCh()+" |  " + c.getNbLitChSimp()+"  | " +c.getNbLitChDoub()+"   | " +c.getSupCh()+"  | " +c.getSalleBainP()+" |" +c.getTvCh()+" | " +c.getBalconCh()+" | " +c.getRefrigerCh()+" | " +c.getBaignCh()+" | "+c.getInsonoCh()+" |"+c.getPrixNtCh()+"  |"
		+objHtl.getById(id_hotel).getNomH()
		);
		System.out.println("------------------------------------------------------------------------------------");		

	});

}

public static void rechercherUnHotel(){
	System.out.println(
		"""
		============ R E C H E R C H E R  D E S  H O T E L S ==============
		""");
	
		// flash
		clavier.nextLine();
		System.out.println("Entrez le nom ou ville de l'hotel à rechercher");
		String mot = clavier.nextLine();
	
	System.out.println("| id | nom | adresse | ville | description | park | wifi | HrArr | HrDep | pisc | navt |animal| catH  | nomS  |");		
	System.out.println("---------------------------------------------------------------------------------------------------------------");						
	new HotelDao().recherche(mot).forEach((h)->{
		int id_soc = new HotelDao().getIdSocieteByHotel(h.getId_hotel());

		//Utilisation de switch case pour gérer l'affichage des étoiles selon la valeur de la catégorie
		switch (h.getCatH()) {
			case 1:
				System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"*".toUpperCase()+" |"
				+new SocieteDao().getById(id_soc).getNom().substring(0,3).concat("...")+" |  "
				);
				System.out.println("---------------------------------------------------------------------------------------------------------------");						

				break;
			case 2:
				System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"**".toUpperCase()+" |"
				+new SocieteDao().getById(id_soc).getNom().substring(0,3).concat("...")+" |  "
				);
				System.out.println("---------------------------------------------------------------------------------------------------------------");						
				break;
			case 3:
				System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"***".toUpperCase()+" |"
				+new SocieteDao().getById(id_soc).getNom().substring(0,3).concat("...")+" |  "
				);
				System.out.println("---------------------------------------------------------------------------------------------------------------");						
				break;
			case 4:
				System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"****".toUpperCase()+" |"
				+new SocieteDao().getById(id_soc).getNom().substring(0,3).concat("...")+" |  "
				);
				System.out.println("---------------------------------------------------------------------------------------------------------------");						
				break;
			case 5:
				System.out.println("| "+h.getId_hotel()+"  |"+h.getNomH().substring(0,2).concat("...")+"|" + h.getAdresseH().substring(0,5).concat("...")+" |" +h.getVilleH().substring(0,4).concat("...")+"|" +h.getDescriptionH().substring(0,9).concat("...")+" | " +h.getParkingH()+"  |  " +h.getWifiH()+" | " +h.getHeureAaH()+"   | " +h.getHeureDepH()+"    | " +h.getPiscineH()+"  | "+h.getNavetteH()+"  | "+h.getPresenceAniH().substring(0,3)+"  |"+"*****".toUpperCase()+" |"
				+new SocieteDao().getById(id_soc).getNom().substring(0,3).concat("...")+" |  "
				);
				System.out.println("---------------------------------------------------------------------------------------------------------------");						
				break;

			default:
				System.out.println("| "+h.getId_hotel()+"  |  "+h.getNomH()+" |  " + h.getAdresseH()+"  | " +h.getVilleH()+"   | " +h.getDescriptionH()+"  | " +h.getParkingH()+" |" +h.getWifiH()+" | " +h.getHeureAaH()+" | " +h.getHeureDepH()+" | " +h.getPiscineH()+" | "+h.getNavetteH()+" |"+h.getPresenceAniH()+"  |"+h.getCatH()+" |"
				+new SocieteDao().getById(id_soc).getNom()+" |  "
				);				
				break;
		}
	});

}

// ===================================== UTILS FONCTIONS ====================
public static boolean isValidMail(String mail) {
	Pattern mailValid = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	Matcher matcher = mailValid.matcher(mail);
	return matcher.matches();
}

public static boolean isValidTel(String tel) {
	Pattern mailValid = Pattern.compile("^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$");
	Matcher matcher = mailValid.matcher(tel);
	return matcher.matches();
}

public static boolean isValidDate(String d) {
		
	SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	df.setLenient(false);
	Date dte =null;
	try {
		dte = df.parse(d);
		System.out.println(dte);
		return true;
	} catch (ParseException pE) {
		System.err.println("Erreur Format");
		return false;
	}	
}


public static float nbJourRes(String d1,String d2){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	sdf.setLenient(false);
	try {
		Date dateAvant = sdf.parse(d1);
		Date dateApres = sdf.parse(d2);

		long diff = dateApres.getTime() - dateAvant.getTime();
		float res = (diff / (1000*60*60*24));
		System.out.println("Nombre de jours entre les deux dates est: "+res);
		return res;
	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}
}


}
