package com.jmajyo.simondice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveData {

    public static String loadAllDataFromDisk(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String maxScore = preferences.getString("MAX_SCORE", "");

        return maxScore;

    }

    public static void saveAllDataToDisk(Context context, String maxScore) {
        //  Abro el fichero
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        //  Grabo eso en el fichero
        editor.putString("MAX_SCORE", maxScore);
        editor.apply();
    }
}
