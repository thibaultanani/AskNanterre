package com.example.asknanterre;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DisplayQuestionProf extends AppCompatActivity {

    SwipeMenuListView myListView;
    TextView textView;
    SwipeMenuListView myListView2;
    TextView textView2;
    EditText name;
    List<Question> questions;
    ArrayList<HashMap<String, String>> data;
    List<Question> quest;
    List<Question> quest2;
    String[] q1;
    String[] q2;
    String[] q3;
    String[] q4;
    String[] q5;
    String[] q6;
    ArrayList<String> list1;
    ArrayList<String> list2;
    ArrayList<String> list3;
    ArrayList<String> list4;
    ArrayList<String> list5;
    ArrayList<String> list6;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    CustomAdapterProf adapt;
    CustomAdapterProf2 adapt2;
    Question question;
    boolean first = true;
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestionprof);

        updateList();

        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkBlue));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlue)));
        bar.setTitle("AskNanterre : Professeur");*/

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





        //SwipeMenu for second list
        SwipeMenuCreator creator2 = new SwipeMenuCreator() {

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

                // create "upvote" item
                SwipeMenuItem upvoteProfItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                upvoteProfItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                upvoteProfItem.setWidth(170);
                // set a icon
                upvoteProfItem.setIcon(R.drawable.ic_validate);
                // add to menu
                menu.addMenuItem(upvoteProfItem);




            }
        };

        myListView2.setMenuCreator(creator2);



        myListView2.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        question = Question.findById(Question.class, Integer.parseInt(list5.get(position)));
                        question.repondu = true;
                        question.save();
                        Toast.makeText(DisplayQuestionProf.this, "la question: " + list4.get(position) + " a été mise en répondue", Toast.LENGTH_LONG).show();
                        adapt.notifyDataSetChanged();
                        updateList();
                        break;

                    case 1:
                        // open
                        question = Question.findById(Question.class, Integer.parseInt(list5.get(position)));
                        question.upvoteProf = true;
                        question.save();
                        Toast.makeText(DisplayQuestionProf.this, "la question: " + list4.get(position) + " a été mise en Upvote", Toast.LENGTH_LONG).show();
                        adapt.notifyDataSetChanged();
                        updateList();
                        break;



                }
                // false : close the menu; true : not close the menu
                return true;
            }
        });




        /*bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        itemToHide = bottomNavigationView.getMenu();
        itemToHide.findItem(R.id.action_goProf).setVisible(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(DisplayQuestionProf.this, "Recents", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                        break;
                    case R.id.action_goStud:
                        //Toast.makeText(DisplayQuestionProf.this, "Favorites", Toast.LENGTH_SHORT).show();
                        goToStudUIActivity();
                        break;
                    case R.id.action_goProf:
                        Toast.makeText(DisplayQuestionProf.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

    public void trier(View v) {
        myListView2 = (SwipeMenuListView) findViewById(R.id.myListView2);

        Collections.sort(quest2, new UpvoteSorter());
        for (Question q: quest2) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for(int i=0; i<quest2.size(); i++) {
            q4[i] = quest2.get(i).nom;
            q5[i] = quest2.get(i).getId().toString();
            q6[i] = "" + quest2.get(i).upvote;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList( Arrays.asList(q4));
        ArrayList<String> list2 = new ArrayList( Arrays.asList(q5));
        ArrayList<String> list3 = new ArrayList( Arrays.asList(q6));
        CustomAdapterProf2 adapt = new CustomAdapterProf2(list1, list2, list3, this);
        myListView2.setAdapter(adapt);

    }

    public void updateList() {
        quest = Question.find(Question.class, "valide = 0");
        q1 = new String[quest.size()];
        q2 = new String[quest.size()];
        q3 = new String[quest.size()];

        quest2 = Question.find(Question.class, "valide = 1");
        q4 = new String[quest2.size()];
        q5 = new String[quest2.size()];
        q6 = new String[quest2.size()];

        myListView = (SwipeMenuListView) findViewById(R.id.myListView);
        myListView2 = (SwipeMenuListView) findViewById(R.id.myListView2);

        if(first) {
            textView = new TextView(this);
            textView.setText("Liste des questions");

            myListView.addHeaderView(textView);

            textView2 = new TextView(this);
            textView2.setText("Liste des questions validées");

            myListView2.addHeaderView(textView2);
            first = false;
        }

        for(int i=0; i<quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
        }

        for(int i=0; i<quest2.size(); i++) {
            q4[i] = quest2.get(i).nom;
            q5[i] = quest2.get(i).getId().toString();
            q6[i] = "" + quest2.get(i).upvote;
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        list1 = new ArrayList( Arrays.asList(q1));
        list2 = new ArrayList( Arrays.asList(q2));
        list3 = new ArrayList( Arrays.asList(q3));
        adapt = new CustomAdapterProf(list1, list2, list3,this);
        myListView.setAdapter(adapt);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q4);
        list4 = new ArrayList( Arrays.asList(q4));
        list5 = new ArrayList( Arrays.asList(q5));
        list6 = new ArrayList( Arrays.asList(q6));
        adapt2 = new CustomAdapterProf2(list4, list5, list6,this);
        myListView2.setAdapter(adapt2);

    }


    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToStudUIActivity(){
        Intent intent = new Intent(this, StudentUI.class);
        startActivity(intent);
    }

}
