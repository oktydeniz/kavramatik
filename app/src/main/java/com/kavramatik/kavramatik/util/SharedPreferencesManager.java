package com.kavramatik.kavramatik.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "com.kavramatik.kavramatik";

    private static final String isFirstTime = "isFirstTime";
    private static final String userID = "userID";
    public static final int defaultID = -100;
    public static final int defaultScore = 0;
    private static final String userEmail = "userEmail";
    private static final String userName = "userName";
    private static final String score = "score";
    public static final String nullValue = "----";


    private SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getUserEmail(Context context) {
        return getSharedPreference(context).getString(userEmail, nullValue);
    }

    public static String getUserName(Context context) {
        return getSharedPreference(context).getString(userName, nullValue);
    }

    public static int getScore(Context context) {
        return getSharedPreference(context).getInt(score, defaultScore);
    }

    public static int getUserId(Context context) {
        return getSharedPreference(context).getInt(userID, defaultID);

    }

    public static void setUserEmail(Context context, String mail) {
        final SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(userEmail, mail);
        editor.apply();
    }

    public static void setUserName(Context context, String name) {
        final SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(userName, name);
        editor.apply();
    }

    public static void setScore(Context context, int lastScore) {
        final SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(score, lastScore);
        editor.apply();
    }

    public static void setUserID(Context context, int id) {
        final SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(userID, id);
        editor.apply();
    }

    public static void setIsFirstTime(Context context, boolean isFirst) {
        final SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(isFirstTime, isFirst);
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context) {
        return getSharedPreference(context).getBoolean(isFirstTime, true);
    }
}
