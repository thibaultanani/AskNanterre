package com.example.asknanterre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterQuizProf extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4 = new ArrayList<String>();
    private ArrayList<String> list5 = new ArrayList<String>();
    private ArrayList<Integer> list6 = new ArrayList<Integer>();
    private ArrayList<Integer> list7 = new ArrayList<Integer>();
    private Context context;
    private boolean checkExists = false;
    private Quiz tmp;
    private String tmpKey;
    private int tour = 0;


    public CustomAdapterQuizProf(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5, ArrayList<Integer> list6, ArrayList<Integer> list7, Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;
        this.list7 = list7;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int pos) {
        return list1.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_quiz, null);
        }


        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_nom2);
        listItemText.setText(list1.get(position));
        TextView listItemText2 = (TextView)view.findViewById(R.id.textView_titre);
        listItemText2.setText(list3.get(position));
        TextView listItemText3 = (TextView)view.findViewById(R.id.textView_date);
        listItemText3.setText(list4.get(position));
        TextView listItemText4 = (TextView)view.findViewById(R.id.textView_correct);
        listItemText4.setText("" + list6.get(position));
        TextView listItemText5 = (TextView)view.findViewById(R.id.textView_wrong);
        listItemText5.setText("" + list7.get(position));

        ImageView imageview1 = (ImageView) view.findViewById(R.id.star1);
        ImageView imageview2 = (ImageView) view.findViewById(R.id.star2);
        ImageView imageview3 = (ImageView) view.findViewById(R.id.star3);
        if(Integer.parseInt(list5.get(position))==3) {
            imageview1.setVisibility(View.VISIBLE);
            imageview2.setVisibility(View.VISIBLE);
            imageview3.setVisibility(View.VISIBLE);
        }
        else if(Integer.parseInt(list5.get(position))==2) {
            imageview1.setVisibility(View.INVISIBLE);
            imageview2.setVisibility(View.VISIBLE);
            imageview3.setVisibility(View.VISIBLE);
        }
        else {
            imageview1.setVisibility(View.INVISIBLE);
            imageview2.setVisibility(View.INVISIBLE);
            imageview3.setVisibility(View.VISIBLE);
        }
        return view;
    }
}