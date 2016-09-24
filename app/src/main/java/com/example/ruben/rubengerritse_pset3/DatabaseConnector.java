package com.example.ruben.rubengerritse_pset3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ruben on 23-9-16.
 */

public class DatabaseConnector extends AsyncTask<URL,Integer,String>{

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
        return jsonString;
    }
}
