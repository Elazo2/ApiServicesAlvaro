package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.football-data.org/v4/competitions";
    Button conectar, limpiar, listado;
    TextView json;
    ArrayList<Competition> cs = new ArrayList<Competition>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        conectar = (Button) findViewById(R.id.btnconectar);
        listado = (Button) findViewById(R.id.listado);
        limpiar = (Button) findViewById(R.id.btnlimpiar);
        json = (TextView) findViewById(R.id.txtjson);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                json.setText("");
            }
        });
        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  requestDatos();
            }
        });
        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListadoEquipos.class);
                i.putParcelableArrayListExtra("equipos", cs);
                startActivity(i);
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                          parserJson(response);
                    }
                }, new Response.ErrorListener() {
                 @Override
                  public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la conexion "+ error.getMessage(), Toast.LENGTH_LONG).show();

            }
                })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "9bd2f202597d41c6ac7335c1e666deb3");
                return headers;
            }
        };
        cola.add(peticion);
        }

    public void parserJson(JSONObject response){
        try {
            String cadena = "";
            JSONArray competitions = response.getJSONArray("competitions");
            Toast.makeText(this,"Cantidad de competencias " + competitions.length(),Toast.LENGTH_LONG).show();
            for (int i = 0 ; i<competitions.length(); i++) {
                JSONObject com = competitions.getJSONObject(i);
                String id = com.getString("id");
                String nomcomp = com.getString("name");
                String code = com.getString("code");
                JSONObject area = com.getJSONObject("area");
                String nomarea = area.getString("name");
                cadena = cadena + id + "," + nomcomp + "," + nomarea + "\n";
                Competition co = new Competition(id,nomcomp,nomarea,code);
                cs.add(co);
            }
            //Toast.makeText(getApplicationContext(),"Id = "+ cs.get(1).getId(), Toast.LENGTH_LONG).show();
            json.setText(cadena);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    }
