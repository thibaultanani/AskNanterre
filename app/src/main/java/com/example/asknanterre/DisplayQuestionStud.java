package com.example.asknanterre;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.asknanterre.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayQuestionStud extends AppCompatActivity {

   // private final int position;
    ListView myListView;
    EditText name;
    List<Question> questions;
    SimpleAdapter adapter;
    ArrayList<HashMap<String, String>> data;
    //List<Question> quest = Question.find(Question.class, "valide = 1");
    List<Question> quest;
    List<String> questID;
    List<Question> questtmp;
    List<String> questIDtmp;
    /*String[] q1 = new String[quest.size()];
    String[] q2 = new String[quest.size()];
    String[] q3 = new String[quest.size()];
    String[] q4 = new String[quest.size()];
    String[] q5 = new String[quest.size()];
    String[] q6 = new String[quest.size()];
    String[] q5 = new String[quest.size()];*/
    String[] q1;
    String[] q2;
    String[] q3;
    String[] q4;
    String[] q5;
    String[] q6;
    String[] q7;
    String[] q8;
    String[] q9;
    BottomNavigationView bottomNavigationView;
    private DatabaseReference mQuestionReference;
    Menu itemToHide;
    ProgressBar progressBar;
    Spinner spinner;
    EditText edit;
    Bundle b;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        b = getIntent().getExtras();
        coursId = b.getString("key");

        mQuestionReference = FirebaseDatabase.getInstance().getReference()
                .child("question");
        updateList();

        spinner = (Spinner) findViewById(R.id.spinner1);

        myListView = (ListView) findViewById(R.id.myListView);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        myListView.setEmptyView(emptyText);

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerArray.add(getString(R.string.trier_par_date_asc));
        spinnerArray.add(getString(R.string.trier_par_date_des));
        spinnerArray.add(getString(R.string.trier_par_upvote));
        spinnerArray.add(getString(R.string.trier_par_downvote));
        spinnerArray.add(getString(R.string.trier_par_upvote_du_prof));
        spinnerArray.add(getString(R.string.trier_par_downvote_du_prof));
        spinnerArray.add(getString(R.string.trier_par_reponse_du_prof));

        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);

        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                trier(selectedItemView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/

        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkRed));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));*/

        //Collections.sort(quest, new UpvoteSorter());

        /*bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        itemToHide = bottomNavigationView.getMenu();
        itemToHide.findItem(R.id.action_goStud).setVisible(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(DisplayQuestionStud.this, "Recents", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                        break;
                    case R.id.action_goStud:
                        Toast.makeText(DisplayQuestionStud.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_goProf:
                        //Toast.makeText(DisplayQuestionStud.this, "Nearby", Toast.LENGTH_SHORT).show();
                        goToProfUIActivity();
                        break;
                }
                return true;
            }
        });*/

        ActionBar ab = getSupportActionBar();
        ab.setSubtitle(getString(R.string.liste_des_questions));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.action_back:
            //add the function to perform here
            annuler();
            return(true);
        case R.id.action_home:
            //add the function to perform here
            goToMainActivity();
            return(true);
        case R.id.action_help:
            //add the function to perform here
            goToHelpActivity();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }


    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

    public void trier(View v, final int position) {
        myListView = (ListView) findViewById(R.id.myListView);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        myListView.setEmptyView(emptyText);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        b = getIntent().getExtras();
        coursId = b.getString("key");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionRef = rootRef.child("question");
        quest = new ArrayList<Question>();
        questID = new ArrayList<String>();
        questtmp = new ArrayList<Question>();
        questIDtmp = new ArrayList<String>();

        String s;
        if(position == 2) {
            s = "upvote";
        }
        else if(position == 3) {
            s = "downvote";
        }
        else if (position == 4 || position == 5) {
            s = "upvoteProf";
        }
        else if (position == 6) {
            s = "repondu";
        }
        else {
            s = "";
        }

        Query q;
        if(!s.isEmpty()) {
            q = questionRef.orderByChild(s);
        }
        else {
            q = questionRef;
        }

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quest.clear();
                questID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if (ds.getValue(Question.class).valide && (ds.getValue(Question.class).coursId.equals(coursId))) {
                        quest.add(ds.getValue(Question.class));
                        questID.add(ds.getKey());
                    }
                    Log.d("TAG", product);
                }
                progressBar.setVisibility(View.GONE);

                if(position != 5 && position != 0) {
                    Collections.reverse(quest);
                    Collections.reverse(questID);
                }

                q1 = new String[quest.size()];
                q2 = new String[quest.size()];
                q3 = new String[quest.size()];

                q4 = new String[quest.size()];
                q5 = new String[quest.size()];
                q6 = new String[quest.size()];

                q7 = new String[quest.size()];
                q8 = new String[quest.size()];
                q9 = new String[quest.size()];

                for (int i = 0; i < quest.size(); i++) {
                    q1[i] = quest.get(i).nom;
                    q3[i] = "" + quest.get(i).upvote;
                    q4[i]= "" + quest.get(i).repondu;
                    q5[i]= "" + quest.get(i).downvote;
                    q6[i] = "" + quest.get(i).upvoteProf;
                    q7[i] = quest.get(i).titre;
                    q8[i] = quest.get(i).date;
                    q9[i] = "" + quest.get(i).type;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuestionStud.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(questID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                ArrayList<String> list6 = new ArrayList(Arrays.asList(q6));
                ArrayList<String> list7 = new ArrayList(Arrays.asList(q7));
                ArrayList<String> list8 = new ArrayList(Arrays.asList(q8));
                ArrayList<String> list9 = new ArrayList(Arrays.asList(q9));

                CustomAdapterStud adapt = new CustomAdapterStud(list1, list2, list3, list4 ,list5, list6, list7, list8, list9, DisplayQuestionStud.this);
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
        DatabaseReference questionRef = rootRef.child("question");
        quest = new ArrayList<Question>();
        questID = new ArrayList<String>();
        questtmp = new ArrayList<Question>();
        questIDtmp = new ArrayList<String>();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quest.clear();
                questID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if (ds.getValue(Question.class).valide && (ds.getValue(Question.class).coursId.equals(coursId))) {
                        quest.add(ds.getValue(Question.class));
                        questID.add(ds.getKey());
                    }
                    Log.d("TAG", product);
                }
                progressBar.setVisibility(View.GONE);

                q1 = new String[quest.size()];
                q2 = new String[quest.size()];
                q3 = new String[quest.size()];

                q4 = new String[quest.size()];
                q5 = new String[quest.size()];
                q6 = new String[quest.size()];

                q7 = new String[quest.size()];
                q8 = new String[quest.size()];
                q9 = new String[quest.size()];

                myListView = (ListView) findViewById(R.id.myListView);
                TextView emptyText = (TextView)findViewById(android.R.id.empty);
                myListView.setEmptyView(emptyText);

                for (int i = 0; i < quest.size(); i++) {
                    q1[i] = quest.get(i).nom;
                    q3[i] = "" + quest.get(i).upvote;
                    q4[i]= "" + quest.get(i).repondu;
                    q5[i]= "" + quest.get(i).downvote;
                    q6[i] = "" + quest.get(i).upvoteProf;
                    q7[i] = quest.get(i).titre;
                    q8[i] = quest.get(i).date;
                    q9[i] = "" + quest.get(i).type;
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
                        questtmp.clear();
                        questIDtmp.clear();
                        final int size = quest.size();
                        Log.v("taille", ""+size);
                        if(s.length()!=0){
                            for(int i=0; i<size; i++) {
                                Log.v("comparaison", quest.get(i).nom.toLowerCase() + "   " + s.toString().toLowerCase());
                                if(quest.get(i).nom.toLowerCase().contains(s.toString().toLowerCase())) {
                                    questtmp.add(quest.get(i));
                                    questIDtmp.add(questID.get(i));
                                    Log.v("Tour recherche",  ""+ i);
                                }
                            }
                        }

                        for(int j=0; j<questtmp.size(); j++) {
                            Log.v("questtmp", questtmp.get(j).nom + " " + questIDtmp.get(j));
                        }

                        if(questtmp.size()!=0) {
                            quest.clear();
                            questID.clear();
                            quest.addAll(questtmp);
                            questID.addAll(questIDtmp);
                            q1 = new String[quest.size()];
                            q2 = new String[quest.size()];
                            q3 = new String[quest.size()];

                            q4 = new String[quest.size()];
                            q5 = new String[quest.size()];
                            q6 = new String[quest.size()];

                            q7 = new String[quest.size()];
                            q8 = new String[quest.size()];
                            q9 = new String[quest.size()];

                            for (int i = 0; i < quest.size(); i++) {
                                q1[i] = quest.get(i).nom;
                                q3[i] = "" + quest.get(i).upvote;
                                q4[i]= "" + quest.get(i).repondu;
                                q5[i]= "" + quest.get(i).downvote;
                                q6[i] = "" + quest.get(i).upvoteProf;
                                q7[i] = quest.get(i).titre;
                                q8[i] = quest.get(i).date;
                                q9[i] = "" + quest.get(i).type;
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuestionStud.this, android.R.layout.simple_list_item_1, q1);
                            ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                            ArrayList<String> list2 = new ArrayList(questID);
                            ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                            ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                            ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                            ArrayList<String> list6 = new ArrayList(Arrays.asList(q6));
                            ArrayList<String> list7 = new ArrayList(Arrays.asList(q7));
                            ArrayList<String> list8 = new ArrayList(Arrays.asList(q8));
                            ArrayList<String> list9 = new ArrayList(Arrays.asList(q9));

                            CustomAdapterStud adapt = new CustomAdapterStud(list1, list2, list3,list4 ,list5, list6, list7, list8, list9, DisplayQuestionStud.this);
                            myListView.setAdapter(adapt);
                            adapt.notifyDataSetChanged();
                        }
                        else {
                            updateList();
                        }
                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayQuestionStud.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(questID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
                ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
                ArrayList<String> list6 = new ArrayList(Arrays.asList(q6));
                ArrayList<String> list7 = new ArrayList(Arrays.asList(q7));
                ArrayList<String> list8 = new ArrayList(Arrays.asList(q8));
                ArrayList<String> list9 = new ArrayList(Arrays.asList(q9));

                CustomAdapterStud adapt = new CustomAdapterStud(list1, list2, list3,list4 ,list5, list6, list7, list8, list9, DisplayQuestionStud.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();

                //Button triBtn = (Button) findViewById(R.id.triupvote);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        questionRef.addListenerForSingleValueEvent(eventListener);
    }

    public void repondre(View v){
        Intent intent = new Intent(this, AnswerQuestion.class);
        startActivity(intent);
    }

    public void annuler() {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }

    public void goToHelpActivity(){
        Intent intent = new Intent(this, HelpQuestionStud.class);
        startActivity(intent);
    }
}
