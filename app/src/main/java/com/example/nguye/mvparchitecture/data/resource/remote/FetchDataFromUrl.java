package com.example.nguye.mvparchitecture.data.resource.remote;

import android.os.AsyncTask;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public class FetchDataFromUrl extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... strings) {
        String json = getJSONStringFromURL(strings[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
    }

    private List<User> getUserFromJSONString(String json) {
        List<User> users = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray(Constant.ITEMS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.optJSONObject(i);
                users.add(new User(object.getInt(Constant.ID),
                        object.getString(Constant.LOGIN),
                        object.getString(Constant.URL),
                        object.getString(Constant.AVATAR_URL)));
            }
            return users;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJSONStringFromURL(String urlString) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String jsonString = sb.toString();
            httpURLConnection.disconnect();
            return jsonString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}