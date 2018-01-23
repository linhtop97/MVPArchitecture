package com.example.nguye.mvparchitecture.data.resource;

import android.graphics.Bitmap;

import com.example.nguye.mvparchitecture.data.User;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public interface UsersDataSource {
    void getUsers(String url, LoadUsersCallback callback);

    void getBitmap(String urlImage, LoadImageCallback callback);

    interface LoadUsersCallback {

        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface LoadImageCallback {

        void onBitmapLoaded(Bitmap bitmap);

        void onDataNotAvailable();
    }

}
