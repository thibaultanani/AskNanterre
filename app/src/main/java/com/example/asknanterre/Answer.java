package com.example.asknanterre;

import com.orm.SugarRecord;

public class Answer extends SugarRecord {
    String nom;
    //int upvote = 0

    public Answer(){}

    public Answer(String nom){
        this.nom = nom;
    }


    public String getNom(){return nom;}

    //public int getUpvote(){return upvote;}

    public void setNom(String nom){
        this.nom = nom;
    }

    //public void setUpvote (int upvote) {this.upvote = upvote;}

}
