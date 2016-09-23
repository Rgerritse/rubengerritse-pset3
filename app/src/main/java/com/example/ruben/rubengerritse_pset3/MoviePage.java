package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by ruben on 22-9-16.
 */

public class MoviePage extends AppCompatActivity {
    private String imdbID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        Intent PrevScreenIntent = getIntent();
        imdbID = PrevScreenIntent.getStringExtra("imdbID");

        URL url = null;
        try {
            url = new URL(String.format("http://www.omdbapi.com/?i=%s", imdbID));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new DatabaseConnector(this, this.findViewById(android.R.id.content).getRootView(), 2).execute(url);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if(pref.contains("savedMovies")){
            Set<String> savedMovies = pref.getStringSet("savedMovies", null);
            if (savedMovies.contains(imdbID)){
                Button changeStatusButton = (Button) findViewById(R.id.change_status_button);
                changeStatusButton.setText("Remove from watchlist");
            }
        }

    }

    public void changeStatus(View view) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        Set<String> savedMovies;
        if (pref.contains("savedMovies"))
        {
            savedMovies = pref.getStringSet("savedMovies", null);
        } else {
            savedMovies = new HashSet<>();
        }

        Button changeStatusButton = (Button) findViewById(R.id.change_status_button);
        if (savedMovies.contains(imdbID)){
            changeStatusButton.setText("Add to watchlist");
            savedMovies.remove(imdbID);
        } else {
            changeStatusButton.setText("Remove from watchlist");
            savedMovies.add(imdbID);
        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet("savedMovies", savedMovies);
        editor.commit();
    }
}
