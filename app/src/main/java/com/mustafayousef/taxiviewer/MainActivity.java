package com.mustafayousef.taxiviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HalteplatzAdapter mHalteplatzAdapter;
    private ArrayList<Halteplatz> mHalteplatzList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btnRefresh = findViewById(R.id.btnRefresh);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mHalteplatzList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson(false);

        btnRefresh.setOnClickListener(v -> {
            mRequestQueue = Volley.newRequestQueue(this);
            parseJson(true);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson(true);
    }

    private void parseJson(boolean isUpdate) {

        String url = "https://taxi-api-de.herokuapp.com/api/v1/halteplaetze";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {

                        if (isUpdate) {
                            mHalteplatzList.clear();
                        }

                        JSONArray stationsArray = response.getJSONArray("stations");

                        for (int i = 0; i < stationsArray.length(); i++) {

                            JSONObject station = stationsArray.getJSONObject(i);

                            String name = station.getString("name");

                            JSONArray innerArray = station.getJSONArray("data");
                            JSONObject data = innerArray.getJSONObject(0);

                            int auftraege = data.optInt("auftraege");
                            int einstiege = data.optInt("einstiege");
                            String wartezeit = data.optString("wartezeit");

                            mHalteplatzList.add(
                                    new Halteplatz(name, auftraege, einstiege, wartezeit)
                            );
                        }

                        mHalteplatzAdapter = new HalteplatzAdapter(MainActivity.this,
                                mHalteplatzList);

                        mRecyclerView.setAdapter(mHalteplatzAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error sending the request", Toast.LENGTH_SHORT).show();
                });

        request.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        mRequestQueue.add(request);

    }
}