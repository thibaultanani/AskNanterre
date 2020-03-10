package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCoursApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours_apercu);
        b=getIntent().getExtras();
        nom= findViewById(R.id.name_cours);
        date=findViewById(R.id.date_cours);
        titre=findViewById(R.id.titre_cours);

        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));


    }

    public void valider_cours(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Normalizer n = new Normalizer();
        Cours c = new Cours(n.normalizeNom(nom.toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        c.date =formatter.format(date);
        c.titre = n.normalizeTitre(titre.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("cours").push().setValue(c);


    }
}
