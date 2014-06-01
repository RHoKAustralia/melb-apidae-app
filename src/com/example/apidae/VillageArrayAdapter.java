package com.example.apidae;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apidae.domain.Village;

public class VillageArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final Village[] villages;

    public VillageArrayAdapter(Context context, String[] values, Village[] villages) {
        super(context, R.layout.view_village_list, values);
        this.context = context;
        this.villages = villages;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.view_village_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rank);
        imageView.setImageResource(resourceForRank(position));
        textView.setText(villages[position].getName());

        return rowView;
    }

    private int resourceForRank(int position) {
        int[] resources = new int[] {R.drawable.badge1, R.drawable.badge2, R.drawable.badge3, R.drawable.badge4};
        return resources[position];
    }
}