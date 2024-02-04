package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.ICatalogue;
import entites.Paiement;


//Meme commentaire que dans la classe Chambre
public class PaiementDao implements ICatalogue<Paiement>{

    @Override
    public ArrayList<Paiement> getAll() {
        ArrayList <Paiement> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                p.setId_reserv(resp.getInt("id_reserv"));
                listeP.add(p);
            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Liste des paiements introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Paiement getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM paiement WHERE id_p = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                
                return p;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Paiement introuvable");
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public ArrayList<Paiement> recherche(String w) {
        ArrayList <Paiement> listeP = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from paiement WHERE dateP LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Paiement p = new Paiement();
                p.setId_p(resp.getInt("id_p"));
                p.setDateP(resp.getString("dateP"));
                p.setMontP(resp.getFloat("montP"));
                p.setMethP(resp.getString("methP"));
                listeP.add(p);
            }
            return listeP;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void save(Paiement p) {
    try {
        
        if(p.getId_p() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE paiement SET dateP=?,montP=?,methP=? WHERE id_p=?");
            ps.setString(1,p.getDateP());
            ps.setFloat(2,p.getMontP());
            ps.setString(3,p.getMethP());
            ps.setInt(4,p.getId_p());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO paiement (dateP,montP,methP) VALUES(?,?,?)");
            ps.setString(1,p.getDateP());
            ps.setFloat(2,p.getMontP());
            ps.setString(3,p.getMethP());
            ps.executeUpdate();
            System.out.println("insertion Réussie".toUpperCase());
        }
        
    } catch (Exception e) {
        System.err.println("update ou insertion échouée".toUpperCase());
        e.printStackTrace();
    }
    }


    @Override
    public void delete(int id) {
        try {
            
            PreparedStatement ps  = Db.con.prepareStatement
            ("DELETE FROM paiement WHERE id_p=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }    }

}
