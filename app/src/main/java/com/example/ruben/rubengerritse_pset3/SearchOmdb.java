package com.example.ruben.rubengerritse_pset3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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
    JSONObject json;
//    String title = "";
//    String plot = "";

    Context context;
    public SearchOmdb(Context context) {
        this.context = context.getApplicationContext();
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

        Intent getMoviePageScreenIntent = new Intent(context, MoviePage.class);
        getMoviePageScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            getMoviePageScreenIntent.putExtra("Title", json.getString("Title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        getMoviePageScreenIntent.putExtra("Title", title);
//        getMoviePageScreenIntent.putExtra("Plot", plot);
        context.startActivity(getMoviePageScreenIntent);
    }
}
