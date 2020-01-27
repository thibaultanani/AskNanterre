package com.example.asknanterre;

import com.orm.SugarRecord;

public class Cours extends SugarRecord {
     String nom;


    public Cours() {}

    public Cours(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
