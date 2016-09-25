package com.example.ruben.rubengerritse_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by ruben on 22-9-16.
 */

public class MoviePage extends AppCompatActivity {
    private String imdbID;
    private JSONObject movie;
    private JSONArray movieArray;
    private boolean inMovieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        Intent PrevScreenIntent = getIntent();
        imdbID = PrevScreenIntent.getStringExtra("imdbID");

        updateViews();
        updateButtonText();
    }

    private void updateViews(){
        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView plotTextView = (TextView) findViewById(R.id.plot_text_view);
        TextView yearTextView = (TextView) findViewById(R.id.year_text_view);
        TextView directorTextView = (TextView) findViewById(R.id.director_text_view);
        TextView actorsTextView = (TextView) findViewById(R.id.actors_text_view);
        TextView ratingTextView = (TextView) findViewById(R.id.rating_text_view);

        ImageView poster = (ImageView) findViewById(R.id.poster_image_view);

        try {
            URL movieUrl = new URL(String.format("http://www.omdbapi.com/?i=%s", imdbID));
            String jsonString = new DatabaseConnector().execute(movieUrl).get();
            movie = new JSONObject(jsonString);

            titleTextView.setText(movie.getString("Title"));
            plotTextView.setText(movie.getString("Plot"));
            yearTextView.setText(String.format("Year: %s", movie.getString("Year")));
            directorTextView.setText(String.format("Director(s): %s", movie.getString("Director")));
            actorsTextView.setText(String.format("Actor(s): %s", movie.getString("Actors")));
            ratingTextView.setText(String.format("Rating: %s / 10", movie.getString("imdbRating") ));

            if (!movie.getString("Poster").equals("N/A")) {
                URL imageUrl = new URL(movie.getString("Poster"));
                Bitmap bmp = new ImageCollector().execute(imageUrl).get();
                poster.setImageBitmap(bmp);
            } else {
                poster.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void updateButtonText(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String movieArrayString = pref.getString("movieArray", null);
        try {
            Button button = (Button) findViewById(R.id.change_status_button);
            inMovieArray = false;
            movieArray = new JSONArray(movieArrayString);
            for (int i = 0; i < movieArray.length(); i++){
                String id = movieArray.getJSONObject(i).getString("imdbID");
                if (id.equals(movie.getString("imdbID"))){
                    inMovieArray = true;
                    button.setText("Remove from Watchlist");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(View view) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        Button button = (Button) findViewById(R.id.change_status_button);

        try {
            if (inMovieArray){
                for (int i = 0; i < movieArray.length(); i++){
                    JSONObject json = movieArray.getJSONObject(i);
                    if (json.getString("imdbID").equals(movie.getString("imdbID"))){
                        JSONArray movieArrayDupl = new JSONArray();
                        for (int j = 0; j < movieArray.length(); j++){
                            if (i != j){
                                movieArrayDupl.put(movieArray.getJSONObject(j));
                            }
                        }
                        movieArray = movieArrayDupl;
                        inMovieArray = false;
                        break;
                    }
                }
                button.setText("Add to Watchlist");
            } else {
                movieArray.put(movie);
                inMovieArray = true;
                button.setText("Remove from Watchlist");
            }

            String movieArrayString = movieArray.toString();
            editor.putString("movieArray", movieArrayString);
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
