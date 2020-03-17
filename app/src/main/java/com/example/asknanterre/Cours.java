package com.example.asknanterre;

import com.orm.SugarRecord;

public class Cours extends SugarRecord {
    String nom;
    String titre;
    String date;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
