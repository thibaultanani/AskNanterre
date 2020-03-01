package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DisplayAnswerQuestion extends AppCompatActivity {

    ListView myListView;
    EditText name;
    List<Answer> answers;
    SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> data;
    List<Answer> answ = Answer.listAll(Answer.class);
    String[] r1 = new String [answ.size()];
    String[] r2 = new String [answ.size()];
    String[] r3 = new String [answ.size()];

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_displayanswerquestion);

        myListView = (ListView) findViewById(R.id.myListView);

        for(Answer r: answ){
            Log.d("gfgfgfgf", r.toString() + r.getNom() + " " );
        }

        for (int i=0; i < answ.size(); i++){
            r1[i] = answ.get(i).nom;
            r2[i] = answ.get(i).getId().toString();
            r3[i] = "" + answ.get(i) ;
        }

        for (int i=0; i<r2.length; i++){
            Log.d("valeur de la liste" + i + ":", r2[i]);
        }

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, r2);
        ArrayList <String> list1 = new ArrayList<>(Arrays.asList(r1));
        ArrayList<String> list2 = new ArrayList<> (Arrays.asList(r2));
        ArrayList <String> list3 = new ArrayList<>(Arrays.asList(r3));
        //CustomAdapter adapt = new CustomAdapter(list1, list2, list3, this);
        //myListView.setAdapter(adapt);
       // Button showanswBtn = (Button) findViewById(R.id.showansw);

    }





    @Override
    protected void onStart(){
        super.onStart();
    }


}
