package com.projectaty.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefrences {
    private static final String FIRST_TIME = "IS_FIRST_TIME";
    private static final String REMEMBER = "REMEMBER_ME";
    private static final String LANGUAGE = "ar";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    /*
        Check if first Time users
     */
    public static boolean isFirstTime(Context context) {
        preferences = getPreferences(context);
        return preferences.getBoolean(FIRST_TIME, true);
    }

    public static void setNotFirstTime(Context context) {
        preferences = getPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(FIRST_TIME, false);
        editor.apply();
    }

    /*
        Remeber User Prefrences
     */
    public static boolean isRememberMe(Context context){
        preferences = getPreferences(context);
        return preferences.getBoolean(REMEMBER, false);
    }

    public static void setRemember(Context context) {
        preferences = getPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(REMEMBER, false);
        editor.apply();
    }

    public static SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = preferences.edit();
        }
        return preferences;
    }
}
