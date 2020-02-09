package com.example.asknanterre;

import com.orm.SugarRecord;

public class QCM extends SugarRecord {
    String rep;
    long questionId;

    public QCM(String rep, long questionId) {
        this.rep = rep;
        this.questionId = questionId;
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

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
