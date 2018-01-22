package com.example.nguye.mvparchitecture.data.resource.remote;

import com.example.nguye.mvparchitecture.data.resource.remote.FetchDataFromUrl;
import com.example.nguye.mvparchitecture.searchuser.SearchUserContract;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDAO {

    private SearchUserContract.Presenter mPresenter;

    public UserDAO(SearchUserContract.Presenter presenter){
        mPresenter = presenter;
    }

    public void getUserList(String url){
        new FetchDataFromUrl(mPresenter).execute(url);
    }

}
