package entites;

public class Societe {
	private int id_soc;
	private String numSiret;
	private String nom;
	private String adresseS;
	public Societe() {
		super();
	}
	public Societe(String numSiret, String nom, String adresseS) {
		super();
		this.numSiret = numSiret;
		this.nom = nom;
		this.adresseS = adresseS;
	}
	public Societe(int id_soc, String numSiret, String nom, String adresseS) {
		super();
		this.id_soc = id_soc;
		this.numSiret = numSiret;
		this.nom = nom;
		this.adresseS = adresseS;
	}
	public int getId_soc() {
		return id_soc;
	}
	public void setId_soc(int id_soc) {
		this.id_soc = id_soc;
	}
	public String getNumSiret() {
		return numSiret;
	}
	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresseS() {
		return adresseS;
	}
	public void setAdresseS(String adresseS) {
		this.adresseS = adresseS;
	}
	@Override
	public String toString() {
		return "Societe [id_soc=" + id_soc + ", numSiret=" + numSiret + ", nom=" + nom + ", adresseS=" + adresseS + "]";
	}
	
	
}
