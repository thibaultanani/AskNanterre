package com.example.asknanterre;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.name);
        EditText firstname = (EditText) findViewById(R.id.firstname);
        EditText email = (EditText) findViewById(R.id.email);

        if(checkEmailUnique(email)) {
            User u = new User(name.getText().toString(), firstname.getText().toString(), email.getText().toString());
            u.save();

            Toast.makeText(this, "l'Utilisateur: " + name.getText() + " " +
                    firstname.getText() + " " + email.getText() +
                    " a été ajoutée", Toast.LENGTH_LONG).show();

            name.setText("");
            firstname.setText("");
            email.setText("");
        }
        else {
            Toast.makeText(this, "l'email: " + email.getText() +
                    " est déjà prit", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkEmailUnique(EditText email) {
        List<User> users = User.listAll(User.class);
        String[] u1 = new String[users.size()];

        for(int i=0; i<users.size(); i++) {
            u1[i] = users.get(i).email;
        }

        int exist=0;
        for(int i=0; i<u1.length; i++) {
            Log.d("valeur de la liste " + i + ":", u1[i]);
            if(email.getText().toString().equals(u1[i].toString())) {
                exist++;
            }
        }
        if(exist>0) {
            return false;
        }
        else {
            return true;
        }
    }
}
