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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AddAnswerApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView question;
    String coursId;
    String questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer_apercu);
        b=getIntent().getExtras();
        nom= findViewById(R.id.reponse);
        question=findViewById(R.id.question);

        Log.v("hththth",b.getString("question"));
        nom.setText(b.getString("nom"));
        question.setText(b.getString("question"));
        coursId=b.getString("key");
        questionId=b.getString("questionID");


        //ActionBar ab = getSupportActionBar();
        //ab.setSubtitle(nom.getText().toString() + getString(R.string.apercu));
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

    public void valider_reponse(View v) {

        TextView name =  findViewById(R.id.reponse);
        TextView question= findViewById(R.id.question);

        Answer a = new Answer(name.getText().toString(), questionId);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("answer").push().setValue(a);

        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
        Map<String, Object> questionMap = new HashMap<String, Object>();
        questionMap.put("repondu", true);
        ref.updateChildren(questionMap);

        Toasty.success(this, getString(R.string.la_reponse) + name.getText() + getString(R.string.a_ete_ajoutee), Toast.LENGTH_LONG).show();

        name.setText("");
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        Intent intent = new Intent(this, ProfessorUI.class);
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
