package com.example.asknanterre;

import com.orm.SugarRecord;

public class Answer extends SugarRecord {
    String nom;
    String questionId;

    public Answer(){}

    public Answer(String nom, String questionId){
        this.nom = nom;
        this.questionId = questionId;
    }


    public String getNom(){return nom;}

    //public int getUpvote(){return upvote;}

    public void setNom(String nom){
        this.nom = nom;
    }

    //public void setUpvote (int upvote) {this.upvote = upvote;}

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

}