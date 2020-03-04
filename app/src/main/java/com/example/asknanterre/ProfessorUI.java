package com.example.asknanterre;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import org.w3c.dom.Text;

public class ProfessorUI extends AppCompatActivity {

    Button btn;
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    Bundle b;
    String coursId;
    TextView cours;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professorui);

        /*Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorDarkBlue));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlue)));

        bar.setTitle("AskNanterre : Professeur");*/
        /*btn = (Button)findViewById(R.id.prof_btn);

        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(ProfessorUI.this, StudentUI.class);
                        startActivity(intent);
                    }
                });*/

        /*bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        itemToHide = bottomNavigationView.getMenu();
        itemToHide.findItem(R.id.action_goProf).setVisible(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(ProfessorUI.this, "Recents", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                        break;
                    case R.id.action_goStud:
                        //Toast.makeText(ProfessorUI.this, "Favorites", Toast.LENGTH_SHORT).show();
                        goToStudUIActivity();
                        break;
                    case R.id.action_goProf:
                        Toast.makeText(ProfessorUI.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/
        b = getIntent().getExtras();
        coursId = b.getString("key");
        cours = (TextView) findViewById(R.id.txtdashboard2);
        s = cours.getText().toString();
        cours.setText(s + " (" + b.getString("name") + ") ");
    }

    public void addQuiz(View v){
        Intent intent = new Intent(this, AddQuiz.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void displayQuestionProf(View v){
        Intent intent = new Intent(this, DisplayQuestionProf.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void displayQuizProf(View v){
        Intent intent = new Intent(this, DisplayQuizProf.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void goToMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
