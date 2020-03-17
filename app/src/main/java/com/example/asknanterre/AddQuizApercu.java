package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddQuizApercu extends AppCompatActivity {

    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;
    String coursId;
    String bonnerep;
    ArrayList rep;
    ArrayList rep2;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    TextView edit;
    Integer dif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_apercu);

        ll = (LinearLayout)findViewById(R.id.mylinearlayout);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        b=getIntent().getExtras();
        nom= findViewById(R.id.name_quiz);
        date=findViewById(R.id.date_quiz);
        titre=findViewById(R.id.titre_quiz);


        coursId=b.getString("key");

        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));
        rep=b.getStringArrayList("rep");
        rep2=b.getStringArrayList("rep2");
        bonnerep=b.getString("bonnerep");
        dif=b.getInt("dif");

        for(int i=0;i<rep2.size();i++) {
            if(rep2.get(i).toString().equals(bonnerep)) {
                edit = new TextView(AddQuizApercu.this);
                edit.setBackgroundResource(R.drawable.edittext_bg);
                edit.setText(rep2.get(i).toString());
                lp.setMargins(0, 0, 0, 20);
                ll.addView(edit, lp);
            }
            else {
                edit = new TextView(AddQuizApercu.this);
                edit.setBackgroundResource(R.drawable.edittext_bg);
                edit.setText(rep2.get(i).toString());
                lp.setMargins(0, 0, 0, 20);
                ll.addView(edit, lp);
            }
        }

    }

    public void valider_quiz(View v) {

        TextView name =  findViewById(R.id.name_quiz);
        TextView titre= findViewById(R.id.titre_quiz);


        Normalizer n = new Normalizer();
        QuestionProf q = new QuestionProf(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date = formatter.format(date);
        q.titre = n.normalizeTitre(name.getText().toString());
        q.difficulte = dif;
        q.coursId = coursId;


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());


        //database.getReference("question").push().setValue(q);
        String id = database.getReference("questionProf").push().getKey();
        database.getReference("questionProf").child(id).setValue(q);

        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView v1 = (TextView) ll.getChildAt(i);
            if(!rep.get(i).toString().equals(bonnerep)){
                Quiz quiz = new Quiz(rep.get(i).toString(), id, false);
                database.getReference("quiz").push().setValue(quiz);
                //quiz.save();
                Log.d("valeur de la rep", "fausse");
            }
            else {
                Quiz quiz = new Quiz(rep.get(i).toString(), id, true);
                database.getReference("quiz").push().setValue(quiz);
                //quiz.save();
                Log.d("valeur de la rep", "vraie");
            }
            Log.d("l'id de la rep", id+"");
        }



        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Intent intent = new Intent(this,  ProfessorUI2.class);
        intent.putExtras(b2);
        startActivity(intent);
    }
}
