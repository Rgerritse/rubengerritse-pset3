package com.example.ruben.rubengerritse_pset3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ruben on 22-9-16.
 */

public class SearchOmdb extends AsyncTask<URL, Integer, String> {
    String title;
    String plot;

    Context context;
    private SearchOmdb(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];

        InputStream in = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        JsonReader reader = new JsonReader(new InputStreamReader(in));
        try {
            reader.beginObject();
            while (reader.hasNext()){
                String name = reader.nextName();
                if (name.equals("Title")){
                    title = name;
                } else if (name.equals("Plot")){
                    title = name;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Intent getMoviePageScreenIntent = new Intent(context, MoviePage.class);
        getMoviePageScreenIntent.putExtra("Title", title);
        getMoviePageScreenIntent.putExtra("Plot", plot);
        context.startActivity(getMoviePageScreenIntent);
    }
}
