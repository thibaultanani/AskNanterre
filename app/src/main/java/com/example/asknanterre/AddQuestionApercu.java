package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuestionApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_apercu);

        b=getIntent().getExtras();
        nom= findViewById(R.id.name_question);
        date=findViewById(R.id.date_question);
        titre=findViewById(R.id.titre_question);

        coursId=b.getString("coursid");
        Log.v("nommm",b.getString("nom"));
        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));



    }

    public void valider_question(View v) {

        TextView name =  findViewById(R.id.name_question);
        TextView titre= findViewById(R.id.titre_question);


        Normalizer n = new Normalizer();
        Question q = new Question(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date =formatter.format(date);
        q.titre = n.normalizeTitre(titre.getText().toString());
        q.type=1;
        q.coursId = coursId;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("question").push().setValue(q);

        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Intent intent = new Intent(this,  StudentUI2.class);
        intent.putExtras(b2);
        startActivity(intent);
    }
}
