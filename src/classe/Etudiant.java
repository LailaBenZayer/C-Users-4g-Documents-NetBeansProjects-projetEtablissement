/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author 4g
 */
@Entity
public class Etudiant {
    @Id
    @GeneratedValue
    
    private int id;
    private String Nom;
    private String prenom;
    private Date dtaeNaissnace;
    private String lieuNaissnace;
    private String codeNational;
    private String niveauEtude;

    @ManyToOne
    private Etablissement etablissement;
    
    public Etudiant() {
    }

    public Etudiant(int id, String Nom, String prenom, Date dtaeNaissnace, String lieuNaissnace, String codeNational, String niveauEtude, Etablissement etablissement) {
        this.id = id;
        this.Nom = Nom;
        this.prenom = prenom;
        this.dtaeNaissnace = dtaeNaissnace;
        this.lieuNaissnace = lieuNaissnace;
        this.codeNational = codeNational;
        this.niveauEtude = niveauEtude;
        this.etablissement = etablissement;
    }

    public Etudiant(String Nom, String prenom, Date dtaeNaissnace, String lieuNaissnace, String codeNational, String niveauEtude, Etablissement etablissement) {
        this.Nom = Nom;
        this.prenom = prenom;
        this.dtaeNaissnace = dtaeNaissnace;
        this.lieuNaissnace = lieuNaissnace;
        this.codeNational = codeNational;
        this.niveauEtude = niveauEtude;
        this.etablissement = etablissement;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtaeNaissnace() {
        return dtaeNaissnace;
    }

    public void setDtaeNaissnace(Date dtaeNaissnace) {
        this.dtaeNaissnace = dtaeNaissnace;
    }

    public String getLieuNaissnace() {
        return lieuNaissnace;
    }

    public void setLieuNaissnace(String lieuNaissnace) {
        this.lieuNaissnace = lieuNaissnace;
    }

    public String getCodeNational() {
        return codeNational;
    }

    public void setCodeNational(String codeNational) {
        this.codeNational = codeNational;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    
    
}
