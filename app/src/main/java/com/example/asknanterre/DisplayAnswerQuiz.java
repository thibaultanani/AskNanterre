package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class DisplayAnswerQuiz extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static final String TAG = "DisplayAnswerQuiz";
    Spinner spinner;
    EditText edit;
    EditText editText;
    TextView textView;
    TextView question;
    String text;
    DatabaseReference ref;
    DatabaseReference ref2;
    private boolean correct;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanswerquiz);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");

        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));

        editText = (EditText) findViewById(R.id.lname);
        textView = (TextView) findViewById(R.id.ltext);

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("quiz");

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if(ds.getValue(Quiz.class).questionId.equals(questionId)) {
                        spinnerArray.add(ds.getValue(Quiz.class).rep);
                        Log.d("HEY", product);
                    }
                    Log.d("TAG", product);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);

        spinner.setAdapter(adapter);

       /* spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        });*/
    }

    public void valider(View v) {

       /* EditText name = (EditText) findViewById(R.id.lname);
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


        Toast.makeText(this, "la Réponse: " + s + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");

        Intent intent = new Intent(this, DisplayQuestionProf.class);
        startActivity(intent);*/

        EditText name = (EditText) findViewById(R.id.lname);
        TextView text = (TextView) findViewById(R.id.ltext);
        String s;
        final int position;

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");
        Log.v("questID", questionId);

        //s = spinner.getSelectedItem().toString();
        spinner = (Spinner) findViewById(R.id.spinner1);
        position = spinner.getSelectedItemPosition();
        Log.v("Pos", ""+position);
        ref2 = FirebaseDatabase.getInstance().getReference().child("quiz");
        final String key2 = ref2.getKey();
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("key recu2", ds.getKey());
                    Quiz q = ds.getValue(Quiz.class);
                    if(q.questionId.equals(questionId)){
                        Log.d("key recu2.1", "tour de boucle" + i);
                        Log.d("key recu2.2", "bon id");
                        if(position == i) {
                            Log.d("ok et correct =", ""+q.correct);
                            if(q.correct) {
                                correct = true;
                            }
                            else {
                                correct = false;
                            }
                            i++;
                        }
                        else {
                            i++;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref2.addListenerForSingleValueEvent(valueEventListener2);

        ref = FirebaseDatabase.getInstance().getReference().child("questionProf").child(questionId);
        final String key = ref.getKey();
        Log.d("key originel", key);
        final Map<String,Object> questionProfMap = new HashMap<String,Object>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("key recu", ds.getKey());
                    if(correct) {
                        if (ds.getKey().equals("ncorrects")) {
                            questionProfMap.put("ncorrects", ds.getValue(Integer.class) + 1);
                            ref.updateChildren(questionProfMap);
                        }
                    }
                    else {
                        if (ds.getKey().equals("nfalses")) {
                            questionProfMap.put("nfalses", ds.getValue(Integer.class) + 1);
                            ref.updateChildren(questionProfMap);
                        }
                    }
                    Log.d("TAG", "tour de boucle");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(valueEventListener);

        if (context instanceof DisplayQuizz) {
            ((DisplayQuizz)context).updateList();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                finish();
            }
        }, 1000);
    }

    public void annuler(View v) {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }
}
