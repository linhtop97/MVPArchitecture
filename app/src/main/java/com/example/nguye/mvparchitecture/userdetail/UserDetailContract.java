package com.example.nguye.mvparchitecture.userdetail;

import android.graphics.Bitmap;

import com.example.nguye.mvparchitecture.BasePresenter;
import com.example.nguye.mvparchitecture.BaseView;

/**
 * Created by nguye on 22/01/2018.
 */

public interface UserDetailContract {
    interface View extends BaseView<Presenter> {
        void setImage(Bitmap bitmap);

        void onError();

        void onNoData();

        void showDiaLog();

        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void getBitmap(String url);

        void getBitmapSuccess(Bitmap bitmap);

        void onNoData();
    }
}
