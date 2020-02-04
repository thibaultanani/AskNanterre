package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddQuestion extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu itemToHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);

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
        });
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Question q = new Question(name.getText().toString());
        q.save();

        Toast.makeText(this, "la Question: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
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

