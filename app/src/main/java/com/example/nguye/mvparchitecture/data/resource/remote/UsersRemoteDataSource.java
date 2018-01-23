package com.example.nguye.mvparchitecture.data.resource.remote;

import android.support.annotation.NonNull;

import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;

/**
 * Created by nguye on 22/01/2018.
 */

public class UsersRemoteDataSource implements UsersDataSource {

    @Override
    public void getUsers(String url, LoadUsersCallback callback) {
        new FetchDataFromUrl(callback).execute(url);
    }

    @Override
    public void getBitmap(String urlImage, LoadImageCallback callback) {
        new FetchBitmapFromUrl(callback).execute(urlImage);
    }

}
