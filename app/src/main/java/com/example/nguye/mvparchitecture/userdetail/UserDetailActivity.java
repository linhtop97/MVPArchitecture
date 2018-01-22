package com.example.nguye.mvparchitecture.userdetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.R;
import com.example.nguye.mvparchitecture.model.User;
import com.example.nguye.mvparchitecture.model.UserHelper;

public class UserDetailActivity extends AppCompatActivity implements UserDetailView {

    private TextView mTextViewLogin;
    private TextView mTextViewUrl;
    private ImageView mImageViewAvatar;

    private UserDetailPresenterImpl mUserDetailPresenter;
    private UserHelper mUserHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mTextViewLogin = findViewById(R.id.text_view_login);
        mTextViewUrl = findViewById(R.id.text_view_url);
        mImageViewAvatar = findViewById(R.id.image_avatar);
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.EXTRA_USERS);
        User user = (User) bundle.getSerializable(Constant.EXTRA_USERS);
        mTextViewLogin.setText(user.getLogin());
        mTextViewUrl.setText(user.getUrlGithub());
        mUserHelper = new UserHelper(this);
        mUserDetailPresenter = new UserDetailPresenterImpl(this, mUserHelper);
        mUserDetailPresenter.getBimap(user.getUrlAvatar());
    }

    @Override
    public void displayImage(Bitmap bitmap) {
        mImageViewAvatar.setImageBitmap(bitmap);
    }
}
