package com.example.asknanterre;

import java.util.Comparator;

public class UpvoteSorter implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {

            return q2.getUpvote()-q1.getUpvote();

    }
}
