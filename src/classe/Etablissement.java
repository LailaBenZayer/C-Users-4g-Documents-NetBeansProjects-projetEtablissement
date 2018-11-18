/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author 4g
 */

@Entity
public class Etablissement {
    
    @Id
    @GeneratedValue
    
    private int id;
    private String nom;
    private String type;
    private String region;
    private String poursuit;

    public Etablissement() {
    }

    public Etablissement(int id, String nom, String type, String region, String poursuit) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.poursuit = poursuit;
    }

    public Etablissement(String nom, String type, String region, String poursuit) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.poursuit = poursuit;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPoursuit() {
        return poursuit;
    }

    public void setPoursuit(String poursuit) {
        this.poursuit = poursuit;
    }

    @Override
    public String toString() {
        return  nom ;
    }
    
    
    
}
