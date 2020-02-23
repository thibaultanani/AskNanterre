package com.example.asknanterre;

import com.orm.SugarRecord;

public class QuestionProf extends SugarRecord {
    String nom;
    boolean repondu= false;
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
}
