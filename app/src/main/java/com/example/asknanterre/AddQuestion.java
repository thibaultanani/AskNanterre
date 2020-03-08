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

public class AddQuestion extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);

        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkRed));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
        bar.setTitle("AskNanterre : Etudiant");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        itemToHide = bottomNavigationView.getMenu();
        itemToHide.findItem(R.id.action_goStud).setVisible(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(AddQuestion.this, "Recents", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                        break;
                    case R.id.action_goStud:
                        Toast.makeText(AddQuestion.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_goProf:
                        //Toast.makeText(AddQuestion.this, "Nearby", Toast.LENGTH_SHORT).show();
                        goToProfUIActivity();
                        break;
                }
                return true;
            }
        });*/
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        final Bundle b = getIntent().getExtras();
        final String coursId = b.getString("key");

        Normalizer n = new Normalizer();
        Question q = new Question(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date = formatter.format(date);
        q.titre = n.normalizeTitre(name.getText().toString());
        q.type = 1;
        q.coursId = coursId;

        //q.save();
        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("question").push().setValue(q, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference mDatabase) {
                Toast.makeText(AddQuestion.this, "User added.", Toast.LENGTH_SHORT).show();

            }
        });*/
        /*FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d(TAG, "connected");
                } else {
                    Log.d(TAG, "not connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Listener was cancelled");
            }
        });*/

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("question").push().setValue(q);


        Toast.makeText(this, "La question: \"" + name.getText() + "\" a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");

        finish();
    }

    public void annuler(View v) {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }
}

