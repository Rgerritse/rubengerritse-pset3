package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ruben on 22-9-16.
 */

public class MoviePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        Intent PrevScreenIntent = getIntent();
        String imdbID = PrevScreenIntent.getStringExtra("imdbID");


        URL url = null;
        try {
            url = new URL(String.format("http://www.omdbapi.com/?i=%s", imdbID));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new DetailsRetrieval(this, this.findViewById(android.R.id.content).getRootView()).execute(url);
//

    }
}
