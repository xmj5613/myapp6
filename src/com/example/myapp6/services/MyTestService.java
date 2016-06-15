package com.example.myapp6.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.myapp6.models.HttpUtils;

/**
 * Created by pc on 2016/5/18.
 */
public class MyTestService extends IntentService {
    public static final String ACTION="jsj.fdgm.edu";
    public MyTestService(String name) {
        super(name);
    }
    public MyTestService() {
        super("service-test");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String json= HttpUtils.getString(intent.getStringExtra("url"));
        Log.v("json-service",json);
       /* ResultReceiver receiver=(ResultReceiver)intent.getExtras().get("myReceiver");
        Bundle bundle=new Bundle();
        bundle.putString("myjson",json);
        if(receiver!=null){
            receiver.send(Activity.RESULT_OK,bundle);
        }*/
        Intent i=new Intent(ACTION);
        i.putExtra("myjson",json);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
    }
}
