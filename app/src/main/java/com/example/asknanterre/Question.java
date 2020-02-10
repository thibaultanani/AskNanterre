package com.example.asknanterre;

import com.orm.SugarRecord;

public class Question extends SugarRecord  {
    String nom;
    int upvote = 0;
    int downvote=0;

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    boolean valide = false;
    boolean repondu= false;

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
