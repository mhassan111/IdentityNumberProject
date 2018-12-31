package com.identitynumber.app.repository;

import com.google.gson.JsonElement;
import com.identitynumber.app.interfaces.ApiCallInterface;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<JsonElement> executeLogin(String mobileNumber, String password) {
        return apiCallInterface.login(mobileNumber, password);
    }
}
