package com.example.inventory.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "App of Holding";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_PICTURE = "picture";
    private static final String KEY_MOBILE = "mobile";

    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Logging in user and setting the name and profile picture
    public void createLogin() {

        //here, handle the mobile number or email or any details that you
        //use for the login. Then do this:

        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);//false is the default value in case there's nothing found with the key
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}
