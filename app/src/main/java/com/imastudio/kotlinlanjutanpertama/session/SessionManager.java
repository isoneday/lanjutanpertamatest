package com.imastudio.kotlinlanjutanpertama.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    //TODO kenalin
    String KEY_NAME = "NAMA";
    String PREF_NAME = "SIMPAN";
    //TODO panggil Sharedpreference
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    //TODO konstruktor
    public SessionManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    //TODO SET_NAME (POSISI LOGIN)
    public void setNama(String nama) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_NAME, nama).apply();
    }

    //TODO GET_NAME (CEK LOGIN)
    public String getNama() {
        return mSharedPreferences.getString(KEY_NAME, null);
    }

    //TODO LOGOUT (MENGHAPUS)
    public void logout() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear().commit();
    }
}