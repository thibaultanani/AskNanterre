package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DisplayQuizz extends AppCompatActivity {

    ListView myListView;
    EditText name;
    List<QuestionProf> quiz;
    List<String> quizID;
    String[] q1;
    String[] q2;
    String[] q3;
    String[] q4;
    String[] q5;
    String[] q6;
    private DatabaseReference mQuestionReference;
    ProgressBar progressBar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquizz);

        mQuestionReference = FirebaseDatabase.getInstance().getReference()
                .child("questionProf");
        updateList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }


    public void updateList() {



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionRef = rootRef.child("questionProf");
        quiz = new ArrayList<QuestionProf>();
        quizID = new ArrayList<String>();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quiz.clear();
                quizID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();

                        quiz.add(ds.getValue(QuestionProf.class));
                        quizID.add(ds.getKey());
                }

                q1 = new String[quiz.size()];


                myListView = (ListView) findViewById(R.id.myListView);

                for (int i = 0; i < quiz.size(); i++) {
                    q1[i] = quiz.get(i).nom;

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuizz.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(quizID);


                CustomAdapterQuiz adapt = new CustomAdapterQuiz(list1, list2, DisplayQuizz.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        questionRef.addListenerForSingleValueEvent(eventListener);


    }






}
