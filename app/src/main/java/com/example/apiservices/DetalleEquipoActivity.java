package com.example.apiservices;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetalleEquipoActivity extends AppCompatActivity {
    TextView txtCoach;
    ListView lstJugadores;
    ArrayAdapter<String> adapter;
    ArrayList<String> listaJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_equipo);

        txtCoach = findViewById(R.id.txtCoach);
        lstJugadores = findViewById(R.id.lstJugadores);

        int idEquipo = getIntent().getIntExtra("ID_EQUIPO", -1);

        if (idEquipo != -1) {
            obtenerInfoEquipo(idEquipo);
        }
    }

    private void obtenerInfoEquipo(int id) {
        String url = "https://api.football-data.org/v4/teams/" + id;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject coach = response.getJSONObject("coach");
                        String nombreCoach = coach.getString("name");
                        String nacionalidad = coach.getString("nationality");

                        txtCoach.setText("Coach: " + nombreCoach + "\nNacionalidad: " + nacionalidad);

                        JSONArray squad = response.getJSONArray("squad");
                        listaJugadores = new ArrayList<>();

                        for (int i = 0; i < squad.length(); i++) {
                            JSONObject jugador = squad.getJSONObject(i);
                            String nombre = jugador.getString("name");
                            String posicion = jugador.getString("position");
                            String nacionalidadJ = jugador.getString("nationality");

                            String info = nombre + " - " + posicion + " - " + nacionalidadJ;
                            listaJugadores.add(info);
                        }

                        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaJugadores);
                        lstJugadores.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", "9bd2f202597d41c6ac7335c1e666deb3");  // ← Aquí tu API KEY
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }
}
