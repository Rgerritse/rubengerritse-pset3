package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by ruben on 22-9-16.
 * Activity of the WatchList
 */

public class WatchList extends AppCompatActivity {
    private JSONArray movieArray;

//    Obtains the watchlist from the shared preferences and displays it if possible. If the watch
//    list is empty then it returns an message saying so.
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

        if (movieArray.length() != 0) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.watch_list_recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerView.Adapter adapter = new MyAdapter(movieArray);
            recyclerView.setAdapter(adapter);
        } else {
            TextView emptyMessage = (TextView) findViewById(R.id.empty_message_text_view);
            emptyMessage.setVisibility(View.VISIBLE);
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
                Intent SearchIntent = new Intent(this, MainActivity.class);
                startActivity(SearchIntent);
                break;
            case R.id.watch_list_menu_item:
                break;
        }
        return true;
    }
}
