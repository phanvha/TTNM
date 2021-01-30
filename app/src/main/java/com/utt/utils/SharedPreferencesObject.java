package com.utt.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.utt.model.DataUser;

public class SharedPreferencesObject {


    public static void storeUserObject(Activity activity, DataUser dataUser){
        SharedPreferences mPrefs = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataUser);
        prefsEditor.putString("dataUser", json);
        prefsEditor.apply();
    }

    public static DataUser getUserObjectPreferences(Activity activity){
        SharedPreferences mPrefs = activity.getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("dataUser", "");
        DataUser obj = gson.fromJson(json, DataUser.class);

        return obj;
    }

}
