package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConnectionActivity extends AppCompatActivity {

    EditText email;
    Button submit;
    List<User> users = User.listAll(User.class);
    String[] u1 = new String[users.size()];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        for(int i=0; i<users.size(); i++) {
            u1[i] = users.get(i).email;
        }

        submit = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.email);

        submit.setOnClickListener(
        new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(userExit(u1, email))
                {
                    Log.d("valeur de la liste ", "Demarre une nouvelle activité");
                    Intent intent = new Intent(ConnectionActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ConnectionActivity.this,
                            "l'Utilisateur: " + email.getText() + "n'existe pas",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean userExit(String[] listEmail, EditText email) {
        int exist=0;
        for(int i=0; i<listEmail.length; i++) {
            Log.d("valeur de la liste " + i + ":", u1[i]);
            if(email.getText().toString().equals(listEmail[i].toString())) {
                exist++;
            }
        }
        if(exist>0) {
            return true;
        }
        else {
            return false;
        }
    }

}
