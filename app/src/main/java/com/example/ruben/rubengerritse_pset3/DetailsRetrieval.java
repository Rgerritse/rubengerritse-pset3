package com.example.ruben.rubengerritse_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ruben on 23-9-16.
 */

public class DetailsRetrieval extends AsyncTask<URL, Integer, String> {
    private JSONObject json;
    private Context context;
    private View view;

    public DetailsRetrieval(Context context, View view) {
        this.context = context;
        this.view = view;
    }

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

        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        TextView titleTextView = (TextView) view.findViewById(R.id.title_text_view);

        try {
            titleTextView.setText(json.getString("Title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
