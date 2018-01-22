package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.model.User;

import java.util.List;

/**
 * Created by nguye on 21/01/2018.
 */

public interface OnLoadUserResultPresenter {
    public void onLoadUserSuccess(List<User> userList);
}
