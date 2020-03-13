package com.example.asknanterre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DisplayQuiz extends AppCompatActivity {

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
    Integer[] q6;
    Integer[] q7;
    private DatabaseReference mQuestionReference;
    ProgressBar progressBar;
    Spinner spinner;
    EditText edit;
    Bundle b;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquizz);

        b = getIntent().getExtras();
        coursId = b.getString("key");

        mQuestionReference = FirebaseDatabase.getInstance().getReference()
                .child("questionProf");
        updateList();

        spinner = (Spinner) findViewById(R.id.spinner1);

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerArray.add(getString(R.string.trier_par_date));
        spinnerArray.add(getString(R.string.trier_par_bonne_reponse));
        spinnerArray.add(getString(R.string.trier_par_mauvaise_reponse));
        spinnerArray.add(getString(R.string.trier_par_difficulte));

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

    public void trier(View v, final int position) {
        myListView = (ListView) findViewById(R.id.myListView);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        b = getIntent().getExtras();
        coursId = b.getString("key");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionProfRef = rootRef.child("questionProf");
        quiz = new ArrayList<QuestionProf>();
        quizID = new ArrayList<String>();
        quiztmp = new ArrayList<QuestionProf>();
        quizIDtmp = new ArrayList<String>();

        String s;
        if (position == 1) {
            s = "ncorrects";
        } else if (position == 2) {
            s = "nfalses";
        } else if (position == 3) {
            s = "difficulte";
        } else {
            s = "";
        }

        Query q;
        if (!s.isEmpty()) {
            q = questionProfRef.orderByChild(s);
        } else {
            q = questionProfRef;
        }

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quiz.clear();
                quizID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getValue(QuestionProf.class).coursId.equals(coursId)) {
                        String product = ds.getKey();
                        quiz.add(ds.getValue(QuestionProf.class));
                        quizID.add(ds.getKey());
                        Log.d("TAG", product);
                    }
                }
                progressBar.setVisibility(View.GONE);

                if (position != 0) {
                    Collections.reverse(quiz);
                    Collections.reverse(quizID);
                }

                q1 = new String[quiz.size()];
                q3 = new String[quiz.size()];
                q4 = new String[quiz.size()];
                q5 = new String[quiz.size()];
                q6 = new Integer[quiz.size()];
                q7 = new Integer[quiz.size()];


                myListView = (ListView) findViewById(R.id.myListView);

                for (int i = 0; i < quiz.size(); i++) {
                    q1[i] = quiz.get(i).nom;
                    q3[i] = quiz.get(i).titre;
                    q4[i] = quiz.get(i).date;
                    q5[i] = "" + quiz.get(i).difficulte;
                    q6[i] = quiz.get(i).Ncorrects;
                    q7[i] = quiz.get(i).Nfalses;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuiz.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(quizID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                ArrayList<Integer> list6 = new ArrayList(Arrays.asList(q6));
                ArrayList<Integer> list7 = new ArrayList(Arrays.asList(q7));

                CustomAdapterQuiz adapt = new CustomAdapterQuiz(list1, list2, list3, list4, list5, list6, list7, DisplayQuiz.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateList() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        b = getIntent().getExtras();
        coursId = b.getString("key");

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
                    if(ds.getValue(QuestionProf.class).coursId.equals(coursId)) {
                        String product = ds.getKey();
                        quiz.add(ds.getValue(QuestionProf.class));
                        quizID.add(ds.getKey());
                    }
                }
                progressBar.setVisibility(View.GONE);

                q1 = new String[quiz.size()];
                q3 = new String[quiz.size()];
                q4 = new String[quiz.size()];
                q5 = new String[quiz.size()];
                q6 = new Integer[quiz.size()];
                q7 = new Integer[quiz.size()];


                myListView = (ListView) findViewById(R.id.myListView);

                for (int i = 0; i < quiz.size(); i++) {
                    q1[i] = quiz.get(i).nom;
                    q3[i] = quiz.get(i).titre;
                    q4[i] = quiz.get(i).date;
                    q5[i] = "" + quiz.get(i).difficulte;
                    q6[i] = quiz.get(i).Ncorrects;
                    q7[i] = quiz.get(i).Nfalses;
                }

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // your code here
                        trier(selectedItemView, position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });

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
                            q3 = new String[quiz.size()];
                            q4 = new String[quiz.size()];
                            q5 = new String[quiz.size()];
                            q6 = new Integer[quiz.size()];
                            q7 = new Integer[quiz.size()];

                            for (int i = 0; i < quiz.size(); i++) {
                                q1[i] = quiz.get(i).nom;
                                q3[i] = quiz.get(i).titre;
                                q4[i] = quiz.get(i).date;
                                q5[i] = "" + quiz.get(i).difficulte;
                                q6[i] = quiz.get(i).Ncorrects;
                                q7[i] = quiz.get(i).Nfalses;
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuiz.this, android.R.layout.simple_list_item_1, q1);
                            ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                            ArrayList<String> list2 = new ArrayList(quizID);
                            ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                            ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                            ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                            ArrayList<Integer> list6 = new ArrayList(Arrays.asList(q6));
                            ArrayList<Integer> list7 = new ArrayList(Arrays.asList(q7));

                            CustomAdapterQuiz adapt = new CustomAdapterQuiz(list1, list2, list3, list4, list5, list6, list7, DisplayQuiz.this);
                            myListView.setAdapter(adapt);
                            adapt.notifyDataSetChanged();
                        }
                        else {
                            updateList();
                        }
                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuiz.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(quizID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                ArrayList<Integer> list6 = new ArrayList(Arrays.asList(q6));
                ArrayList<Integer> list7 = new ArrayList(Arrays.asList(q7));

                CustomAdapterQuiz adapt = new CustomAdapterQuiz(list1, list2, list3, list4, list5, list6, list7, DisplayQuiz.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        questionRef.addListenerForSingleValueEvent(eventListener);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateList();
    }
}
