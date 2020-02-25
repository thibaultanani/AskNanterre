package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    List<QuestionProf> quiztmp;
    List<String> quizIDtmp;
    String[] q1;
    String[] q2;
    String[] q3;
    String[] q4;
    String[] q5;
    String[] q6;
    private DatabaseReference mQuestionReference;
    ProgressBar progressBar;
    Spinner spinner;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquizz);

        mQuestionReference = FirebaseDatabase.getInstance().getReference()
                .child("questionProf");
        updateList();

        spinner = (Spinner) findViewById(R.id.spinner1);

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerArray.add("Trier par date");
        spinnerArray.add("Trier par upvote");
        spinnerArray.add("Trier par downvote");
        spinnerArray.add("Trier par upvote du prof");
        spinnerArray.add("Trier par downvote du prof");

        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }


    public void updateList() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionRef = rootRef.child("questionProf");
        quiz = new ArrayList<QuestionProf>();
        quizID = new ArrayList<String>();
        quiztmp = new ArrayList<QuestionProf>();
        quizIDtmp = new ArrayList<String>();
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
                progressBar.setVisibility(View.GONE);

                q1 = new String[quiz.size()];


                myListView = (ListView) findViewById(R.id.myListView);

                for (int i = 0; i < quiz.size(); i++) {
                    q1[i] = quiz.get(i).nom;

                }

                edit = (EditText) findViewById(R.id.EditText01);

                edit.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) {}

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        quiztmp.clear();
                        quizIDtmp.clear();
                        final int size = quiz.size();
                        if(s.length()!=0){
                            for(int i=0; i<size; i++) {
                                if(quiz.get(i).nom.toLowerCase().contains(s.toString().toLowerCase())) {
                                    quiztmp.add(quiz.get(i));
                                    quizIDtmp.add(quizID.get(i));
                                }
                            }
                        }

                        if(quiztmp.size()!=0) {
                            quiz.clear();
                            quizID.clear();
                            quiz.addAll(quiztmp);
                            quizID.addAll(quizIDtmp);
                            q1 = new String[quiz.size()];

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
                        else {
                            updateList();
                        }
                    }
                });

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
