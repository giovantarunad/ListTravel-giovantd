package com.example.listtravel.Models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.listtravel.LoginActivity;

public class Session {

    private static final String USERNAME_KEY = "key_username";
    private static final String TOKEN_KEY = "key_token";
    SharedPreferences.Editor editor;
    Context context;
    private SharedPreferences preferences;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUsername() {
        return preferences.getString(USERNAME_KEY, null);
    }

    public void setUsername(String username) {
        preferences.edit().putString(USERNAME_KEY, username)
                .apply();
    }

    public void setSession(String token) {
        preferences.edit().putString(TOKEN_KEY, token)
                .apply();
    }

    public boolean isLoggedIn() {
        String token = preferences.getString(TOKEN_KEY, null);
        return (token != null);
    }

    public boolean validate(String username, String password) {
        if (username.equals("admin") && password.equals("rahasia")) {
            setSession(username);
            return true;
        }
        return false;
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}