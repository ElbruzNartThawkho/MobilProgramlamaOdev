package com.example.sayacuygulamasi;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedSingleton {
    private static SharedSingleton sharedSingleton;

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    Context context;

    public static SharedSingleton getInstance(Context context){
        if(sharedSingleton==null){
            sharedSingleton=new SharedSingleton(context);
        }
        return sharedSingleton;
    }

    private SharedSingleton(Context context){
        this.context=context;
        preferences=context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static int sayacKontrol()
    {
        return preferences.getInt("Sayac",0);
    }

    public static void sayacAyarla(int value)
    {
        editor.putInt("Sayac",value);
        editor.apply();
    }

    public static int limitKontrol()
    {
        return preferences.getInt("Limit",0);
    }

    public static void limitAyarla(int value)
    {
        editor.putInt("Limit",value);
        editor.apply();
    }

    public static boolean titresimVeSesKontrol()
    {
        return preferences.getBoolean("titresimVeSes",true);
    }

    public static void titresimVeSesAyarla(boolean bool)
    {
        editor.putBoolean("titresimVeSes",bool);
        editor.apply();
    }
}
