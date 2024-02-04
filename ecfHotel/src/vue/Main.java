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
				listeDesReservations();
			} else if (choix == 2) {
				effectuerUneReservation();
			} else if (choix == 3) {
				modifierUneReservation();
			} else if (choix == 4) {
				supprimerUneReservation();
			} else if (choix == 5) {
				rechercherUnReservation();
			} else if (choix == 6) {
				listeDesClients();
			} else if (choix == 7) {
				ajouterUnClient();
			} else if (choix == 8) {
				modifierUnClient();
			} else if (choix == 9) {
				supprimerUnClient();
			} else if (choix == 10) {
				rechercherUnClient();
			} else if (choix == 11) {
				listeDesChambres();
			} else if (choix == 12) {
				ajouterUneChambre();
			} else if (choix == 13) {
				// modifierUneCatégorie();
			} else if (choix == 14) {
				supprimerUneChambre();
			} else if (choix == 15) {
				modifierUneChambre();
			} else if (choix == 16) {
				listeLesPaiements();
			} else if (choix == 17) {
				effectuerUnPaiement();
			} else if (choix == 18) {
				modifierUnPaiement();
			} else if (choix == 19) {
				supprimerUnPaiement();
			} else if (choix == 20) {
				// modifierUnFournisseur();
			} else if (choix == 21) {
				ajouterUnHotel();
			} else if (choix == 22) {
				listerLesHotels();
			} else if (choix == 23) {
				modifierUnHotel();
			} else if (choix == 24) {
				supprimerUnHotel();
			} else if (choix == 25) {
				// supprimerUneEntréeEnStock();
			} else if (choix == 26) {
				listeLesSocietes();
			} else if (choix == 27) {
				ajouterUneSociete();
			} else if (choix == 28) {
				modifiUneSociete();
			} else if (choix == 29) {
				supprimerUneSociete();
			}

		}


	}

	public static int menu() {
		try {
			// Type text-block => String multiligne
			System.out.println(
				""" 
======================================== MENU ==============================================
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||                                                                                   	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||   *** 1- GESTION HOTEL ***     ***    2- CAISSE     ***  |||||||||||||||||||||| 	
||||||||||||   ########################     ########################  ||||||||||||||||||||||
||||||||||||----------------------------------------------------------||||||||||||||||||||||
||||||||||||   ########################     ########################  |||||||||||||||||||||| 	
||||||||||||   *** 3- CONTACT       ***     ***    4- QUITTER    ***  ||||||||||||||||||||||	
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

// ====================================== METHODESS D'AFFICHAGE ======================	
	//-------------------- LISTE DES RESERVATIONS ----------------------
	public static void listeDesReservations(){
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
		//utilisation de la méthode foreach(boucle) sur l'arraylist retourné par la méthode getAll 
		//de l'objet ChambreDao,instancié directement avec le mot clef NEW pour afficher la liste des Chambres
		System.out.println("| id | n°ch | nbLS | nbLD | sup | Sb | tv | bal | ref | bai | iso | prix | catH    |");		
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
		System.out.print("| id | dateDeb Res  | dateFin Res  | methP  | Mont Pai | Date Paiemt |");
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		new PaiementDao().getAll().forEach((p)->{ 
			System.out.println("|  "+p.getId_p()+ " | " +
			new ReservDao().getById(p.getId_p()).getDateDebRes()
			+"   | "+new ReservDao().getById(p.getId_p()).getDateFinR()
			+ "   | "+p.getMethP().substring(0,2).concat("...")+"  |"+p.getMontP()+"  | " +p.getDateP()+" |");
			System.out.println("--------------------------------------------------------------------");
		}
				);
		// new PaiementDao().getAll().forEach((p)->{
		// 	System.out.println("{id: " + p.getId_p()+ " ,dateP: "+ p.getDateP()+
		// 	" ,dateResCon[" +new ReservDao().getById(p.getId_reserv()).getDateDebRes()+
		// 	"/"+new ReservDao().getById(p.getId_reserv()).getDateFinR()+" ]" +
		// 	" ,montP :"+p.getMontP()+ " ,methP :"+p.getMethP() +" }");
		// });
		// System.out.println();
	}
	

	//-------------------- LISTE DES HOTELS ----------------------
	public static void listerLesHotels(){
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
		System.out.println("| id_soc | numSiret          | nom          | adresseS                                         |");
		System.out.println("------------------------------------------------------------------------------------------------");
		new SocieteDao().getAll().forEach((s)->{
			System.out.println("| "+s.getId_soc()+ "      |"+ s.getNumSiret()+ "  |" +s.getNom()+ "   |" +s.getAdresseS()+ "|");
			System.out.println("------------------------------------------------------------------------------------------------");

		});
	}

// ====================================== METHODESS D'AJOUT ===================a	

public static void ajouterUnClient() {

		boolean err;
		do {
			try {
				err = false;
				Client clt = new Client();
				System.out.println("Saisir nom du client à ajouter");
				// flash
				clavier.nextLine();
				clt.setNomC(clavier.nextLine());

				System.out.println("Ville du client à ajouter");
				clt.setVilleC(clavier.nextLine());

				System.out.println("Age du client à ajouter");
				clt.setAgeC(clavier.nextInt());
				// flash
				clavier.nextLine();
				System.out.println("Prenom du client à ajouter");
				clt.setPrenomC(clavier.nextLine());

				System.out.println("adresse du client");
				clt.setAdC(clavier.nextLine());

				String iPut;
				do {
					System.out.println("mail du client");
					iPut = clavier.nextLine();
				} while (!isValidMail(iPut));

				clt.setMailC(iPut);

				String sexe;
				do {
					System.out.println("Sexe du client F(femme)/H(homme)");
					sexe =clavier.nextLine();		
				} while (!sexe.equalsIgnoreCase("F") && !sexe.equalsIgnoreCase("M") );
				clt.setSexeC(sexe);

				String tel;
				do {
					System.out.println("tél du client");
					tel= clavier.nextLine();
				} while (!isValidTel(tel));

				clt.setTelC(tel);

				System.out.println("pays du client");
				clt.setPaysC(clavier.nextLine());
				System.out.println(clt);
				// insertion
				// new ClientDao().save(clt);

			} catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			}

		} while (err);
	}

public static void ajouterUneChambre(){
	boolean err;
	do {
		try {
			err = false;
			Chambre ch = new Chambre();
			// flash
			clavier.nextLine();

			int numCh;
			do {
				System.out.println("Saisir numéro chambre à ajouter");
				numCh = clavier.nextInt();
			} while (numCh <0);
			
			ch.setNumCh(numCh);

			int litS;
			do {
				System.out.println("Saisir nombre de lits Simples à ajouter");
				litS = clavier.nextInt();
			} while (litS <0);
			
			ch.setNbLitChSimp(litS);

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

			clavier.nextLine();
			String slB;
			do {
				System.out.println("disponibilité de salle de bain(oui/non) ?");
				slB = clavier.nextLine();
			} while (!slB.equalsIgnoreCase("OUI") && !slB.equalsIgnoreCase("NON"));

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

			new HotelDao().getAll().forEach((h)->{
				System.out.println("{ id : "+h.getId_hotel()+" ,nomHotel : " +h.getNomH()+" }");
			});
			int idH;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idH = clavier.nextInt();
			} while (new HotelDao().getById(idH)==null);

			ch.setId_hotel(idH);
			System.out.println(ch);
			// insertion
			// new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void effectuerUnPaiement(){
	boolean err;
	do {
		try {
				err = false;

				Paiement p = new Paiement();

				listeDesReservations();
				
				int idR;
				do {
					System.out.println("choisir la réservation à régler en tapant l'ID");
					idR = clavier.nextInt();
				} while (new ReservDao().getById(idR) ==null);

				p.setId_reserv(idR);

				String date1 = new ReservDao().getById(idR).getDateDebRes();
				String date2 = new ReservDao().getById(idR).getDateFinR();
				int idch = new ReservDao().getIdChambreByReser(idR);
				float priUCh = new ChambreDao().getById(idch).getPrixNtCh();

				float nbTnuit = nbJourRes(date1, date2);
				
				float mtTotal = nbTnuit*priUCh;

				System.out.println("Reste à Charge : " +mtTotal);

				clavier.nextLine();
				String moyP;
				do {
					System.out.println("comment reglez vous (cb/esp/ch) ?");
					moyP =clavier.nextLine();		
				} while (!moyP.equalsIgnoreCase("cb") && !moyP.equalsIgnoreCase("esp") && !moyP.equalsIgnoreCase("ch"));
				
				p.setMethP(moyP);

				// int mens;
				// do {
				// 	System.out.println("En combien de fois ?");
				// 	mens = clavier.nextInt();
				// } while (mens <0);

				// System.out.println("Vous paierez : " + (mtTotal/mens) +"/mois");

				int versmt;
				do {
					System.out.println("Vous premier versement ?");
					versmt = clavier.nextInt();
				} while (versmt <0);

				System.out.println("Reste à payer :"+ (mtTotal-versmt) +" E");

				p.setMontP(versmt);

				clavier.nextLine();
				String datePaiement;
				do {
					System.out.println("Saisir une date de paiement au format (YYYY-MM-DD)");
					datePaiement = clavier.nextLine();
				} while (!isValidDate(datePaiement));

				p.setDateP(datePaiement);
			
				System.out.println(p);
			} 
			catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

} while (err);

}

public static void ajouterUnHotel(){
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

			// flash

			System.out.println("Saisir la description(MAX 500 caract) à ajouter");
			String des = clavier.nextLine();

			if (des.length()==490) {
				System.out.println("il vous reste 10 carateres au maximum");
			} else {
				htl.setDescriptionH(des);
			}

			// clavier.nextLine();

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

			System.out.println("saisir heure arrivéé à l'hotel");
			String hrArr =clavier.nextLine();		
			
			htl.setHeureAaH(hrArr);

			System.out.println("saisir heure départ de l'hotel");
			String hrDep =clavier.nextLine();		
			
			htl.setHeureDepH(hrDep);

			String pisc;
			do {
				System.out.println("disponibilité de piscine (OUI/NON) ?");
				pisc =clavier.nextLine();		
			} while (!pisc.equalsIgnoreCase("oui") && !pisc.equalsIgnoreCase("non") );
			
			htl.setPiscineH(pisc);

			String nav;
			do {
				System.out.println("disponibilité de navette (OUI/NON) ?");
				nav =clavier.nextLine();		
			} while (!nav.equalsIgnoreCase("oui") && !nav.equalsIgnoreCase("non") );
			
			htl.setNavetteH(nav);

			String pAni;
			do {
				System.out.println("Acceptaion animal (OUI/NON) ?");
				pAni =clavier.nextLine();		
			} while (!pAni.equalsIgnoreCase("oui") && !pAni.equalsIgnoreCase("non") );
			
			htl.setPresenceAniH(pAni);

			int catH;
			do {
				System.out.println("Catégorie de l'hotel");
				catH =clavier.nextInt();		
			} while (catH <0);
			
			htl.setCatH(catH);

			new SocieteDao().getAll().forEach((s)->{
				System.out.println("{ id : "+s.getId_soc()+" ,nomSociete : " +s.getNom()+" }");
			});


			int idS;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idS = clavier.nextInt();
			} while (new HotelDao().getById(idS)==null);

			htl.setId_soc(idS);
			System.out.println(htl);
			// insertion
			// new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void ajouterUneSociete(){
	clavier.nextLine();
	System.out.println("Donnez un num de Siret");
	String numS = clavier.nextLine();
	System.out.println("Donnez un nom de societe");
	String nmSoc = clavier.nextLine();
	System.out.println("Donnez une adresse  de societe");
	String adS = clavier.nextLine();

	Societe s = new Societe(numS,nmSoc,adS);
	
	new SocieteDao().save(s);
}

public static void effectuerUneReservation(){
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


	Reservation res = new Reservation();

	clavier.nextLine();
	String dateDeb;
	do {
		System.out.println("Saisir une date de début de reservation au format (YYYY-MM-DD)");
		dateDeb = clavier.nextLine();
	} while (!isValidDate(dateDeb));

	String dateFin;
	do {
		System.out.println("Saisir une date de fin de reservation au format (YYYY-MM-DD)");
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
			// float nbj = nbJourRes(dateDeb,dateFin);
			// System.out.println(nbj);
		}else{
			System.out.println("Date dé&but de réservation doit etre inferieure \nà la date de fin de reservation");
			effectuerUneReservation();
		}
	} catch (ParseException e) {
		System.out.println("erreur parse Comparaison");
	}


	int nbPer;
	do {
		System.out.println("indiquer le nombre de personnes pour la réservation");
		nbPer = clavier.nextInt();
	} while (nbPer <0);

	res.setNbPerso(nbPer);

	listeDesChambres();
	int idCh;
	do {
		System.out.println("Veuillez choisir la chambre à réserver en tapant l'ID");
		idCh = clavier.nextInt();
	} while (new ChambreDao().getById(idCh)==null);

	res.setId_ch(idCh);

	listeDesClients();
	int idClt;
	do {
		System.out.println("Veuillez choisir le client effectuant la réservation en tapant l'ID");
		idClt = clavier.nextInt();
	} while (new ClientDao().getById(idClt)==null);

	res.setId_client(idCh);

	System.out.println(res);
} catch (InputMismatchException e) {
	err = true;
	System.err.println("/!\\ Saisir le bon type de donnée demandé !");
}

} while (err);


}



// ===================================== SUP FONCTIONS ====================
public static void supprimerUnClient() {

	boolean err;

	do {
		err = false;
		try {

			listeDesClients();

			int idC;
			do {
				System.out.println("Saisir l'ID du client à sup");
				idC = clavier.nextInt();
			} while (new ClientDao().getById(idC) == null);

			Client c = new ClientDao().getById(idC);

			System.out.println("ATTENTION les réservations du client seront aussi supp : 1(CONF)/0(ANN)".toUpperCase());

			int resp = clavier.nextInt();
			if (resp == 1){
				// new ClientDao().delete(c.getId_client());
				System.out.println(c.getId_client());
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

public static void supprimerUneChambre() {

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
				// new ChambreDao().delete(chbre.getId_ch());
				System.out.println(chbre.getId_ch());
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
				// new ReservDao().delete(res.getId_reserv());
				System.out.println(res.getId_reserv());
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
				// new HotelDao().delete(hot.getId_hotel());
				System.out.println(hot.getId_hotel());
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
				// new SocieteDao().delete(soc.getId_soc());
				System.out.println(soc.getId_soc());
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
				// new PaiementDao().getById(pymt.getId_p());
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

	boolean err;
	do {
		err = false;
		try {

			// Affichage à l'utilisateur ID Client à modifier
			System.out.println("Liste des client à Modofier exple id/prenom");
			new ClientDao().getAll().forEach((cl) -> {
				System.out.println("{ id : " + cl.getId_client() + " , prenom :" + cl.getPrenomC() +
				" ,mail : "+cl.getMailC()+" ,tel : " +cl.getTelC()+
				" }");
			});


			int idCl;
			do {
				System.out.println("Saisir l'ID du client à modifier");
				idCl = clavier.nextInt();
			} while (new ClientDao().getById(idCl) == null);

			Client clt = new ClientDao().getById(idCl);

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

			System.out.println(clt);
			// new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
			clavier.nextLine();
		}
	} while (err);
}

public static void modifierUnHotel(){
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

			// flash

			System.out.println("Saisir la description(MAX 500 caract) à mod " +htl.getDescriptionH());
			String des = clavier.nextLine();

			if (des.length()==490) {
				System.out.println("il vous reste 10 carateres au maximum");
			} else {
				htl.setDescriptionH(des);
			}

			// clavier.nextLine();

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

			System.out.println("saisir heure arrivéé à l'hotel à modifier"+htl.getHeureAaH());
			String hrArr =clavier.nextLine();		
			
			htl.setHeureAaH(hrArr);

			System.out.println("saisir heure départ de l'hotel à modifier" +htl.getHeureDepH());
			String hrDep =clavier.nextLine();		
			
			htl.setHeureDepH(hrDep);

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
			} while (!pAni.equalsIgnoreCase("oui") && !pAni.equalsIgnoreCase("non") );
			
			htl.setPresenceAniH(pAni);

			int catH;
			do {
				System.out.println("Catégorie de l'hotel à mod " +htl.getCatH());
				catH =clavier.nextInt();		
			} while (catH <0);
			
			htl.setCatH(catH);

			new SocieteDao().getAll().forEach((s)->{
				System.out.println("{ id : "+s.getId_soc()+" ,nomSociete : " +s.getNom()+" }");
			});


			int idS;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idS = clavier.nextInt();
			} while (new HotelDao().getById(idS)==null);

			htl.setId_soc(idS);
			System.out.println(htl);
			// insertion
			// new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void modifierUneChambre(){
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

			new HotelDao().getAll().forEach((h)->{
				System.out.println("{ id : "+h.getId_hotel()+" ,nomHotel : " +h.getNomH()+" }");
			});
			int idH;
			do {
				System.out.println("Saisir un ID parmis les ID proposés ci-dessus");
				idH = clavier.nextInt();
			} while (new HotelDao().getById(idH)==null);

			ch.setId_hotel(idH);
			System.out.println(ch);
			// insertion
			// new ClientDao().save(clt);

		} catch (InputMismatchException e) {
			err = true;
			System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

	} while (err);
}

public static void modifierUnPaiement(){
	boolean err;
	do {
		try {
				err = false;

				listeLesPaiements();

				int idP;
				do {
					System.out.println("Saisir l'ID de la chambre à modifier");
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

				String date1 = new ReservDao().getById(idR).getDateDebRes();
				String date2 = new ReservDao().getById(idR).getDateFinR();
				int idch = new ReservDao().getIdChambreByReser(idR);
				float priUCh = new ChambreDao().getById(idch).getPrixNtCh();

				float nbTnuit = nbJourRes(date1, date2);
				
				float mtTotal = nbTnuit*priUCh;

				System.out.println("Reste à Charge : " +mtTotal);

				clavier.nextLine();
				String moyP;
				do {
					System.out.println("modification mode de paiement " +p.getMethP());
					moyP =clavier.nextLine();		
				} while (!moyP.equalsIgnoreCase("cb") && !moyP.equalsIgnoreCase("esp") && !moyP.equalsIgnoreCase("ch"));
				
				p.setMethP(moyP);

				// int mens;
				// do {
				// 	System.out.println("En combien de fois ?");
				// 	mens = clavier.nextInt();
				// } while (mens <0);

				// System.out.println("Vous paierez : " + (mtTotal/mens) +"/mois");

				int versmt;
				do {
					System.out.println("modification de verssement " +p.getMethP());
					versmt = clavier.nextInt();
				} while (versmt <0);

				System.out.println("Reste à payer :"+ (mtTotal-versmt) +" E");

				p.setMontP(versmt);

				clavier.nextLine();
				String datePaiement;
				do {
					System.out.println("modification date du paiement au format (YYYY-MM-DD) "+p.getDateP());
					datePaiement = clavier.nextLine();
				} while (!isValidDate(datePaiement));

				p.setDateP(datePaiement);
			
				System.out.println(p);
			} 
			catch (InputMismatchException e) {
				err = true;
				System.err.println("/!\\ Saisir le bon type de donnée demandé !");
		}

} while (err);

}

public static void modifiUneSociete(){

	listeLesSocietes();

	int idS;
	do {
		System.out.println("Saisir l'ID de la chambre à modifier");
		idS = clavier.nextInt();
	} while (new SocieteDao().getById(idS)==null);

	Societe socte = new SocieteDao().getById(idS);

	clavier.nextLine();
	System.out.println("numero de Siret à mod " +socte.getNumSiret());
	socte.setNumSiret(clavier.nextLine());

	System.out.println(" nom de societe à mod " +socte.getNom());
	socte.setNom(clavier.nextLine());

	System.out.println("adresse  de societe à mod " +socte.getAdresseS());
	socte.setAdresseS(clavier.nextLine());

	System.out.println(socte);	
	// new SocieteDao().save(s);
}

public static void modifierUneReservation(){
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
	} while (nbPer <0);

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

	System.out.println(res);
} catch (InputMismatchException e) {
	err = true;
	System.err.println("/!\\ Saisir le bon type de donnée demandé !");
}

} while (err);


}


// ===================================== RECHERCHEs FONCTIONS ====================
public static void rechercherUnClient(){

	// flash
	clavier.nextLine();

	System.out.println("Saisir le prénom du Client à cherecher");

	new ClientDao().recherche(clavier.nextLine()).forEach((cl) -> {
		System.out.println("{ id :" + cl.getId_client() + " ,nom :" + cl.getPrenomC() +
				" ,ville :" + cl.getVilleC() + " ,age :" + cl.getAgeC() + " ,prénom : " 
				+ cl.getPrenomC() +" ,mail: "+cl.getMailC() +" ,tel :"+cl.getTelC()+" }");
	});

}


public static void rechercherUnReservation(){
	System.out.println("Saisir la date de début ou date de fin de réservation");
	ChambreDao objCh = new ChambreDao();
	ClientDao objCl = new ClientDao();
	HotelDao objHot = new HotelDao();
	if (new ReservDao().recherche(clavier.nextLine()).isEmpty()) {
		System.out.println("Auncun résultat".toUpperCase());
	}else{
		new ReservDao().recherche(clavier.nextLine()).forEach((r)->{
			int id_ch = new ReservDao().getIdChambreByReser(r.getId_reserv());
			int id_client = new ReservDao().getIdChambreByReser(r.getId_reserv());
			int id_hotel = objCh.getById(id_ch).getId_ch();
			System.out.println( r.getId_reserv());
			System.out.println("numCham =>"+objCh.getById(id_ch).getNumCh());
			System.out.println("id_hotel =>"+objHot.getById(id_hotel).getId_hotel());
			System.out.println("id_client => " + objCl.getById(id_client).getId_client());
			
		});
	
	}
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
