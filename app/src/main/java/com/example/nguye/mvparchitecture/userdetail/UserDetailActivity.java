package com.example.nguye.mvparchitecture.userdetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.R;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.model.UserHelper;

public class UserDetailActivity extends AppCompatActivity implements UserDetailContract.View {

    private TextView mTextViewLogin;
    private TextView mTextViewUrl;
    private ImageView mImageViewAvatar;
    private UserDetailContract.Presenter mPresenter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mTextViewLogin = findViewById(R.id.text_view_login);
        mTextViewUrl = findViewById(R.id.text_view_url);
        mImageViewAvatar = findViewById(R.id.image_avatar);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.loading));
        mPresenter = new UserDetailPresenter(this);
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.EXTRA_USERS);
        User user = (User) bundle.getSerializable(Constant.EXTRA_USERS);
        mTextViewLogin.setText(user.getLogin());
        mTextViewUrl.setText(user.getUrlGithub());
        mPresenter.getBitmap(user.getUrlAvatar());
    }

    @Override
    public void setImage(Bitmap bitmap) {
        mImageViewAvatar.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void showDiaLog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void setPresenter(UserDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
