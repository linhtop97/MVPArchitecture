package com.example.nguye.mvparchitecture.data.resource;

import android.support.annotation.NonNull;

import com.example.nguye.mvparchitecture.data.User;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public interface UsersDataSource {
    interface LoadUsersCallback {

        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface GetUserCallback {

        void onTaskLoaded(User user);

        void onDataNotAvailable();
    }

    void getUsers(String url, @NonNull LoadUsersCallback callback);

    void getUser(@NonNull String userId, @NonNull GetUserCallback callback);
}
