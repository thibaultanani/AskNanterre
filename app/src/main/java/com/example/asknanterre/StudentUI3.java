package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentUI3 extends AppCompatActivity {

    Button btn;
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentui3);

    }

    public void displayCours(View v){
        Intent intent = new Intent(this, DisplayCours.class);
        startActivity(intent);
    }
    public void goToMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
