package com.example.myapp6.utils;

import android.util.Log;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by pc on 2016/6/1.
 */
public class JsonUtils  {
    public static String ObjectToJson(Object obj){
        String json="";
        Gson gson=new Gson();
        json=gson.toJson(obj,Object.class);
        return json;
    }
    public static Object jsonToOjbect(String json,Class c){
        try {
            Gson gson=new Gson();
            return gson.fromJson(json,c);
        }catch (Exception e){
            Log.v("error",e.getMessage());
        }
        return null;
    }
}
