package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

/**
 * Created by ruben on 22-9-16.
 */

public class MoviePage extends AppCompatActivity {
    private String imdbID;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        Intent PrevScreenIntent = getIntent();
        imdbID = PrevScreenIntent.getStringExtra("imdbID");

        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView plotTextView = (TextView) findViewById(R.id.plot_text_view);
        try {
            URL url = new URL(String.format("http://www.omdbapi.com/?i=%s", imdbID));
            String jsonString = new DatabaseConnector().execute(url).get();
            json = new JSONObject(jsonString);

            titleTextView.setText(json.getString("Title"));
            plotTextView.setText(json.getString("Plot"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
//        if(pref.contains("savedMovies")){
//            Set<String> savedMovies = pref.getStringSet("savedMovies", null);
//            if (savedMovies.contains(imdbID)){
//                Button changeStatusButton = (Button) findViewById(R.id.change_status_button);
//                changeStatusButton.setText("Remove from watchlist");
//            }
//        }

    }

//    public void changeStatus(View view) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
//        Set<String> savedMovies;
//        if (pref.contains("savedMovies"))
//        {
//            savedMovies = pref.getStringSet("savedMovies", null);
//        } else {
//            savedMovies = new HashSet<>();
//        }
//
//        Button changeStatusButton = (Button) findViewById(R.id.change_status_button);
//        if (savedMovies.contains(imdbID)){
//            changeStatusButton.setText("Add to watchlist");
//            savedMovies.remove(imdbID);
//        } else {
//            changeStatusButton.setText("Remove from watchlist");
//            savedMovies.add(imdbID);
//        }
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putStringSet("savedMovies", savedMovies);
//        editor.commit();
//    }
}
