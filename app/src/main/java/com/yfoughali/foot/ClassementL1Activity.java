package com.yfoughali.foot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

public class ClassementL1Activity extends AppCompatActivity {
    private TextView text, text2;
    private RequestQueue mQueue;
    private Button buttonPL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement_l1);

        text = findViewById(R.id.text);
        text2 = findViewById(R.id.text2);
        buttonPL = findViewById(R.id.buttonPL);
        mQueue = Volley.newRequestQueue(this);

        buttonPL.setOnClickListener(PLlistener);

        affichageClassement();
    }

    private void affichageClassement() {
        String url = "https://api.football-data.org/v2/competitions/FL1/standings";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("competition");
                    String nameCompetition = jsonObject.getString("name");
                    text.setText(nameCompetition);

                    for (int i = 0; i < 20; i++) {



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        })

        {
            //Methode permettant de mettre la clé d'api dans le header
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                String apiKey = BuildConfig.ApiKey; // securisation de la clé d'API, ajoutée dans gradle.properties et build.gradle
                headers.put("X-Auth-Token", apiKey);
                return headers;

            }
        };

        mQueue.add(request);
    }

    private View.OnClickListener PLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent MainActivity = new Intent(ClassementL1Activity.this, MainActivity.class);
            startActivity(MainActivity);

        }
    };
}
