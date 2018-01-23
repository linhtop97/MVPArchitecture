package com.example.nguye.mvparchitecture.data.resource.nothing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDetailDAO {
    private Context mContext;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    public UserDetailDAO(Context context) {
        mContext = context;
    }

    public void getBitmap(String url) {
        Picasso.with(mContext).load(url).into(target);
    }
}
