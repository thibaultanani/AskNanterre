package com.example.asknanterre;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        myListView = (ListView) findViewById(R.id.myListView);

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

        data = new ArrayList<HashMap<String, String>>();
        name=(EditText) findViewById(R.id.slname);

        adapter = new SimpleAdapter(this, data, R.layout.item,
                new String[]{"id","name"},
                new int[]   { R.id.textView_id,
                        R.id.textView_name});
        myListView.setAdapter(adapter);

    }

    private void show(){
        data.clear();
        try {
            questions = Question.find(Question.class,
                    "name = ?" + name.getText().toString()) ;

            if(questions.size() !=0) {
                Iterator i = questions.iterator();
                while(i.hasNext()){
                    Question q= (Question) i.next();
                    HashMap<String,String> item = new HashMap<String,String>();
                    item.put("name", q.getNom());
                    data.add(item);
                }

            }

        }catch(Exception e){
            Log.e ("Main : ",e.toString());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        show();
    }
}
