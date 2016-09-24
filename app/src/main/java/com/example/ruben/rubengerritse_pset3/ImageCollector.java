package com.example.ruben.rubengerritse_pset3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ruben on 24-9-16.
 */

public class ImageCollector extends AsyncTask<URL,Integer,Bitmap> {

    @Override
    protected Bitmap doInBackground(URL... params) {
        URL url = params[0];

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }
}
