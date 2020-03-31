package com.example.asknanterre;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orm.dsl.Column;

import java.util.ArrayList;
import java.util.List;

public class DisplayAnswerQuizProf extends AppCompatActivity {
    PieChart barChart;
    PieChart pieChart2;
    BarData barData;
    PieDataSet barDataSet;
    PieDataSet pieDataSet2;
    ArrayList<PieEntry> barEntries;
    ArrayList<Integer> barColors;
    ArrayList<String> barNames;
    ArrayList<Integer> barIndex;
    ArrayList<Integer> barNumber;
    ArrayList<LegendEntry> list;
    ArrayList<PieEntry> barEntries2;
    ArrayList<Integer> barColors2;
    ArrayList<String> barNames2;
    ArrayList<Integer> barIndex2;
    ArrayList<Integer> barNumber2;
    ArrayList<LegendEntry> list2;
    int total1;
    int total2;
    DatabaseReference ref;
    DatabaseReference ref2;
    Context context;
    int i = 0;
    int j = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanswerquizprof);
        barChart = findViewById(R.id.BarChart);
        pieChart2 = findViewById(R.id.BarChart2);
        getEntries();
        Log.d("Fin", "Fin");
        /*barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(barColors);
        XAxis xAxis = barChart.getXAxis();
        BarData barData = new BarData(barDataSet);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(barNames);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateXY(5000, 5000);
        barChart.invalidate();*/
        /*barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(barColors);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return barNames.get((int) value);
            }
        });
        //barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);*/
        Bundle b = getIntent().getExtras();
        ActionBar ab = getSupportActionBar();
        Normalizer n = new Normalizer();
        ab.setSubtitle(n.normalizeTitre(b.getString("name")));
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

    private void getEntries() {
        Bundle b = getIntent().getExtras();
        final String questionId;
        final String questionName;
        questionId = b.getString("key");
        questionName = b.getString("name");

        TextView listItemText = (TextView)findViewById(R.id.question);
        listItemText.setText(questionName);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("quiz");
        ref2=FirebaseDatabase.getInstance().getReference().child("questionProf");

        barEntries = new ArrayList<>();
        barColors = new ArrayList<Integer>();
        barNames = new ArrayList<String>();
        barIndex = new ArrayList<Integer>();
        barNumber = new ArrayList<Integer>();
        list = new ArrayList<LegendEntry>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    i = i + 2;
                    Quiz q = ds.getValue(Quiz.class);
                    Log.d("TAG", "HEY");
                    if(q.questionId.equals(questionId)) {
                        barEntries.add(new PieEntry(q.nbRep, q.nbRep));
                        barIndex.add(i);
                        barNumber.add(q.nbRep);
                        total1 = total1 + q.nbRep;
                        if(q.correct) {
                            barNames.add(q.rep + " " + getString(R.string.correcte));
                            barColors.add(getApplicationContext().getResources().getColor(R.color.colorAccentDark));
                        }
                        else {
                            barNames.add(q.rep);
                            barColors.add(getApplicationContext().getResources().getColor(R.color.colorRed));
                        }
                        Log.d("HEY", product);
                    }
                    Log.d("TAG", product);
                    Log.v("Entree Taille", barEntries.size()+"");
                    Log.v("Color Taille", barColors.size()+"");
                    Log.v("Name Taille", barNames.size()+"");
                }
                barDataSet = new PieDataSet(barEntries, "Les réponses");
                //barDataSet.setBarBorderWidth(0.9f);
                //barDataSet.setColors(barColors);
                barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                barDataSet.setSliceSpace(2f);
                barDataSet.setValueTextColor(Color.WHITE);
                PieData barData = new PieData(barDataSet);
                barData.setValueFormatter(new PercentFormatter());
                barData.setValueTextSize(15f);
                barData.setValueTextColor(Color.DKGRAY);
                barChart.getDescription().setText("");
                barChart.getLegend().setWordWrapEnabled(true);
                Legend legend = barChart.getLegend();
                final int [] colors = barData.getColors();
                j = 0;
                for(String s : barNames) {
                    list.add(new LegendEntry(s,Legend.LegendForm.DEFAULT,10f,2f,null, colors[j]));
                    j++;
                }
                legend.setCustom(list);
                legend.setWordWrapEnabled(true);
                barChart.setUsePercentValues(true);
                barChart.setCenterText(getString(R.string.total) + total1);
                barChart.setCenterTextSize(20f);
                barChart.setData(barData);
                //barChart.setFitBars(true);
                barChart.animateXY(1000, 1000);
                barChart.invalidate();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        barEntries2 = new ArrayList<>();
        barColors2 = new ArrayList<Integer>();
        barNames2 = new ArrayList<String>();
        barIndex2 = new ArrayList<Integer>();
        barNumber2 = new ArrayList<Integer>();
        list2 = new ArrayList<LegendEntry>();
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    i = i + 2;
                    QuestionProf q = ds.getValue(QuestionProf.class);
                    Log.d("TAG", "HEY");
                    if(ds.getKey().equals(questionId)) {
                        barEntries2.add(new PieEntry(q.getNcorrects(), q.getNcorrects()));
                        barEntries2.add(new PieEntry(q.getNfalses(), q.getNfalses()));
                        total2 = q.getNcorrects() + q.getNfalses();
                        barNumber2.add(q.getNcorrects()+q.getNcorrects());
                        barNames2.add(getString(R.string.nombre_de_bonne_reponse));
                        barNames2.add(getString(R.string.nombre_de_mauvaise_reponse));
                        barColors2.add(getApplicationContext().getResources().getColor(R.color.colorAccentDark));
                        barColors2.add(getApplicationContext().getResources().getColor(R.color.colorRed));
                        Log.d("HEY", product);
                    }
                }
                pieDataSet2 = new PieDataSet(barEntries2, "Les réponses");
                //barDataSet.setBarBorderWidth(0.9f);
                //barDataSet.setColors(barColors);
                pieDataSet2.setColors(barColors2);
                pieDataSet2.setSliceSpace(2f);
                pieDataSet2.setValueTextColor(Color.WHITE);
                PieData barData2 = new PieData(pieDataSet2);
                barData2.setValueFormatter(new PercentFormatter());
                barData2.setValueTextSize(15f);
                barData2.setValueTextColor(Color.DKGRAY);
                pieChart2.getDescription().setText("");
                pieChart2.getLegend().setWordWrapEnabled(true);
                Legend legend = pieChart2.getLegend();
                final int [] colors = barData2.getColors();
                j = 0;
                for(String s : barNames2) {
                    list2.add(new LegendEntry(s,Legend.LegendForm.DEFAULT,10f,2f,null, colors[j]));
                    j++;
                }
                legend.setCustom(list2);
                legend.setWordWrapEnabled(true);
                pieChart2.setUsePercentValues(true);
                pieChart2.setCenterText("Total:\n" + total2);
                pieChart2.setCenterTextSize(20f);
                pieChart2.setData(barData2);
                //barChart.setFitBars(true);
                pieChart2.animateXY(1000, 1000);
                pieChart2.invalidate();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        progressBar.setVisibility(View.GONE);
        Log.v("Entree Taille", barEntries.size()+"");
        Log.v("Color Taille", barColors.size()+"");
        Log.v("Name Taille", barNames.size()+"");
    }

    public void annuler() {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
