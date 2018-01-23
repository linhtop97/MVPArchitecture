package com.example.nguye.mvparchitecture.data.resource.nothing;

import com.example.nguye.mvparchitecture.data.resource.remote.FetchDataFromUrl;

/**
 * Created by nguye on 22/01/2018.
 */

public class UserDAO {

    public void getUserList(String url){
        new FetchDataFromUrl().execute(url);
    }

}
