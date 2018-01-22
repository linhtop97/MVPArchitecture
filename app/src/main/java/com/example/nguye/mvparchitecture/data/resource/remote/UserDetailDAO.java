package com.example.nguye.mvparchitecture.data.resource.remote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.example.nguye.mvparchitecture.userdetail.UserDetailContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDetailDAO {
    private Context mContext;
    private UserDetailContract.Presenter mPresenter;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mPresenter.getBitmapSuccess(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    public UserDetailDAO(Context context, UserDetailContract.Presenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    public void getBitmap(String url) {
        Picasso.with(mContext).load(url).into(target);
    }
}
