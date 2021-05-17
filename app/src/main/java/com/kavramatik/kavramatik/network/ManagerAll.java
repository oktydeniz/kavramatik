package com.kavramatik.kavramatik.network;


import com.kavramatik.kavramatik.model.ResponseModel;
import com.kavramatik.kavramatik.model.StatusModel;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private final static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }


    public Call<ResponseModel> register(String userMail, String userName, String userPassword) {
        return getRestApi().register(userMail, userName, userPassword);
    }

    public Call<ResponseModel> login(String email, String password) {
        return getRestApi().login(email, password);
    }

    public Call<StatusModel> sendMail(String email) {
        return getRestApi().sendMail(email);
    }

}
