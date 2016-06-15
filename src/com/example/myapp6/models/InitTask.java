package com.example.myapp6.models;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.myapp6.R;
import com.example.myapp6.adapter.UsersAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/5/12.
 */
public class InitTask extends AsyncTask<String,String,String> {
    ListView lv_faxian;
    ProgressBar pv_loading;
    Context context;
    int dataFlag;
    Dialog dialog;
    public InitTask(Context context,ProgressBar pv_loading, ListView lv_faxian,int dataFlag,Dialog dialog) {
        super();
        this.lv_faxian=lv_faxian;
        this.pv_loading=pv_loading;
        this.context=context;
        this.dataFlag=dataFlag;
        this.dialog=dialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //pv_loading.setVisibility(View.VISIBLE);
        if(dialog!=null){
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... addresser) {
        return HttpUtils.getString(addresser[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.v("json",result);
        switch (dataFlag){
            case 1: new InitAadpter<Users>().initData(context,lv_faxian,result);break;
            default: break;
        }
        //pv_loading.setVisibility(View.GONE);
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}