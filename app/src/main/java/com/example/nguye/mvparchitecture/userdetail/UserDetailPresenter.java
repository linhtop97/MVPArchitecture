package com.example.nguye.mvparchitecture.userdetail;

import android.graphics.Bitmap;

import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;
import com.example.nguye.mvparchitecture.data.resource.remote.UsersRemoteDataSource;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDetailPresenter implements UserDetailContract.Presenter, UsersDataSource.LoadImageCallback {

    private UserDetailContract.View mView;
    private UsersRemoteDataSource mUsersRemoteDataSource;

    public UserDetailPresenter(UserDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUsersRemoteDataSource = new UsersRemoteDataSource();
    }

    @Override
    public void start() {

    }

    @Override
    public void getBitmap(String url) {
        mView.showDiaLog();
        mUsersRemoteDataSource.getBitmap(url, this);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap) {
        mView.dismissDialog();
        mView.setImage(bitmap);
    }

    @Override
    public void onDataNotAvailable() {
        mView.dismissDialog();
        mView.onNoData();
    }
}
