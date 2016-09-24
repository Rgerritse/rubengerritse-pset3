package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Set;

/**
 * Created by ruben on 22-9-16.
 */

public class WatchList extends AppCompatActivity {
//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
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
