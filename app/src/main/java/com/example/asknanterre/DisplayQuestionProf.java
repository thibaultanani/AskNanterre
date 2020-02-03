package com.example.asknanterre;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DisplayQuestionProf extends AppCompatActivity {

    SwipeMenuListView myListView;
    EditText name;
    List<Question> questions;
    ArrayList<HashMap<String, String>> data;
    List<Question> quest;
    String[] q1;
    String[] q2;
    String[] q3;
    ArrayList<String> list1;
    ArrayList<String> list2;
    ArrayList<String> list3;
    ArrayAdapter<String> adapter;
    CustomAdapterProf adapt;
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestionprof);

        updateList();

        Button triBtn=(Button) findViewById(R.id.triupvote);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem validateItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                validateItem.setBackground(new ColorDrawable(Color.rgb(0x33, 0xff,
                        0x57)));
                // set item width
                validateItem.setWidth(170);
                // set item title
                validateItem.setIcon(R.drawable.ic_validate);
                // add to menu
                menu.addMenuItem(validateItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        myListView.setMenuCreator(creator);

        myListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                        question.valide = true;
                        question.save();
                        Toast.makeText(DisplayQuestionProf.this, "la question: " + list1.get(position) + " a été validée", Toast.LENGTH_LONG).show();
                        list1.remove(position);
                        adapt.notifyDataSetChanged();
                        updateList();
                        break;
                    case 1:
                        // delete
                        question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                        question.delete();
                        Toast.makeText(DisplayQuestionProf.this, "la question: " + list1.get(position) + " a été supprimée", Toast.LENGTH_LONG).show();
                        list1.remove(position); //or some other task
                        adapt.notifyDataSetChanged();
                        updateList();
                        break;
                }
                // false : close the menu; true : not close the menu
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

    public void trier(View v) {
        myListView = (SwipeMenuListView) findViewById(R.id.myListView);

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
        CustomAdapterProf adapt = new CustomAdapterProf(list1, list2, list3, this);
        myListView.setAdapter(adapt);

    }

    public void updateList() {
        quest = Question.find(Question.class, "valide = 0");
        q1 = new String[quest.size()];
        q2 = new String[quest.size()];
        q3 = new String[quest.size()];

        myListView = (SwipeMenuListView) findViewById(R.id.myListView);

        for(int i=0; i<quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        list1 = new ArrayList( Arrays.asList(q1));
        list2 = new ArrayList( Arrays.asList(q2));
        list3 = new ArrayList( Arrays.asList(q3));
        adapt = new CustomAdapterProf(list1, list2, list3,this);
        myListView.setAdapter(adapt);
    }

    public void repondre(View v){
        Intent intent = new Intent(this, AnswerQuestion.class);
        startActivity(intent);
    }

}
