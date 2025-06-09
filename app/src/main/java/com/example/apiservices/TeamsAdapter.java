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

public class TeamsAdapter extends ArrayAdapter<Teams>{

    public TeamsAdapter(@NonNull Context context, @NonNull ArrayList<Teams> teams) {
        super(context, 0, teams);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Teams teams = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_teams_competencia, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.txtidteams);
        TextView nombre = (TextView) convertView.findViewById(R.id.txtnameteams);
        TextView web = (TextView) convertView.findViewById(R.id.txtweb);
        TextView fundado = (TextView) convertView.findViewById(R.id.txtfundado);
        // Populate the data into the template view using the data object
        id.setText(teams.getId());
        nombre.setText(teams.getName());
        web.setText(teams.getWeb());
        fundado.setText(teams.getFundado());
        // Return the completed view to render on screen
        return convertView;
    }
}
