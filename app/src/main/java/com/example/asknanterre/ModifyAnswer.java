package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ModifyAnswer extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    DatabaseReference ref;
    EditText name;
    TextView question;
    Bundle b;
    String questionId;
    String AnswerId;
    String nameAnswer;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyansweropen);

        b = getIntent().getExtras();
        name = (EditText) findViewById(R.id.lname);
        name.setText(b.getString("nameAnswer"));

        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));
        coursId= b.getString("idcours");

        ActionBar ab = getSupportActionBar();
        Normalizer n = new Normalizer();
        ab.setSubtitle(n.normalizeTitre(b.getString("name")));
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

    public void modifier(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        if (name.getText().toString().isEmpty()) {
            Toasty.error(this, getString(R.string.Le_nom_de_la_reponse), Toast.LENGTH_LONG).show();
        }
        else {
            Bundle b = getIntent().getExtras();
            questionId = b.getString("key");
            AnswerId = b.getString("keyAnswer");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            Log.v("Exemple", database.toString());

            ref = FirebaseDatabase.getInstance().getReference().child("answer").child(AnswerId);
            Map<String, Object> answerMap = new HashMap<String, Object>();
            answerMap.put("nom", name.getText().toString());
            ref.updateChildren(answerMap);
            Toast.makeText(this, getString(R.string.la_reponse) + name.getText() + getString(R.string.a_ete_mise_a_jour), Toast.LENGTH_LONG).show();
            Bundle b2 = new Bundle();
            b2.putString("key", coursId);
            Intent intent = new Intent(this, DisplayQuestionProf.class);
            intent.putExtras(b2);
            startActivity(intent);
            //name.setText("");
        }
    }

    public void supprimer(View v) {
        EditText name = (EditText) findViewById(R.id.lname);

        Bundle b = getIntent().getExtras();
        questionId = b.getString("key");
        AnswerId = b.getString("keyAnswer");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("answer").child(AnswerId);
        ref.removeValue();

        DatabaseReference ref2;
        ref2=FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
        Map<String,Object> questionMap = new HashMap<String,Object>();
        questionMap.put("repondu", false);
        ref2.updateChildren(questionMap);

        Toasty.success(this, getString(R.string.la_reponse) + name.getText() + getString(R.string.a_ete_supprimee), Toast.LENGTH_LONG).show();
        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Log.v("valeur du cours Id", coursId);
        Intent intent = new Intent(this, DisplayQuestionProf.class);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }

    public void annuler() {
        finish();
    }

}
