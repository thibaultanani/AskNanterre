package com.example.asknanterre;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfessorUI2 extends AppCompatActivity {

    Button btn;
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professorui2);
    }



    public void displayCours(View v){
        Intent intent = new Intent(this, DisplayCoursProf.class);
        startActivity(intent);
    }

    public void addCours(View v){
        Intent intent = new Intent(this, AddCours.class);
        startActivity(intent);
    }

    public void goToMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }
}
