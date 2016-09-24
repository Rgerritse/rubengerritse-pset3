package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Set;

/**
 * Created by ruben on 22-9-16.
 */

public class WatchList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JSONArray movieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String movieArrayString = pref.getString("movieArray", null);
        try {
            movieArray = new JSONArray(movieArrayString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.watch_list_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new MyAdapter(movieArray);
        recyclerView.setAdapter(adapter);

//        Set<String> savedMovies;
//        if (pref.contains("savedMovies")){
//            savedMovies = pref.getStringSet("savedMovies", null);
//
//        } else {
//
//        }

//        String[] movies = {"movie 1", "movie 2", "movie 3"};

//        setContentView(R.layout.activity_main);
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        RecyclerView.Adapter adapter = new MyAdapter(movies);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }

    public void toSearch(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
