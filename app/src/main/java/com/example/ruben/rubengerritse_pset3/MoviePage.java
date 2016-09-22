package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ruben on 22-9-16.
 */

public class MoviePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);



        Intent PrevScreenIntent = getIntent();
        String title = PrevScreenIntent.getStringExtra("Title");
        Toast.makeText(this, "Title " + title, Toast.LENGTH_SHORT).show();
//        String plot = PrevScreenIntent.getStringExtra("Plot");

//        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
//        titleTextView.setText(title);
    }
}
