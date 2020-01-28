package com.example.asknanterre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendFeedBack extends AppCompatActivity {

    EditText to;
    EditText subject;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        subject = (EditText)findViewById(R.id.txtSub);
        message = (EditText) findViewById(R.id.txtMsg);

        Button startBtn = (Button) findViewById(R.id.btnSend);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail(subject.getText().toString(), message.getText().toString());
            }
        });
    }

    protected void sendEmail(String subject, String message) {
        Log.i("Send email", "");
        String[] TO = {"thuny.ta@gmail.com, lourme78@gmail.com, tabit-mohamed@outlook.fr, oivuphiyeb@outlook.fr"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Envoi de mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendFeedBack.this, "Il n'y a pas d'appli de mail installé.", Toast.LENGTH_SHORT).show();
        }
    }
}
