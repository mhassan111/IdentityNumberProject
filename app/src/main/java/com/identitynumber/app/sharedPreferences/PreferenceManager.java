package com.identitynumber.app.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String PREFERENCE_NAME = "IDENTITY_NUMBER_PREFS";
    public static final String USER_TOKEN = "USER_TOKEN";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Save keys
     *
     * @param context Context
     * @param key     Key Name
     * @param value   Key Value
     */
    public static void saveUserKey(Context context, String key, String value) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * Get Key Values
     * @param context Context
     * @param key Key
     * @return Stored value of Key
     */
    public static String getUserKey(Context context, String key) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(key, "");
    }
}
