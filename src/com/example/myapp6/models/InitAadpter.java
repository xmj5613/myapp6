package com.example.myapp6.models;

import android.content.Context;
import android.widget.ListView;
import com.example.myapp6.R;
import com.example.myapp6.adapter.UsersAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/5/12.
 */
public class InitAadpter<T> {
    public void initData(Context context, ListView lv_faxian,String result){
        try{
            List<Users> list =(List<Users>) getList(result,Users.class);
            UsersAdapter adapter=new UsersAdapter(context, R.layout.faxian_item,list);
            lv_faxian.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<T> getList(String result, Type type) {
        Gson gson=new Gson();
        JsonArray myArray=new JsonArray();
        JsonParser parser=new JsonParser();
        myArray=parser.parse(result).getAsJsonArray();
        List<T> list=new ArrayList<>();
        for(JsonElement element:myArray){
            T myT=gson.fromJson(element,type);
            list.add(myT);
        }
        return list;
    }
}
