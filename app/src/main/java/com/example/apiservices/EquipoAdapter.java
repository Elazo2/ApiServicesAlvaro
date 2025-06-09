package com.example.apiservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EquipoAdapter extends ArrayAdapter<Competition>{

    public EquipoAdapter(@NonNull Context context, @NonNull ArrayList<Competition> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Competition comp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_equipos, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.txtid);
        TextView compet = (TextView) convertView.findViewById(R.id.txtcomp);
        TextView area = (TextView) convertView.findViewById(R.id.txtarea);
        TextView code = (TextView) convertView.findViewById(R.id.txtcode);
        // Populate the data into the template view using the data object
        id.setText(comp.getId());
        compet.setText(comp.getName());
        area.setText(comp.getArea());
        code.setText(comp.getCode());
        // Return the completed view to render on screen
        return convertView;
    }
}
