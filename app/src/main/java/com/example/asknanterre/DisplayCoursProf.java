package com.example.asknanterre;



import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class DisplayCoursProf extends AppCompatActivity {
    ListView myListView;
    EditText name;
    List<Cours> cours;
    List<String> coursID;
    List<Cours> courstmp;
    List<String> coursIDtmp;
    String[] q1;
    String[] q2;
    String[] q3;
    private DatabaseReference mCoursReference;
    ProgressBar progressBar;
    Spinner spinner;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycoursprof);

        mCoursReference = FirebaseDatabase.getInstance().getReference()
                .child("cours");
        updateList();

        spinner = (Spinner) findViewById(R.id.spinner1);

        myListView = (ListView) findViewById(R.id.myListView);

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);


        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void updateList() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference coursRef = rootRef.child("cours");
        cours = new ArrayList<Cours>();
        coursID = new ArrayList<String>();
        courstmp= new ArrayList<Cours>();
        coursIDtmp = new ArrayList<String>();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cours.clear();
                coursID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    cours.add(ds.getValue(Cours.class));
                    coursID.add(ds.getKey());
                    Log.d("TAG", product);
                }
                progressBar.setVisibility(View.GONE);

                q1 = new String[cours.size()];
                q2 = new String[cours.size()];
                q3 = new String[cours.size()];

                myListView = (ListView) findViewById(R.id.myListView);

                for (int i = 0; i < cours.size(); i++) {
                    q1[i] = cours.get(i).nom;
                    q2[i] = cours.get(i).titre;
                    q3[i] = cours.get(i).date;
                }

                edit = (EditText) findViewById(R.id.EditText01);

                edit.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) {}

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        courstmp.clear();
                        coursIDtmp.clear();
                        final int size = cours.size();
                        Log.v("taille", ""+size);
                        if(s.length()!=0){
                            for(int i=0; i<size; i++) {
                                Log.v("comparaison", cours.get(i).nom.toLowerCase() + "   " + s.toString().toLowerCase());
                                if(cours.get(i).nom.toLowerCase().contains(s.toString().toLowerCase())) {
                                    courstmp.add(cours.get(i));
                                    coursIDtmp.add(coursID.get(i));
                                    Log.v("Tour recherche",  ""+ i);
                                }
                            }
                        }

                        for(int j=0; j<courstmp.size(); j++) {
                            Log.v("questtmp", courstmp.get(j).nom + " " + coursIDtmp.get(j));
                        }

                        if(courstmp.size()!=0) {
                            cours.clear();
                            coursID.clear();
                            cours.addAll(courstmp);
                            coursID.addAll(coursIDtmp);
                            q1 = new String[cours.size()];
                            q2 = new String[cours.size()];
                            q3 = new String[cours.size()];

                            for (int i = 0; i < cours.size(); i++) {
                                q1[i] = cours.get(i).nom;
                                q2[i] = cours.get(i).titre;
                                q3[i] = cours.get(i).date;
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayCoursProf.this, android.R.layout.simple_list_item_1, q1);
                            ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                            ArrayList<String> list2 = new ArrayList(coursID);
                            ArrayList<String> list3 = new ArrayList(Arrays.asList(q2));
                            ArrayList<String> list4 = new ArrayList(Arrays.asList(q3));

                            CustomAdapterCoursProf adapt = new CustomAdapterCoursProf(list1, list2, list3, list4, DisplayCoursProf.this);
                            myListView.setAdapter(adapt);
                            adapt.notifyDataSetChanged();
                        }
                        else {
                            updateList();
                        }
                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayCoursProf.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(coursID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q2));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q3));

                CustomAdapterCoursProf adapt = new CustomAdapterCoursProf(list1, list2, list3, list4,DisplayCoursProf.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        coursRef.addListenerForSingleValueEvent(eventListener);
    }



    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}