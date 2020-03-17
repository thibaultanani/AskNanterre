package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddQCMApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;
    String coursId;
    ArrayList rep;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    TextView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qcmapercu);
        ll = (LinearLayout)findViewById(R.id.mylinearlayout);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        b=getIntent().getExtras();
        nom= findViewById(R.id.name_qcm);
        date=findViewById(R.id.date_qcm);
        titre=findViewById(R.id.titre_qcm);


        coursId=b.getString("key");

        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));
        rep=b.getStringArrayList("rep");

        for(int i=0;i<rep.size();i++) {
            edit = new TextView(AddQCMApercu.this);
            edit.setBackgroundResource(R.drawable.edittext_bg);
            edit.setText(rep.get(i).toString());
            lp.setMargins(0, 0, 0, 20);
            ll.addView(edit, lp);
        }



    }

    public void valider_question(View v) {

        TextView name =  findViewById(R.id.name_qcm);
        TextView titre= findViewById(R.id.titre_qcm);


        Normalizer n = new Normalizer();
        Question q = new Question(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date =formatter.format(date);
        q.titre = n.normalizeTitre(titre.getText().toString());
        q.type=2;
        q.coursId = coursId;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());


        //Push la question fermée
        String id = database.getReference("question").push().getKey();
        database.getReference("question").child(id).setValue(q);

        //Recuperer les questions et push les questions
        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView v1 = (TextView) ll.getChildAt(i);
            QCM qcm = new QCM(v1.getText().toString(), id);
            database.getReference("qcm").push().setValue(qcm);
            Log.d("l'id de la rep", id);
        }



        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Intent intent = new Intent(this,  StudentUI.class);
        intent.putExtras(b2);
        startActivity(intent);
    }
}
