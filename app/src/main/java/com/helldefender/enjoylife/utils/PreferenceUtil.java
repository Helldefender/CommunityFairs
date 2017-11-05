package com.helldefender.enjoylife.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.helldefender.enjoylife.app.App;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class PreferenceUtil {

    private static final String KEY_USER_ACCOUNT = "userAccount";

    //账号类型
    private static final String KEY_USER_ACCOUNT_TYPE = "userAccountType";

    //用户类型
    private static final String KEY_USER_ID = "userId";

    private static final String KEY_USER_NAME = "userName";

    private static final String KEY_USER_AVATAR = "userAvatar";

//    private static final String KEY_USER_PHONE = "userPhone";
//
//    private static final String KEY_USER_EMAIL = "userEmail";

    private static final String KEY_USER_PASSWORD = "userPassword";

    private static final String KEY_USER_TOKEN = "userToken";

    private static final String KEY_ENTER_STATUS = "enterStatus";

    private static final String KEY_LAB_ID = "labId";

    //是否是PI
    private static final String KEY_TYPE = "type";

    public static void saveUserAccount(String account) {
        saveString(KEY_USER_ACCOUNT, account);
    }

    public static String getUserAccount() {
        return getString(KEY_USER_ACCOUNT);
    }

    public static void clearUserAccount() {
        saveString(KEY_USER_ACCOUNT, null);
    }

    public static void saveUserAccountType(boolean isPhone) {
        saveBoolean(KEY_USER_ACCOUNT_TYPE, isPhone);
    }

    public static boolean getUserAccountType() {
        return getBoolean(KEY_USER_ACCOUNT_TYPE);
    }

    public static void clearUserAccountType() {
        saveBoolean(KEY_USER_ACCOUNT_TYPE, true);
    }

    public static void saveUserId(int id) {
        saveInt(KEY_USER_ID, id);
    }

    public static int getUserId() {
        return getInt(KEY_USER_ID);
    }

    public static void clearUserId() {
        saveInt(KEY_USER_ID, -1);
    }

    public static void saveUserName(String account) {
        saveString(KEY_USER_NAME, account);
    }

    public static String getUserName() {
        return getString(KEY_USER_NAME);
    }

    public static void clearUserName() {
        saveString(KEY_USER_NAME, null);
    }

    public static void saveUserAvatar(String account) {
        saveString(KEY_USER_AVATAR, account);
    }

    public static String getUserAvatar() {
        return getString(KEY_USER_AVATAR);
    }

    public static void clearUserAvatar() {
        saveString(KEY_USER_AVATAR, null);
    }

    public static void saveLabId(int labId) {
        saveInt(KEY_LAB_ID, labId);
    }

    public static int getLabId() {
        return getInt(KEY_LAB_ID);
    }

    public static void clearLabId() {
        saveInt(KEY_LAB_ID, -1);
    }

//    public static void saveUserPhone(String account) {
//        saveString(KEY_USER_PHONE, account);
//    }
//
//    public static String getUserPhone() {
//        return getString(KEY_USER_PHONE);
//    }
//
//    public static void clearUserPhone() {
//        saveString(KEY_USER_PHONE, null);
//    }
//
//    public static void saveUserEmail(String account) {
//        saveString(KEY_USER_EMAIL, account);
//    }
//
//    public static String getUserEmail() {
//        return getString(KEY_USER_EMAIL);
//    }
//
//    public static void clearUserEmail() {
//        saveString(KEY_USER_EMAIL, null);
//    }

    public static void saveUserPassword(String password) {
        saveString(KEY_USER_PASSWORD, password);
    }

    public static String getUserPassword() {
        return getString(KEY_USER_PASSWORD);
    }

    public static void clearUserPassword() {
        saveString(KEY_USER_PASSWORD, null);
    }

    public static void saveEnterStatus(String enterStatus) {
        saveString(KEY_ENTER_STATUS, enterStatus);
    }

    public static String getEnterStatus() {
        return getString(KEY_ENTER_STATUS);
    }

    public static void clearEnterStatus() {
        saveString(KEY_ENTER_STATUS, null);
    }

    public static void saveUserToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static String getUserToken() {
        return getString(KEY_USER_TOKEN);
    }

    public static void clearUserToken() {
        saveString(KEY_USER_TOKEN, null);
    }

    public static boolean getType() {
        return getBoolean(KEY_TYPE);
    }

    public static void saveType(boolean type) {
        saveBoolean(KEY_TYPE, type);
    }

    public static void clearType() {
        saveBoolean(KEY_TYPE, false);
    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    private static void saveInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static int getInt(String key) {
        return getSharedPreferences().getInt(key, -1);
    }

    private static void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    static SharedPreferences getSharedPreferences() {
        return App.getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
    }
//
//    private static final String KEY_USER_ACCOUNT = "account";
//
//    private static final String KEY_USER_TOKEN = "token";
//
//    private static final String KEY_ENTER_STATUS = "enter";
//
//    private static final String KEY_SEARCH_HISTORY = "searchHistory";
//
//    private static final String KEY_TYPE = "type";
//
//    public static void saveUserAccount(String account) {
//        saveString(KEY_USER_ACCOUNT, account);
//    }
//
//    public static String getUserAccount() {
//        return getString(KEY_USER_ACCOUNT);
//    }
//
//    public static void saveEnterStatus(String enterStatus) {
//        saveString(KEY_ENTER_STATUS, enterStatus);
//    }
//
//    public static String getEnterStaus() {
//        return getString(KEY_ENTER_STATUS);
//    }
//
//    public static void saveUserToken(String token) {
//        saveString(KEY_USER_TOKEN, token);
//    }
//
//    public static String getUserToken() {
//        return getString(KEY_USER_TOKEN);
//    }
//
//    public static void clearUserToken() {
//        saveString(KEY_USER_TOKEN ,null);
//    }
//
//    public static void saveSearchHistory(String searchHistory) {
//        saveString(KEY_SEARCH_HISTORY, searchHistory);
//    }
//
//    public static String getSearchHistory() {
//        return getString(KEY_SEARCH_HISTORY);
//    }
//
//    public static void clearSearchHistory() {
//        saveString(KEY_SEARCH_HISTORY, null);
//    }
//
//    public static String getType() {
//        return getString(KEY_TYPE);
//    }
//
//    public static void saveType(String type) {
//        saveString(KEY_TYPE, type);
//    }
//
//    private static void saveString(String key, String value) {
//        SharedPreferences.Editor editor = getSharedPreferences().edit();
//        editor.putString(key, value);
//        editor.commit();
//    }
//
//    private static String getString(String key) {
//        return getSharedPreferences().getString(key, null);
//    }
//
//    static SharedPreferences getSharedPreferences() {
//        return App.getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
//    }
}
