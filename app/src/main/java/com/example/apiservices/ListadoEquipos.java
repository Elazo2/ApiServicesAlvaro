package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListadoEquipos extends AppCompatActivity {

    ArrayList<Competition> comp = new ArrayList<Competition>();
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_equipos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent i = getIntent();
        listado = findViewById(R.id.lstcompetitions);
        comp = i.getParcelableArrayListExtra("equipos");
        if (comp!=null && comp.size()>0){
            EquipoAdapter adapter = new EquipoAdapter(this, comp);
            listado.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Competition c = comp.get(i);
                String code = c.getCode();
               // Toast.makeText(getApplicationContext(), "Codigo competencia = " + code, Toast.LENGTH_LONG).show();
               Intent in = new Intent(getApplicationContext(), ListadoTeamsCompetencia.class);
               in.putExtra("code", code);
               startActivity(in);
            }
        });
    }
}