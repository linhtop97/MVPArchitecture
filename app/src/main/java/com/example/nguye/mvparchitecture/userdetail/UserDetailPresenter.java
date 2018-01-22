package com.example.nguye.mvparchitecture.userdetail;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.nguye.mvparchitecture.data.resource.remote.UserDetailDAO;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDetailPresenter implements UserDetailContract.Presenter {

    private UserDetailContract.View mView;
    private UserDetailDAO mUserDetailDAO;

    public UserDetailPresenter(UserDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserDetailDAO = new UserDetailDAO((Context) view, this);

    }

    @Override
    public void start() {

    }

    @Override
    public void getBitmap(String url) {
        mView.showDiaLog();
        mUserDetailDAO.getBitmap(url);
    }

    @Override
    public void getBitmapSuccess(Bitmap bitmap) {
        mView.dismissDialog();
        mView.setImage(bitmap);
    }

    @Override
    public void onNoData() {

    }
}
