package com.example.nguye.mvparchitecture.data.resource.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nguye on 23/01/2018.
 */

public class FetchBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

    private UsersDataSource.LoadImageCallback mCallback;

    public FetchBitmapFromUrl(UsersDataSource.LoadImageCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap == null) {
            mCallback.onDataNotAvailable();
            return;
        }
        mCallback.onBitmapLoaded(bitmap);
    }

}
