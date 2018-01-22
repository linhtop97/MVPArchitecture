package com.example.nguye.mvparchitecture.data;

import java.io.Serializable;

/**
 * Created by nguye on 22/01/2018.
 */

public class User implements Serializable {
    private int mId;
    private String mLogin;
    private String mUrlGithub;
    private String mUrlAvatar;

    public User(int id, String login, String urlGithub, String urlAvatar) {
        this.mId = id;
        this.mLogin = login;
        this.mUrlGithub = urlGithub;
        this.mUrlAvatar = urlAvatar;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String loin) {
        mLogin = loin;
    }

    public String getUrlGithub() {
        return mUrlGithub;
    }

    public void setUrlGithub(String urlGithub) {
        mUrlGithub = urlGithub;
    }

    public String getUrlAvatar() {
        return mUrlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        mUrlAvatar = urlAvatar;
    }
}
