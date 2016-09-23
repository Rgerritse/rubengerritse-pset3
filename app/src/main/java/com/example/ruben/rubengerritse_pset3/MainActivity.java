package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import java.net.URL;

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

        new DatabaseConnector(this, this.findViewById(android.R.id.content), 0).execute(url);
    }

    public void toWatchList(View view) {
        Intent watchListIntent = new Intent(this, WatchList.class);
        startActivity(watchListIntent);
    }
}
