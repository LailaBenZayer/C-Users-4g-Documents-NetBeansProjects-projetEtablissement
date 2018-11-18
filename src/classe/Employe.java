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
import javax.persistence.Temporal;

/**
 *
 * @author 4g
 */
@Entity
public class Employe {
 
    @Id
    @GeneratedValue
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmbauche;
    
    @ManyToOne
    private Profil profil;
    
    @ManyToOne
    private Etablissement etablissement;

    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String email, String password, Date dateNaissance, Date dateEmbauche, Profil profil, Etablissement etablissement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.profil = profil;
        this.etablissement = etablissement;
    }

    public Employe(String nom, String prenom, String email, String password, Date dateNaissance, Date dateEmbauche, Profil profil, Etablissement etablissement) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.profil = profil;
        this.etablissement = etablissement;
    }

   

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    
    
    
}
