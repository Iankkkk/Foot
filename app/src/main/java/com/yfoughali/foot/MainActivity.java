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

public class MainActivity extends AppCompatActivity {

    private TextView text, text2;
    private  RequestQueue mQueue;
    private Button buttonL1, buttonSA, buttonLIGA, buttonBL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        text2 = findViewById(R.id.text2);
        mQueue = Volley.newRequestQueue(this);
        buttonL1 = findViewById(R.id.buttonL1);
        buttonSA = findViewById(R.id.buttonSA);
        buttonLIGA = findViewById(R.id.buttonLIGA);
        buttonBL = findViewById(R.id.buttonBL);

        buttonL1.setOnClickListener(L1listener);
        buttonSA.setOnClickListener(SAlistener);
        buttonLIGA.setOnClickListener(LIGAlistener);
        buttonBL.setOnClickListener(BLlistener);

        affichageClassementButeurs();


    }

    private void affichageClassementButeurs()
    {
        String url = "https://api.football-data.org/v2/competitions/PL/scorers";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("scorers");


                        for(int i = 0; i < 10; i++)
                        {
                            JSONObject buteurs = jsonArray.getJSONObject(i);
                            JSONObject player = buteurs.getJSONObject("player");
                            String namePlayer = player.getString("name");
                            JSONObject team = buteurs.getJSONObject("team");
                            String nameTeam = team.getString("name");
                            int nbGoals = buteurs.getInt("numberOfGoals");
                            text.append(namePlayer + "  "+ nameTeam +"  " +nbGoals + "\n");
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
            public Map getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                String apiKey = BuildConfig.ApiKey; // securisation de la clé d'API, ajoutée dans gradle.properties et build.gradle
                headers.put("X-Auth-Token", apiKey);
                return headers;

            }
        };

        mQueue.add(request);
    }

    private View.OnClickListener L1listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent L1Activity = new Intent(MainActivity.this, L1Activity.class);
            startActivity(L1Activity);

        }
    };

    private View.OnClickListener SAlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent SAActivity = new Intent(MainActivity.this, SAActivity.class);
            startActivity(SAActivity);

        }
    };

    private View.OnClickListener LIGAlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent LIGAActivity = new Intent(MainActivity.this, LIGAActivity.class);
            startActivity(LIGAActivity);

        }
    };

    private View.OnClickListener BLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent BLActivity = new Intent(MainActivity.this, BLActivity.class);
            startActivity(BLActivity);

        }
    };

}



