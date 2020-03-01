package com.example.asknanterre;

import com.orm.SugarRecord;

public class Question extends SugarRecord  {
    String nom;
    String titre;
    int upvote = 0;
    int downvote = 0;
    boolean valide = false;
    int type = 1;
    String date;

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getUpvoteProf() {
        return upvoteProf;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public void setUpvoteProf(int upvoteProf) {
        this.upvoteProf = upvoteProf;
    }

    public void setValide(boolean valide) { this.valide = valide; }

    public boolean isRepondu() {
        return repondu;
    }

    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
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
