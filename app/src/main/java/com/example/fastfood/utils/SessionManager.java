package com.example.fastfood.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserLoginSession";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLogin(boolean isLoggedIn, String userId) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // Nếu người dùng đang đăng nhập, lưu cả tên người dùng
        if (isLoggedIn) {
            editor.putString(KEY_USER_ID, userId);
        } else {
            // Nếu người dùng đăng xuất, xóa thông tin tên người dùng
            editor.remove(KEY_USER_ID);
        }
        // commit changes
        editor.commit();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUserId() {
        return prefs.getString(KEY_USER_ID, null);
    }
}
