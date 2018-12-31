package com.identitynumber.app.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    // Web Services Urls
    public static final String URL_BASE = "http://178.128.14.110:3000/";
    public static final String URL_LOGIN = "login";
    public static final String URL_SIGN_UP = URL_BASE + "signup";
    public static final String URL_COUNTRIES_LIST = URL_BASE + "getcountries";
    public static final String URL_GET_STATES = URL_BASE + "getstate";

    // Auth Services Constants
    public static final int TYPE_LOGIN_CALL = 1;
    public static final int TYPE_STATES_CALL = 2;

    // GET Data calls
    public static final int TYPE_GET_COUNTRIES = 1;

    public static boolean isEmailAddressValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
