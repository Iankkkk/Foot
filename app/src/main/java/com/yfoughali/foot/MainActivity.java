package com.yfoughali.foot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private TextView text, text2;
    private  RequestQueue mQueue;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        text2 = findViewById(R.id.text2);
        mQueue = Volley.newRequestQueue(this);
        button = findViewById(R.id.calendrier);

        jsonTest();


    }

    private void jsonTest()
    {
        String url = "https://api.myjson.com/bins/n63l4";
        String apiKey = BuildConfig.ApiKey; // securisation de la clé d'API, ajoutée dans gradel.properties et build.gradle

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
                            int nbGoals = buteurs.getInt("numberOfGoals");
                            text.append(namePlayer + "   " +nbGoals + "\n");
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
        });

        mQueue.add(request);
    }

}



