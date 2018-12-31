package com.identitynumber.app.inputParsing;

import com.google.gson.annotations.SerializedName;

public class SignUp {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("emailaddress")
    private String emailAddress;

    @SerializedName("password")
    private String password;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("mobile")
    private String mobileNumber;

    @SerializedName("country")
    private String countryName;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("file")
    private String profilePic;

    @SerializedName("isMale")
    private String isMale;

    @SerializedName("state")
    private String state;

    public SignUp(String firstName, String lastName, String emailAddress, String password, String dateOfBirth, String mobileNumber,
                  String countryName, String deviceType, String isMale, String state, String profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.countryName = countryName;
        this.deviceType = deviceType;
        this.isMale = isMale;
        this.state = state;
        this.profilePic = profilePic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getIsMale() {
        return isMale;
    }

    public String getState() {
        return state;
    }
}
