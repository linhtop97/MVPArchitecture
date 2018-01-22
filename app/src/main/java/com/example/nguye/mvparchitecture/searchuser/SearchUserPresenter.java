package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.data.resource.remote.UserDAO;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public class SearchUserPresenter implements SearchUserContract.Presenter {
    private UserDAO mUserDAO;

    private final SearchUserContract.View mView;

    public SearchUserPresenter(SearchUserContract.View view){
        mView = view;
        mView.setPresenter(this);
        mUserDAO = new UserDAO(this);
    }

    public void getUserList(String loginName, int limit){
        mView.showDiaLog();
        mUserDAO.getUserList(Constant.LINK_USER + limit + Constant.LOGIN_NAME + loginName);
    }

    @Override
    public void getUserListSuccess(List<User> users) {
        mView.dismissDialog();
        mView.onLoadUserSuccess(users);
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void start() {

    }
}
