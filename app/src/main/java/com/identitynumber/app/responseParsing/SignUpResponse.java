package com.identitynumber.app.responseParsing;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    private boolean success;
    private String token;
    @SerializedName("user")
    private UserInfo userInfo;

    public String getToken() {
        return token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public boolean isSuccess() {
        return success;
    }
}
