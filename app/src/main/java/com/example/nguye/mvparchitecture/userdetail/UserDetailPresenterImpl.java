package com.example.nguye.mvparchitecture.userdetail;

import android.graphics.Bitmap;

import com.example.nguye.mvparchitecture.model.UserHelper;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDetailPresenterImpl implements UserDetailPresenter {

    private UserDetailView view;
    private UserHelper mUserHelper;

    public UserDetailPresenterImpl(UserDetailView view, UserHelper mUserHelper) {
        this.view = view;
        this.mUserHelper = mUserHelper;
        this.mUserHelper.setUserDetailPresenter(this);
    }

    public void getBimap(String url) {
        mUserHelper.getBitmap(url);
    }

    @Override
    public void setImage(Bitmap bitmap) {
        view.displayImage(bitmap);
    }
}
