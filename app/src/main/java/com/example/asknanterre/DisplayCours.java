package com.example.asknanterre;

import android.os.Bundle;
import android.util.Log;
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

public class DisplayCours extends AppCompatActivity {

    ListView myListView2;
    ArrayList<HashMap<String, String>> data;
    List<Cours> cours = Cours.listAll(Cours.class);
    String[] q1 = new String[cours.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycours);

        myListView2 = (ListView) findViewById(R.id.myListViewCours);


        /*for (Cours c: cours) {
            Log.d("gfgfgfgf", c.toString() + "" + c.getNom() );
        }*/

        for(int i=0; i<cours.size(); i++) {
            q1[i] = cours.get(i).nom;
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, q1);
        myListView2.setAdapter(adapter);
        ArrayList<String> list1 = new ArrayList( Arrays.asList(q1));
        CustomAdapter adapt = new CustomAdapter(list1,this);
        myListView2.setAdapter(adapt);




    }

    @Override
    protected void onStart() {
        super.onStart();
        //show();
    }

}

/*public class DisplayQuestion extends AppCompatActivity {

    ListView myListView;
    List<Question> quest = Question.listAll(Question.class);
    String[] q1 = new String[quest.size()];
    String[] q2 = new String[quest.size()];
    String[] q3 = new String[quest.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayquestion);

        myListView = (ListView) findViewById(R.id.myListView);

        //Collections.sort(quest, new UpvoteSorter());
        for (Question q : quest) {
            Log.d("gfgfgfgf", q.toString() + "" + q.getNom() + "" + q.getUpvote());
        }

        for (int i = 0; i < quest.size(); i++) {
            q1[i] = quest.get(i).nom;
            q2[i] = quest.get(i).getId().toString();
            q3[i] = "" + quest.get(i).upvote;
        }

        for (int i = 0; i < q2.length; i++) {
            Log.d("valeur de la liste " + i + ":", q2[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q1);
        ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
        ArrayList<String> list2 = new ArrayList(Arrays.asList(q2));
        ArrayList<String> list3 = new ArrayList(Arrays.asList(q3));
        CustomAdapter adapt = new CustomAdapter(list1, list2, list3, this);
        myListView.setAdapter(adapt);
    }
}

*/




