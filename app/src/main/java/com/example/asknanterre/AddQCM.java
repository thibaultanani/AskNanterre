package com.example.asknanterre;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddQCM extends AppCompatActivity {

    Spinner spinner;
    String text;
    EditText edit;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addqcm);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ll = (LinearLayout)findViewById(R.id.mylinearlayout);
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Log.d("Nombre actuel", spinner.getSelectedItem().toString() );

                ll.removeAllViewsInLayout();

                Log.d("Nombre actuel", spinner.getSelectedItem() + "" );
                for(int i=0; i<Integer.parseInt(spinner.getSelectedItem().toString()); i++){
                    edit = new EditText(AddQCM.this);
                    ll.addView(edit, lp);
                }

                Log.d("Nombre d'enfants", ll.getChildCount()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Question q = new Question(name.getText().toString());
        q.type = 2;
        long id = q.save();
        Log.d("l'id de la question", id+"");

        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            EditText v1 = (EditText) ll.getChildAt(i);
            QCM qcm = new QCM(v1.getText().toString(), id);
            Log.d("l'id de la rep", id+"");
            qcm.save();
        }

        Toast.makeText(this, "la Question: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }
}