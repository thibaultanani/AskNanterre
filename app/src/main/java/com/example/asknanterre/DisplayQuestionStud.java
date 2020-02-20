package com.example.asknanterre;

import com.example.asknanterre.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DisplayQuestionStud extends AppCompatActivity {

   // private final int position;
    ListView myListView;
    EditText name;
    List<Question> questions;
    SimpleAdapter adapter;
    ArrayList<HashMap<String, String>> data;
    List<Question> quest = Question.find(Question.class, "valide = 1");
    String[] q1 = new String[quest.size()];
    String[] q2 = new String[quest.size()];
    String[] q3 = new String[quest.size()];
    String[] q4 = new String[quest.size()];
    String[] q5 = new String[quest.size()];
    Integer[] q6 = new Integer[quest.size()];
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        myListView = (ListView) findViewById(R.id.myListView);

        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkRed));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));*/

        //Collections.sort(quest, new UpvoteSorter());
        for (Question q : quest) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for (int i = 0; i < quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
            q4[i] = "" + quest.get(i).repondu;
            q5[i] = "" + quest.get(i).downvote;
            q6[i]=  quest.get(i).upvoteProf;
        }

        for (int i = 0; i < q2.length; i++) {
            Log.d("valeur de la liste " + i + ":", q2[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
        ArrayList<String> list2 = new ArrayList(Arrays.asList(q2));
        ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
        ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
        ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
        ArrayList<Integer> list6 = new ArrayList(Arrays.asList(q5));

        CustomAdapterStud adapt = new CustomAdapterStud(list1, list2, list3,list4 ,list5,list6, this);
        myListView.setAdapter(adapt);
        Button triBtn = (Button) findViewById(R.id.triupvote);
        adapt.notifyDataSetChanged();

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

    public void trier(View v) {
        myListView = (ListView) findViewById(R.id.myListView);

        Collections.sort(quest, new UpvoteSorter());
        for (Question q : quest) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for (int i = 0; i < quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
            q4[i]= "" + quest.get(i).repondu;
            q4[i]= "" + quest.get(i).downvote;
            q6[i]=  quest.get(i).upvoteProf;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
        ArrayList<String> list2 = new ArrayList(Arrays.asList(q2));
        ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
        ArrayList<String> list4 = new ArrayList(Arrays.asList(q4));
        ArrayList<String> list5 = new ArrayList(Arrays.asList(q5));
        ArrayList<Integer> list6 = new ArrayList(Arrays.asList(q5));
        CustomAdapterStud adapt = new CustomAdapterStud(list1, list2, list3,list4,list5,list6, this);
        myListView.setAdapter(adapt);

    }

    public void repondre(View v){
        Intent intent = new Intent(this, AnswerQuestion.class);
        startActivity(intent);
    }


    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4 = new ArrayList<String>();
    private ArrayList<String> list5 = new ArrayList<String>();
    private ArrayList<Integer> list6 = new ArrayList<Integer>();
    private Context context;




   public void liker(View v) {


                }



    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }
}
