package com.example.lance_3770.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance-3770 on 7/2/2015.
 */
public class PreferencesService {

    private Context context;

    public PreferencesService(Context context) {
        this.context = context;
    }

    public void save(String name, Integer age) {

        SharedPreferences preferences = context.getSharedPreferences("preferences",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        editor.commit();

   }


    public Map<String,String> getPrferences() {
        Map<String,String> params = new HashMap<String,String>();

        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        params.put("name", preferences.getString("name",""));
        params.put("age", String.valueOf(preferences.getInt("age",0)));

        return params;
    }

}
