package com.example.asknanterre;

import com.orm.SugarRecord;

public class QCM extends SugarRecord {
    String rep;
    String questionId;

    public QCM() {}

    public QCM(String rep, String questionId) {
        this.rep = rep;
        this.questionId = questionId;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
