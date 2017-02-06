package com.jmajyo.simondice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveData {

    public static String loadAllDataFromDisk(final Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String maxScore = preferences.getString("MAX_SCORE", "0");

        return maxScore;

    }

    public static void saveAllDataToDisk(final Context context, final String maxScore) {
        //  Abro el fichero
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        //  Grabo eso en el fichero
        editor.putString("MAX_SCORE", maxScore);
        //Este editor.aply() se ejecuta de manera asincrona (crea otro hilo).
        editor.apply();
    }
}
