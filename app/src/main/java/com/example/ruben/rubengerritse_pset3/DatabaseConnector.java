package com.example.ruben.rubengerritse_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ruben on 23-9-16.
 */

public class DatabaseConnector extends AsyncTask<URL,Integer,String>{
    private JSONArray moviesArray;
    private JSONObject json;
    private Context context;
    private View view;
    private int method;

    public DatabaseConnector(Context context, View view, int method) {
        this.context = context;
        this.view = view;
        this.method = method;
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
        if (method == 0){
            updateSearchRecyclerView();
        } else {
            updateMoviePage();
        }
    }

    private void updateSearchRecyclerView(){
        try {
            if (json.getString("Response").equals("True")){
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_recycler_view);
                String moviesArrayString = json.getString("Search");
                moviesArray = new JSONArray(moviesArrayString);
                RecyclerView.Adapter adapter = new MyAdapter(moviesArray);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(context, "No movies found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateMoviePage(){
        TextView titleTextView = (TextView) view.findViewById(R.id.title_text_view);
        TextView plotTextView = (TextView) view.findViewById(R.id.plot_text_view);
        try {
            titleTextView.setText(json.getString("Title"));
            plotTextView.setText(json.getString("Plot"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
