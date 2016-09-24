package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void searchMovie(View view) throws IOException {
        EditText search_edit_text = (EditText) findViewById(R.id.search_edit_text);

        URL url = new URL(String.format("http://www.omdbapi.com/?s=%s", search_edit_text.getText().toString()));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        JSONObject json = null;
        String jsonString;
        try {
            jsonString = new DatabaseConnector().execute(url).get();
            json = new JSONObject(jsonString);
            if (json.getString("Response").equals("True")){
                String moviesArrayString = json.getString("Search");
                JSONArray moviesArray = new JSONArray(moviesArrayString);
                RecyclerView.Adapter adapter = new MyAdapter(moviesArray);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No movies found", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void toWatchList(View view) {
        Intent watchListIntent = new Intent(this, WatchList.class);
        startActivity(watchListIntent);
    }
}
