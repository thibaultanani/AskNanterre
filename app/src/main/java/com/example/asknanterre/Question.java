package com.example.asknanterre;

import com.orm.SugarRecord;

public class Question extends SugarRecord  {
    String nom;
    int upvote = 0;
    boolean valide = false;
    int type = 1;
    boolean repondu= false;
    int upvoteProf = 0;

    public Question() {

    }

    public Question(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getUpvote() {
        return upvote;
    }

    public boolean getValide() { return valide; }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public void setValide(boolean valide) { this.valide = valide; }

    public boolean isRepondu() {
        return repondu;
    }

    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
    }
}
