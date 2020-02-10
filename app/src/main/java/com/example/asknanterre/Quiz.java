package com.example.asknanterre;

import com.orm.SugarRecord;

public class Quiz extends SugarRecord {
    String rep;
    long questionId;
    boolean correct;

    public Quiz(String rep, long questionId, boolean correct) {
        this.rep = rep;
        this.questionId = questionId;
        this.correct = correct;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
