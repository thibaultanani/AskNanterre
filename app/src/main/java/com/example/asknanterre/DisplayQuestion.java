package com.example.asknanterre;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        myListView = (ListView) findViewById(R.id.myListView);

        for(int i=0; i<quest.size(); i++) {
            q1[i] = quest.get(i).nom;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        myListView.setAdapter(adapter);

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
}
