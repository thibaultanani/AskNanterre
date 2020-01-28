package com.example.asknanterre;

import com.orm.SugarRecord;

public class Question extends SugarRecord  {
    String nom;
    int upvote = 0;

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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }
}
