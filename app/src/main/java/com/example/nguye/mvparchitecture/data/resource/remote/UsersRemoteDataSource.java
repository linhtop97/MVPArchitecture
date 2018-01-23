package com.example.nguye.mvparchitecture.data.resource.remote;

import android.support.annotation.NonNull;

import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;

/**
 * Created by nguye on 22/01/2018.
 */

public class UsersRemoteDataSource implements UsersDataSource {

    private static volatile UsersRemoteDataSource _instance;

    public static UsersRemoteDataSource getInstance() {
        if (_instance == null) {
            _instance = new UsersRemoteDataSource();
        }
        return _instance;
    }

    @Override
    public void getUsers(String url, @NonNull LoadUsersCallback callback) {
        new FetchDataFromUrl().execute(

        );
        callback.onUsersLoaded();
    }

    @Override
    public void getUser(@NonNull String userId, @NonNull GetUserCallback callback) {

    }
}
