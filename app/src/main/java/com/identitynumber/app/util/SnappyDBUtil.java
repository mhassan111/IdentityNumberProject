package com.identitynumber.app.util;

import android.content.Context;

import com.identitynumber.app.responseParsing.UserInfo;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

public class SnappyDBUtil {
    private static final String snappyDBName = "IdentityNumber";
    private static final String userInfoKey = "userInfoKey";
    private static final String userToken = "userToken";

    public static void addUserInfo(Context context, UserInfo userInfo) {
        try {
            DB db = DBFactory.open(context, snappyDBName);
            db.put(userInfoKey, userInfo);
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static UserInfo getUserInfo(Context context) {
        UserInfo userInfo = null;
        try {
            DB db = DBFactory.open(context, snappyDBName);
            userInfo = db.getObject(userInfoKey, UserInfo.class);
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public static void saveUserToken(Context context, String token) {
        try {
            DB db = DBFactory.open(context, snappyDBName);
            db.put(userToken, token);
            db.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static String getUserToken(Context context) {
        String userToken = "";
        try {
            DB db = DBFactory.open(context, snappyDBName);
            userToken = db.get(userToken);
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return userToken;
    }


}
