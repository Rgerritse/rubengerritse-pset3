package com.example.ruben.rubengerritse_pset3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ruben on 22-9-16.
 */

public class SearchOmdb extends AsyncTask<URL, Integer, String> {
    String title = "";
    String plot = "";

    Context context;
    public SearchOmdb(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];

        InputStream in = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        getStringFromInputStream(in);


//        JsonReader reader = null;
//

//        try {
//            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
//            reader.beginObject();
//            title = "bla";
////            while (reader.hasNext()){
////                String name = reader.nextName();
////                if(name.equals("Title"))
////                {
////                    title = reader.nextString();
////                }
////            }
//            reader.endObject();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//            while (reader.hasNext()){
//                String name = reader.nextName();
//                if (name.equals("Title")) {
//                    title = name;
//                }
////                } else if (name.equals("Plot")){
////                    plot = name;
////                }

//
//        try {
//            reader.endObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Intent getMoviePageScreenIntent = new Intent(context, MoviePage.class);
        getMoviePageScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getMoviePageScreenIntent.putExtra("Title", title);
//        getMoviePageScreenIntent.putExtra("Title", title);
//        getMoviePageScreenIntent.putExtra("Plot", plot);
        context.startActivity(getMoviePageScreenIntent);
    }

    private void getStringFromInputStream(InputStream is) {

        BufferedReader br = null;

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                title += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
