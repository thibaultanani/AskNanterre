package com.example.asknanterre;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddQuiz extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    String text;
    String text2;
    EditText edit;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquiz);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();

        final List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("1");
        spinnerArray.add("2");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ll = (LinearLayout)findViewById(R.id.mylinearlayout);
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Log.d("Nombre actuel", spinner.getSelectedItem().toString() );

                ll.removeAllViewsInLayout();
                spinnerArray.clear();


                Log.d("Nombre actuel", spinner.getSelectedItem() + "" );
                for(int i=0; i<Integer.parseInt(spinner.getSelectedItem().toString()); i++){
                    edit = new EditText(AddQuiz.this);
                    edit.setBackgroundResource(R.drawable.edittext_bg);
                    lp.setMargins(0, 0, 0, 20);
                    ll.addView(edit, lp);
                    spinnerArray.add(""+(i+1));

                }

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                final Spinner sItems = (Spinner) findViewById(R.id.spinner2);
                sItems.setAdapter(adapter);

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
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        text2 = spinner2.getSelectedItem().toString();

        QuestionProf q = new QuestionProf(name.getText().toString());
        long id = q.save();
        Log.d("l'id de la question", id+"");

        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            EditText v1 = (EditText) ll.getChildAt(i);
            if(Integer.parseInt(text2)!=(i+1)){
                Quiz quiz = new Quiz(v1.getText().toString(), id, false);
                quiz.save();
                Log.d("valeur de la rep", "fausse");
            }
            else {
                Quiz quiz = new Quiz(v1.getText().toString(), id, true);
                quiz.save();
                Log.d("valeur de la rep", "vraie");
            }
            Log.d("l'id de la rep", id+"");
        }

        Toast.makeText(this, "la Question: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }
}