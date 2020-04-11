package com.example.asknanterre;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentUI extends AppCompatActivity {

    Button btn;
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    TextView cours;
    Bundle b;
    String coursId;
    String nom;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentui);

        b = getIntent().getExtras();

        cours = (TextView) findViewById(R.id.txtdashboard2);
        s = cours.getText().toString();
        cours.setText(s + " (" + b.getString("name") + ") ");
        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkRed));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
        bar.setTitle("AskNanterre : Etudiant");*/

        /*btn = (Button)findViewById(R.id.stud_btn);

        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(StudentUI.this, ProfessorUI.class);
                        startActivity(intent);
                    }
                });*/

        /*bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        itemToHide = bottomNavigationView.getMenu();
        itemToHide.findItem(R.id.action_goStud).setVisible(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(StudentUI.this, "Recents", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                        break;
                    case R.id.action_goStud:
                        Toast.makeText(StudentUI.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_goProf:
                        //Toast.makeText(StudentUI.this, "Nearby", Toast.LENGTH_SHORT).show();
                        goToProfUIActivity();
                        break;
                }
                return true;
            }
        });*/
        coursId = b.getString("key");
        nom =  b.getString("name");
        Log.v("hfhfhfhf", coursId);
        Log.v("hfhfhfhf", nom);
    }

    /*public void displayQuestion(View v){
        Intent intent = new Intent(this, DisplayQuestion.class);
        startActivity(intent);
    }*/

    public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        b2.putString("name", nom);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void addQuestionQCM(View v){
        Intent intent = new Intent(this, AddQCM.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        b2.putString("name", nom);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void displayQuestionStud(View v){
        Intent intent = new Intent(this, DisplayQuestionStud.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        b2.putString("name", nom);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void goToMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void displayQuizz(View v) {
        Intent intent = new Intent(this, DisplayQuiz.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        b2.putString("name", nom);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void studentUI2(View v) {
        Intent intent = new Intent(this, StudentUI2.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        b2.putString("name", nom);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }
}
