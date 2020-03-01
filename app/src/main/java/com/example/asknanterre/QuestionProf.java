package com.example.asknanterre;

import com.orm.SugarRecord;

public class QuestionProf extends SugarRecord {
    String nom;
    boolean repondu= false;
    String date;
    String titre;
    int difficulte;
    int Ncorrects;
    int Nfalses;

    public QuestionProf() {}

    public QuestionProf(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isRepondu() {
        return repondu;
    }

    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getNcorrects() {
        return Ncorrects;
    }

    public void setNcorrects(int ncorrects) {
        Ncorrects = ncorrects;
    }

    public int getNfalses() {
        return Nfalses;
    }

    public void setNfalses(int nfalses) {
        Nfalses = nfalses;
    }
}
