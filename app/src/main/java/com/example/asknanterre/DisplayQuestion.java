package com.example.asknanterre;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DisplayQuestion extends AppCompatActivity {

    ListView myListView;
    EditText name;
    List<Question> questions;
    SimpleAdapter adapter;
    ArrayList<HashMap<String, String>> data;
    List<Question> quest = Question.listAll(Question.class);
    String[] q1 = new String[quest.size()];
    String[] q2 = new String[quest.size()];
    String[] q3 = new String[quest.size()];

    List <Answer> answers;
    List <Answer> answ = Answer.listAll(Answer.class);
    String [] r1 = new String [answ.size()];
    String [] r2 = new String[answ.size()];
    String [] r3 = new String[answ.size()];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        myListView = (ListView) findViewById(R.id.myListView);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkRed));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
        bar.setTitle("AskNanterre : Etudiant");

        //Collections.sort(quest, new UpvoteSorter());
        for (Question q: quest) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for(int i=0; i<quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
        }

        for(int i=0; i<q2.length; i++) {
            Log.d("valeur de la liste " + i + ":", q2[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList( Arrays.asList(q1));
        ArrayList<String> list2 = new ArrayList( Arrays.asList(q2));
        ArrayList<String> list3 = new ArrayList( Arrays.asList(q3));
        CustomAdapter adapt = new CustomAdapter(list1, list2, list3, this);
        myListView.setAdapter(adapt);
        Button triBtn=(Button) findViewById(R.id.triupvote);



        /*myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), modify.class);
                TextView id =(TextView) view.findViewById(R.id.textView_id);
                intent.putExtra("id",id.getText());
                if (findViewById(R.id.Modify) != null) {
                    modifyFragment firstFragment = new modifyFragment();

                    FragmentTransaction transaction =
                            getFragmentManager().beginTransaction();
                    transaction.add(R.id.Modify, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

    public void trier(View v) {
        myListView = (ListView) findViewById(R.id.myListView);

        Collections.sort(quest, new UpvoteSorter());
        for (Question q: quest) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for(int i=0; i<quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList( Arrays.asList(q1));
        ArrayList<String> list2 = new ArrayList( Arrays.asList(q2));
        ArrayList<String> list3 = new ArrayList( Arrays.asList(q3));
        CustomAdapter adapt = new CustomAdapter(list1, list2, list3, this);
        myListView.setAdapter(adapt);

    }


    public void repondre(View v){
        Intent intent = new Intent(this, AnswerQuestion.class);
        startActivity(intent);
    }

    public void afficherReponse (View v) {
        Intent intent = new Intent (this, DisplayAnswerQuestion.class);
        startActivity(intent);
    }
/*
    public void answerQuestion2 (View v){
        EditText name = (EditText) findViewById(R.id.lname);

        Answer r = new Answer(name.getText().toString());
        r.save();

        Toast.makeText(this, "la reponse: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");    } */

}

