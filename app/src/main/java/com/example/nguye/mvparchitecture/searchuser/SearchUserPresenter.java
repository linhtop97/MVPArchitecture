package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.data.resource.UsersDataSource;
import com.example.nguye.mvparchitecture.data.resource.UsersRepository;
import com.example.nguye.mvparchitecture.data.resource.nothing.UserDAO;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public class SearchUserPresenter implements SearchUserContract.Presenter {
    private final UsersRepository mUsersRepository;
    private final SearchUserContract.View mView;

    public SearchUserPresenter(UsersRepository usersRepository, SearchUserContract.View view){
        mView = view;
        mUsersRepository = usersRepository;
        mView.setPresenter(this);
    }

    public void getUserList(){
        mView.showDiaLog();
        mUsersRepository.getUsers(new UsersDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                mView.onLoadUserSuccess(users);
            }

            @Override
            public void onDataNotAvailable() {
                mView.onNoData();
            }
        });
       // mUserDAO.getUserList(Constant.LINK_USER + limit + Constant.LOGIN_NAME + loginName);
    }

    @Override
    public void getUserList(String loginName, int limit) {

    }

    @Override
    public void getUserListSuccess(List<User> users) {
//        mView.dismissDialog();
//        mView.onLoadUserSuccess(users);
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void start() {
        getUserList();
    }
}
