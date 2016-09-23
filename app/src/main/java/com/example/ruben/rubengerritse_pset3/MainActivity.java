package com.example.ruben.rubengerritse_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import java.net.URL;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchMovie(View view) throws IOException {
        EditText search_edit_text = (EditText) findViewById(R.id.search_edit_text);



        URL url = new URL(String.format("http://www.omdbapi.com/?s=%s", search_edit_text.getText().toString()));
        LayoutInflater inflater = getLayoutInflater();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        new SearchOmdb(this.getApplicationContext(), recyclerView).execute(url);

    }
}
