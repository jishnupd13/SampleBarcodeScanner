package com.spellknight.barcodescanner.data.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */

@Singleton
class PreferenceHandler @Inject constructor(context: Context){
    var sharedpreferences: SharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedpreferences.edit()
    var appContext=context

    fun saveVale(key:String,obj:Any):Boolean{
        when (obj) {
            is String -> {
                editor.putString(key,obj)
            }
            is Int -> {
                editor.putInt(key,obj)
            }
            is Boolean -> {
                editor.putBoolean(key,obj)
            }
            is Float -> {
                editor.putFloat(key,obj)
            }
        }
       return editor.commit()
    }

    fun getString(key: String):String{
        return sharedpreferences.getString(key,"")?:""
    }

    fun getBoolean(key:String):Boolean{
        return sharedpreferences.getBoolean(key,true)
    }

    fun getInteger(key: String):Int{
        return sharedpreferences.getInt(key,0)
    }

    fun getFloat(key: String):Float{
        return sharedpreferences.getFloat(key,0F)
    }

    fun removeKey(key: String):Boolean{
        editor.remove(key)
       return editor.commit()
    }

    fun removeAll():Boolean{
        return appContext.getSharedPreferences("MyPreference",0).edit().clear().commit()
    }

}