package com.example.asknanterre;



import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class DisplayCoursProf extends AppCompatActivity {
    SwipeMenuListView  myListView;
    EditText name;
    List<Cours> cours;
    List<String> coursID;
    List<Cours> courstmp;
    List<String> coursIDtmp;
    String[] q1;
    String[] q2;
    String[] q3;
    Boolean[] q4;
    private DatabaseReference mCoursReference;
    ProgressBar progressBar;
    Spinner spinner;
    EditText edit;
    CustomAdapterCoursProf adapt;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycoursprof);

        mCoursReference = FirebaseDatabase.getInstance().getReference()
                .child("cours");
        updateList();

        spinner = (Spinner) findViewById(R.id.spinner1);

        myListView = (SwipeMenuListView) findViewById(R.id.myListView);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        myListView.setEmptyView(emptyText);

        final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerArray.add(getString(R.string.trier_par_date_asc));
        spinnerArray.add(getString(R.string.trier_par_date_des));
        spinnerArray.add(getString(R.string.trier_par_visibilite_asc));
        spinnerArray.add(getString(R.string.trier_par_visibilite_des));

        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);

        ActionBar ab = getSupportActionBar();
        ab.setSubtitle(getString(R.string.liste_des_cours));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.action_back:
            //add the function to perform here
            annuler();
            return(true);
        case R.id.action_home:
            //add the function to perform here
            goToMainActivity();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void trier(View v, final int position) {
        myListView = (SwipeMenuListView) findViewById(R.id.myListView);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        myListView.setEmptyView(emptyText);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference coursRef = rootRef.child("cours");
        cours = new ArrayList<Cours>();
        coursID = new ArrayList<String>();
        courstmp= new ArrayList<Cours>();
        coursIDtmp = new ArrayList<String>();

        String s;
        if(position == 2 || position == 3) {
            s = "visible";
        }
        else {
            s = "";
        }

        Query q;
        if(!s.isEmpty()) {
            q = coursRef.orderByChild(s);
        }
        else {
            q = coursRef;
        }

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cours.clear();
                coursID.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    cours.add(ds.getValue(Cours.class));
                    coursID.add(ds.getKey());
                    Log.d("TAG", product);
                }
                progressBar.setVisibility(View.GONE);

                if(position != 3 && position != 0) {
                    Collections.reverse(cours);
                    Collections.reverse(coursID);
                }

                q1 = new String[cours.size()];
                q2 = new String[cours.size()];
                q3 = new String[cours.size()];
                q4 = new Boolean[cours.size()];


                for (int i = 0; i < cours.size(); i++) {
                    q1[i] = cours.get(i).nom;
                    q2[i] = cours.get(i).titre;
                    q3[i] = cours.get(i).date;
                    q4[i] = cours.get(i).visible;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayCoursProf.this, android.R.layout.simple_list_item_1, q1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                ArrayList<String> list2 = new ArrayList(coursID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q2));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q3));
                ArrayList<Boolean> list5 = new ArrayList(Arrays.asList(q4));

                adapt = new CustomAdapterCoursProf(list1, list2, list3, list4, list5, DisplayCoursProf.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                q4 = new Boolean[cours.size()];

                myListView = (SwipeMenuListView) findViewById(R.id.myListView);
                TextView emptyText = (TextView)findViewById(android.R.id.empty);
                myListView.setEmptyView(emptyText);

                for (int i = 0; i < cours.size(); i++) {
                    q1[i] = cours.get(i).nom;
                    q2[i] = cours.get(i).titre;
                    q3[i] = cours.get(i).date;
                    q4[i] = cours.get(i).visible;
                }

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // your code here
                        trier(selectedItemView, position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });

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
                            q4 = new Boolean[cours.size()];

                            for (int i = 0; i < cours.size(); i++) {
                                q1[i] = cours.get(i).nom;
                                q2[i] = cours.get(i).titre;
                                q3[i] = cours.get(i).date;
                                q4[i] = cours.get(i).visible;
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayCoursProf.this, android.R.layout.simple_list_item_1, q1);
                            ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                            ArrayList<String> list2 = new ArrayList(coursID);
                            ArrayList<String> list3 = new ArrayList(Arrays.asList(q2));
                            ArrayList<String> list4 = new ArrayList(Arrays.asList(q3));
                            ArrayList<Boolean> list5 = new ArrayList(Arrays.asList(q4));

                            adapt = new CustomAdapterCoursProf(list1, list2, list3, list4, list5, DisplayCoursProf.this);
                            myListView.setAdapter(adapt);
                            adapt.notifyDataSetChanged();
                        }
                        else {
                            updateList();
                        }
                    }
                });

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayCoursProf.this, android.R.layout.simple_list_item_1, q1);
                final ArrayList<String> list1 = new ArrayList(Arrays.asList(q1));
                final ArrayList<String> list2 = new ArrayList(coursID);
                ArrayList<String> list3 = new ArrayList(Arrays.asList(q2));
                ArrayList<String> list4 = new ArrayList(Arrays.asList(q3));
                final ArrayList<Boolean> list5 = new ArrayList(Arrays.asList(q4));

                adapt = new CustomAdapterCoursProf(list1, list2, list3, list4, list5, DisplayCoursProf.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();


                SwipeMenuCreator creator = new SwipeMenuCreator() {

                    @Override
                    public void create(SwipeMenu menu) {
                        // create "open" item
                        SwipeMenuItem visibilityItem = new SwipeMenuItem(
                                getApplicationContext());
                        // set item background
                        visibilityItem.setBackground(new ColorDrawable(Color.rgb(44, 34, 240)));
                        // set item width
                        visibilityItem.setWidth(170);
                        // set item title
                        visibilityItem.setIcon(R.drawable.ic_visibility);
                        // add to menu
                        menu.addMenuItem(visibilityItem);

                        // create "delete" item
                        SwipeMenuItem deleteItem = new SwipeMenuItem(
                                getApplicationContext());
                        // set item background
                        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                                0x3F, 0x25)));
                        // set item width
                        deleteItem.setWidth(170);
                        // set a icon
                        deleteItem.setIcon(R.drawable.ic_delete);
                        // add to menu
                        menu.addMenuItem(deleteItem);
                    }
                };

                myListView.setMenuCreator(creator);

                myListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                        switch (index) {
                            case 0:
                                ref=FirebaseDatabase.getInstance().getReference().child("cours").child(list2.get(position));
                                Map<String,Object> coursMap = new HashMap<String,Object>();
                                if(!list5.get(position)) {
                                    coursMap.put("visible", true);
                                    ref.updateChildren(coursMap);
                                    Toasty.success(DisplayCoursProf.this, getString(R.string.le_cours) + list1.get(position) + getString(R.string.est_devenu_invisible), Toast.LENGTH_LONG).show();
                                }
                                else {
                                    coursMap.put("visible", false);
                                    ref.updateChildren(coursMap);
                                    Toasty.success(DisplayCoursProf.this, getString(R.string.le_cours) + list1.get(position) + getString(R.string.est_devenu_visible), Toast.LENGTH_LONG).show();
                                }
                                list1.remove(position);
                                adapt.notifyDataSetChanged();
                                updateList();
                                break;
                            case 1:
                                // delete
                                ref=FirebaseDatabase.getInstance().getReference().child("cours").child(list2.get(position));
                                ref.removeValue();
                                Toasty.success(DisplayCoursProf.this, getString(R.string.le_cours) + list1.get(position) + getString(R.string.a_ete_supprime), Toast.LENGTH_LONG).show();
                                list1.remove(position); //or some other task
                                adapt.notifyDataSetChanged();
                                updateList();
                                break;
                        }
                        // false : close the menu; true : not close the menu
                        return true;
                    }
                });
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        coursRef.addListenerForSingleValueEvent(eventListener);
    }

    public void annuler() {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}