package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.model.User;
import com.example.nguye.mvparchitecture.model.UserHelper;

import java.util.List;

/**
 * Created by nguye on 21/01/2018.
 */

public class OnLoadUserResultPresenterImpl implements OnLoadUserResultPresenter {
    private ListUserView view;
    private UserHelper mUserHelper;

    public OnLoadUserResultPresenterImpl(ListUserView view, UserHelper userHelper) {
        this.view = view;
        this.mUserHelper = userHelper;
        this.mUserHelper.setOnLoadUserResult(this);
    }

    public void getData(String login, String limit) {
        mUserHelper.getUserList(login, limit);
    }

    @Override
    public void onLoadUserSuccess(List<User> userList) {
        if (userList.size() > 0)
            view.displayUserList(userList);
    }
}
