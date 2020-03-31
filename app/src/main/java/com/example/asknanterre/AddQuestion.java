package com.example.asknanterre;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class AddQuestion extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);

        ActionBar ab = getSupportActionBar();
        ab.setSubtitle(getString(R.string.poser_une_question_ouverte));
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

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        final Bundle b = getIntent().getExtras();
        final String coursId = b.getString("key");

        if (name.getText().toString().isEmpty()) {
            Toasty.error(this, getString(R.string.Le_nom_de_la_question), Toast.LENGTH_LONG).show();
        }
        else {
            Normalizer n = new Normalizer();
            Question q = new Question(n.normalizeNom(name.getText().toString()));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            q.date = formatter.format(date);
            q.titre = n.normalizeTitre(name.getText().toString());
            q.type = 1;
            q.coursId = coursId;


            Bundle b2 = new Bundle();
            b2.putString("nom", q.nom);
            b2.putString("titre", q.titre);
            b2.putString("date", q.date);
            b2.putString("coursid", coursId);
            Intent intent = new Intent(this, AddQuestionApercu.class);
            intent.putExtras(b2); //Put your id to your next Intent
            startActivity(intent);
        }
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

