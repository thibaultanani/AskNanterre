package com.example.asknanterre;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterProf extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4 = new ArrayList<String>();
    private ArrayList<String> list5 = new ArrayList<String>();
    private Context context;



    public CustomAdapterProf(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5, Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
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
            view = inflater.inflate(R.layout.item_prof, null);
        }


        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_nom);
        //String formatedText="Nombre d'upvote: " + list3.get(position);
        //TextView listItemTextUpvote= (TextView) view.findViewById(R.id.textView_upvote);
        //listItemTextUpvote.setText(formatedText);
        listItemText.setText(list1.get(position));

        Log.v("nom", list1.get(position));
        Log.v("id", list2.get(position));
        Log.v("type", list3.get(position));
        Log.v("titre", list4.get(position));
        Log.v("date", list5.get(position));
        ImageView listItemText2 = (ImageView)view.findViewById(R.id.lock);
        if(Integer.parseInt(list3.get(position))!=1) {
            listItemText2.setImageResource(R.drawable.ic_lock);
        }
        else {
            listItemText2.setImageResource(R.drawable.ic_unlock);
        }

        TextView listItemText3 = (TextView)view.findViewById(R.id.textView_titre);
        listItemText3.setText(list4.get(position));

        TextView listItemText4 = (TextView)view.findViewById(R.id.textView_date);
        listItemText4.setText(list5.get(position));
        /*
        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.del);
        Button validateBtn= (Button ) view.findViewById(R.id.val);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.delete();
                list1.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.valide = true;
                question.save();
            }
        });*/


        return view;
    }
}
