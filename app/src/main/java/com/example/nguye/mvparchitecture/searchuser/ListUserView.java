package com.example.nguye.mvparchitecture.searchuser;

import com.example.nguye.mvparchitecture.model.User;

import java.util.List;

/**
 * Created by Allen on 01-Apr-16.
 */
public interface ListUserView {

    void displayUserList(List<User> users);

    void recyclerItemClick(int position);

}
