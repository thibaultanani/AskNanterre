package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CustomAdapterCours extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private Context context;

    public CustomAdapterCours(ArrayList<String> list1, ArrayList<String> list2, Context context) {
        this.list1 = list1;
        this.list2=list2;
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
            view = inflater.inflate(R.layout.item_cours, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_titre);
        LinearLayout myLinearLayout = (LinearLayout) view.findViewById(R.id.mylinearLayoutx);
        Log.v("test",list1.get(position));
        listItemText.setText(list1.get(position));

        //Handle buttons and add onClickListeners

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentUI.class);
                Bundle b = new Bundle();
                b.putString("key", list2.get(position)); //Your id
                b.putString("name", list1.get(position));
                intent.putExtras(b); //Put your id to your next Intent
                context.startActivity(intent);
            }
        });
        return view;
    }
}
