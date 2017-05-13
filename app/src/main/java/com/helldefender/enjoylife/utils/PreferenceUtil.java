package com.helldefender.enjoylife.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.helldefender.enjoylife.app.MyApplication;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class PreferenceUtil {

    private static final String KEY_USER_ACCOUNT = "account";

    private static final String KEY_USER_TOKEN = "token";

    private static final String KEY_ENTER_STATUS = "enter";

    private static final String KEY_SEARCH_HISTORY = "searchHistory";

    private static final String KEY_TYPE = "type";

    public static void saveUserAccount(String account) {
        saveString(KEY_USER_ACCOUNT, account);
    }

    public static String getUserAccount() {
        return getString(KEY_USER_ACCOUNT);
    }

    public static void saveEnterStatus(String enterStatus) {
        saveString(KEY_ENTER_STATUS, enterStatus);
    }

    public static String getEnterStaus() {
        return getString(KEY_ENTER_STATUS);
    }

    public static void saveUserToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static String getUserToken() {
        return getString(KEY_USER_TOKEN);
    }

    public static void clearUserToken() {
        saveString(KEY_USER_TOKEN ,null);
    }

    public static void saveSearchHistory(String searchHistory) {
        saveString(KEY_SEARCH_HISTORY, searchHistory);
    }

    public static String getSearchHistory() {
        return getString(KEY_SEARCH_HISTORY);
    }

    public static void clearSearchHistory() {
        saveString(KEY_SEARCH_HISTORY, null);
    }

    public static String getType() {
        return getString(KEY_TYPE);
    }

    public static void saveType(String type) {
        saveString(KEY_TYPE, type);
    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return MyApplication.getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
    }
}
