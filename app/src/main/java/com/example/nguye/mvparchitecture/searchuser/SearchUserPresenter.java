package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;
import com.example.nguye.mvparchitecture.data.resource.remote.UsersRemoteDataSource;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public class SearchUserPresenter implements SearchUserContract.Presenter, UsersDataSource.LoadUsersCallback {
    private final SearchUserContract.View mView;

    public SearchUserPresenter(SearchUserContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getUserList(String loginName, int limit) {
        mView.showDiaLog();
        UsersRemoteDataSource usersRemoteDataSource = new UsersRemoteDataSource();
        usersRemoteDataSource.getUsers(Constant.LINK_USER + limit + Constant.LOGIN_NAME + loginName,
                this);
    }

    @Override
    public void start() {
    }

    @Override
    public void onUsersLoaded(List<User> users) {
            mView.dismissDialog();
            mView.onLoadUserSuccess(users);
    }

    @Override
    public void onDataNotAvailable() {
        mView.dismissDialog();
        mView.onNoData();
    }
}
