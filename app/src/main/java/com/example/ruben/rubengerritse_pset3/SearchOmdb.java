package com.example.ruben.rubengerritse_pset3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by ruben on 22-9-16.
 */

public class SearchOmdb extends AsyncTask<URL, Integer, String> {
    private JSONArray moviesArray;
    private JSONObject json;
    private RecyclerView recyclerView;
//    String title = "";
//    String plot = "";

    Context context;
    public SearchOmdb(Context context, RecyclerView recyclerView) {
        this.context = context.getApplicationContext();
        this.recyclerView = recyclerView;
    }

    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputLine;
        String jsonString = "";

        try {
            while ((inputLine = in.readLine()) != null) {
                jsonString += inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            if (json.getString("Response").equals("True")){
                String moviesArrayString = json.getString("Search");
                moviesArray = new JSONArray(moviesArrayString);
                RecyclerView.Adapter adapter = new MyAdapter(moviesArray);
                recyclerView.setAdapter(adapter);

//                Intent getMoviePageScreenIntent = new Intent(context, MoviePage.class);
//                getMoviePageScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                try {
//                    getMoviePageScreenIntent.putExtra("Title", json.getString("Title"));
//                    getMoviePageScreenIntent.putExtra("Plot", json.getString("Plot"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                context.startActivity(getMoviePageScreenIntent);
            } else {
                Toast.makeText(context, "No movies found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//
//        try {
//            if (json.getString("Response").equals("True")){
//                Intent getMoviePageScreenIntent = new Intent(context, MoviePage.class);
//                getMoviePageScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                try {
//                    getMoviePageScreenIntent.putExtra("Title", json.getString("Title"));
//                    getMoviePageScreenIntent.putExtra("Plot", json.getString("Plot"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                context.startActivity(getMoviePageScreenIntent);
//            } else {
//                Toast.makeText(context, "No movie found", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
