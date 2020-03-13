package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAnswer2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    Spinner spinner;
    EditText edit;
    EditText editText;
    TextView textView;
    TextView question;
    String text;
    DatabaseReference ref;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addanswerclose);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");

        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));

        editText = (EditText) findViewById(R.id.lname);
        textView = (TextView) findViewById(R.id.ltext);
        coursId= b.getString("idcours");

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("qcm");

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if(ds.getValue(QCM.class).questionId.equals(questionId)) {
                        spinnerArray.add(ds.getValue(QCM.class).rep);
                        Log.d("HEY", product);
                    }
                    Log.d("TAG", product);
                }
                spinnerArray.add("Réponse personalisée");
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(position!=(spinnerArray.size()-1)) {
                    textView.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                }
                else {
                    textView.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);
        TextView text = (TextView) findViewById(R.id.ltext);
        String s;

        Bundle b = getIntent().getExtras();
        String questionId;
        questionId = b.getString("key");

        Answer a;
        if(editText.getVisibility() != View.VISIBLE) {
            a = new Answer(spinner.getSelectedItem().toString(), questionId);
            s = spinner.getSelectedItem().toString();
        }
        else {
            a = new Answer(name.getText().toString(), questionId);
            s = name.getText().toString();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("answer").push().setValue(a);

        DatabaseReference ref;
        ref=FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
        Map<String,Object> questionMap = new HashMap<String,Object>();
        questionMap.put("repondu", true);
        ref.updateChildren(questionMap);

        Toast.makeText(this, getString(R.string.la_reponse) + s + getString(R.string.a_ete_ajoutee), Toast.LENGTH_LONG).show();

        name.setText("");

        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Intent intent = new Intent(this, DisplayQuestionProf.class);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }
}
