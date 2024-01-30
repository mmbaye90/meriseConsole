package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Chambre;
import entites.Db;
import entites.ICatalogue;

public class ChambreDao implements ICatalogue<Chambre>{

    @Override
    public ArrayList<Chambre> getAll() {
        ArrayList <Chambre> listeCh = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM chambre");

            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Chambre ch = new Chambre();

                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));

                listeCh.add(ch);
            }
            return listeCh;
        } catch (Exception e) {
            System.err.println("Liste des chambres introuvables".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Chambre getById(int id) {
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * FROM Chambre WHERE id_ch = ?");
            ps.setInt(1, id);
            ResultSet resp = ps.executeQuery();
            if (resp.next()) {
                Chambre ch = new Chambre();

                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));
                
                return ch;                  
            }else return null;
        } catch (Exception e) {
            System.err.println("Objet Chambre introuvable");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Chambre> recherche(String w) {
        ArrayList <Chambre> listeCh = new ArrayList<>();
        try {
            PreparedStatement ps = Db.con.prepareStatement
            ("SELECT * from chambre WHERE prixNtCh LIKE ?");
            ps.setString(1, "%"+w+"%");
            ResultSet resp = ps.executeQuery();

            while (resp.next()) {
                Chambre ch = new Chambre();

                ch.setId_ch(resp.getInt("id_ch"));
                ch.setNumCh(resp.getInt("numCh"));
                ch.setNbLitChSimp(resp.getInt("nbLitChSimp"));
                ch.setNbLitChDoub(resp.getInt("nbLitChDoub"));
                ch.setSupCh(resp.getInt("supCh"));
                ch.setSalleBainP(resp.getString("salleBainP"));
                ch.setTvCh(resp.getString("tvCh"));
                ch.setBalconCh(resp.getString("balconCh"));
                ch.setRefrigerCh(resp.getString("refrigerCh"));
                ch.setBaignCh(resp.getString("baignCh"));
                ch.setInsonoCh(resp.getString("insonoCh"));
                ch.setPrixNtCh(resp.getFloat("prixNtCh"));

                listeCh.add(ch);
            }
            return listeCh;
        } catch (Exception e) {
            System.err.println("Donnée introuvable".toUpperCase());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Chambre ch) {
    try {
        
        if(ch.getId_ch() != 0) {
            PreparedStatement ps  = Db.con.prepareStatement
            ("UPDATE chambre SET numCh=?,nbLitChSimp=?,nbLitChDoub=?,supCh=?,salleBainP=?,tvCh=?,balconCh=?,refrigerCh=?,baignCh=?,insonoCh=?,prixNtCh=? WHERE id_ch=?");
            ps.setInt(1,ch.getNumCh());
            ps.setInt(2,ch.getNbLitChSimp());
            ps.setInt(3,ch.getNbLitChDoub());
            ps.setInt(4,ch.getSupCh());
            ps.setString(5, ch.getSalleBainP());
            ps.setString(6, ch.getTvCh());
            ps.setString(7, ch.getBaignCh());
            ps.setString(8, ch.getRefrigerCh());
            ps.setString(9, ch.getBaignCh());
            ps.setString(10, ch.getInsonoCh());
            ps.setFloat(11, ch.getPrixNtCh());
            ps.setInt(12, ch.getId_ch());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée".toUpperCase());
        }else {
            PreparedStatement ps  = Db.con.prepareStatement
            ("INSERT INTO chambre (numCh,nbLitChSimp,nbLitChDoub,supCh,salleBainP,tvCh,balconCh,refrigerCh,baignCh,insonoCh,prixNtCh) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,ch.getNumCh());
            ps.setInt(2,ch.getNbLitChSimp());
            ps.setInt(3,ch.getNbLitChDoub());
            ps.setInt(4,ch.getSupCh());
            ps.setString(5, ch.getSalleBainP());
            ps.setString(6, ch.getTvCh());
            ps.setString(7, ch.getBaignCh());
            ps.setString(8, ch.getRefrigerCh());
            ps.setString(9, ch.getBaignCh());
            ps.setString(10, ch.getInsonoCh());
            ps.setFloat(11, ch.getPrixNtCh());
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
            ("DELETE FROM chambre WHERE id_ch=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Supprimé".toUpperCase());
        
    } catch (Exception e) {
        System.out.println("non suppriùé".toLowerCase());
        e.printStackTrace();
    }
}

}
