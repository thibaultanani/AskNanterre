package com.example.asknanterre;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class AddQuestionApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;
    String coursId;
    String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_apercu);

        b=getIntent().getExtras();
        nom= findViewById(R.id.name_question);
        date=findViewById(R.id.date_question);
        titre=findViewById(R.id.titre_question);

        coursId=b.getString("coursid");
        name2=b.getString("name");
        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));

        ActionBar ab = getSupportActionBar();
        ab.setSubtitle(titre.getText().toString() + getString(R.string.apercu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.action_back:
            //add the function to perform here
            annuler();
            return(true);
        case R.id.action_home:
            //add the function to perform here
            goToMainActivity();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
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

        Toasty.success(this, getString(R.string.la_question) + name.getText().toString() + getString(R.string.a_ete_ajoutee), Toast.LENGTH_LONG).show();

        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        b2.putString("name",name2);
        Intent intent = new Intent(this,  StudentUI.class);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }

    public void annuler() {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
