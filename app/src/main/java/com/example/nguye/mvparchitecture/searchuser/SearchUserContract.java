package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.BasePresenter;
import com.example.nguye.mvparchitecture.BaseView;
import com.example.nguye.mvparchitecture.data.User;

import java.util.List;

/**
 * Created by nguye on 22/01/2018.
 */

public interface SearchUserContract {
    interface View extends BaseView<Presenter>{
        void onLoadUserSuccess(List<User> userList);

        void onNoData();

        void showDiaLog();

        void dismissDialog();
    }

    interface Presenter extends BasePresenter{
        void getUserList(String loginName, int limit);
    }
}
