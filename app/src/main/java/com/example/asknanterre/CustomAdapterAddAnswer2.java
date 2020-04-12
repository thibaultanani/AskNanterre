package com.example.asknanterre;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapterAddAnswer2 extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<Boolean> list3 = new ArrayList<Boolean>();

    private Context context2;
    DatabaseReference ref;
    int upvote;
    private boolean checkExists = false;
    private Answer tmp;
    private String tmpKey;
    int textlength=0;
    ListView myListView;
    private int selectedPosition = -1;
    private Activity activity;

    public CustomAdapterAddAnswer2(ArrayList<String> list1, ArrayList<String> list2, ArrayList<Boolean> list3, Activity context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.activity = context;
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
        CustomAdapterAddAnswer2.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_addanswer2, parent, false);
            // get all UI view
            holder = new CustomAdapterAddAnswer2.ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (CustomAdapterAddAnswer2.ViewHolder) convertView.getTag();
        }

        holder.checkBox.setTag(position); // This line is important.

        holder.checkBoxName.setText(list1.get(position));
        if (position == selectedPosition) {
            holder.checkBox.setChecked(true);
        } else holder.checkBox.setChecked(false);

        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox, position));

        return convertView;
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedPosition = position;
                } else {
                    selectedPosition = -1;
                }
                ((AddAnswer2)activity).textVisibility(position, checkBox.isChecked());
                notifyDataSetChanged();
            }
        };
    }


    private static class ViewHolder {
        private TextView checkBoxName;
        private CheckBox checkBox;

        public ViewHolder(View v) {
            checkBox = (CheckBox) v.findViewById(R.id.list_view_item_checkbox);
            checkBoxName = (TextView) v.findViewById(R.id.list_view_item_text);
        }
    }
}

