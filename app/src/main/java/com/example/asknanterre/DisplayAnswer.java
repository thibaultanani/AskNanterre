package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class DisplayAnswer extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    DatabaseReference ref;
    EditText name;
    Bundle b;
    String questionId;
    String AnswerId;
    String nameAnswer;
    TextView question;
    TextView reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanswer);

        b = getIntent().getExtras();

        question = (TextView) findViewById(R.id.question);
        reponse = (TextView) findViewById(R.id.answer);

        question.setText(b.getString("name"));
        reponse.setText(b.getString("nameAnswer"));

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

    public void annuler() {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
