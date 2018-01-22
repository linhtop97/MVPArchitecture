package com.example.nguye.mvparchitecture.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.searchuser.OnLoadUserResultPresenter;
import com.example.nguye.mvparchitecture.userdetail.UserDetailPresenter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
 * Created by nguye on 21/01/2018.
 */

public class UserHelper {
    private OnLoadUserResultPresenter onLoadUserResult;
    private UserDetailPresenter mUserDetailPresenter;
    private Context mContext;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mUserDetailPresenter.setImage(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    public UserHelper() {
    }

    public UserHelper(Context context) {
        this.mContext = context;
    }

    public void setUserDetailPresenter(UserDetailPresenter userDetailPresenter) {
        this.mUserDetailPresenter = userDetailPresenter;
    }

    public void setOnLoadUserResult(OnLoadUserResultPresenter onLoadUserResult) {
        this.onLoadUserResult = onLoadUserResult;
    }

    public void getBitmap(String url) {
        Picasso.with(mContext).load(url).into(target);
    }

    public void getUserList(String login, String limit) {
        FetchDataFromUrl fetchDataFromUrl = new FetchDataFromUrl();
        fetchDataFromUrl.execute(Constant.LINK_USER +
                limit + Constant.LOGIN_NAME + login);
    }

    public class FetchDataFromUrl extends AsyncTask<String, Void, List<User>> {
        @Override
        protected List<User> doInBackground(String... strings) {
            String json = getJSONStringFromURL(strings[0]);
            return getUserFromJSONString(json);
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

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            onLoadUserResult.onLoadUserSuccess(users);
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
}
