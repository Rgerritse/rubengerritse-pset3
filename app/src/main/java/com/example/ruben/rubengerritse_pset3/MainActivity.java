package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

// Activity of the MainActivity (Search Activity).
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create empty movieArray for the watch list if it doesn't exist
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if (!pref.contains("movieArray")){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("movieArray", new JSONArray().toString());
            editor.commit();
        }
    }

//    Obtain the layout of the menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

//    Opens the selected Activity on selection of an menu item.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.search_menu_item:
                break;
            case R.id.watch_list_menu_item:
                Intent watchListIntent = new Intent(this, WatchList.class);
                startActivity(watchListIntent);
                break;
        }
        return true;
    }

//    Searches the database upon query and updates the recycler view with the output.
    public void searchDatabase(View view) throws IOException {
        EditText search_edit_text = (EditText) findViewById(R.id.search_edit_text);

        String query = URLEncoder.encode(search_edit_text.getText().toString().trim(), "UTF-8");
        URL url = new URL(String.format("http://www.omdbapi.com/?s=%s", query));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            String jsonString = new DatabaseConnector().execute(url).get();
            JSONObject json = new JSONObject(jsonString);
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
}
