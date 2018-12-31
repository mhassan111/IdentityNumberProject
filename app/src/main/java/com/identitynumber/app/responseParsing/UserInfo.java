package com.identitynumber.app.responseParsing;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("memberId")
    private String memberId;

    @SerializedName("login")
    private String login;

    @SerializedName("added")
    private String dateAdded;

    @SerializedName("password")
    private String password;

    @SerializedName("fName")
    private String firstName;

    @SerializedName("lName")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("profileImgUrl")
    private String profileImageUrl;

    public String getDateAdded() {
        return dateAdded;
    }

    public String getLogin() {
        return login;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
