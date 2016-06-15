package com.example.myapp6.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pc on 2016/5/23.
 */
public class MyStatiService extends IntentService {
    public static final String ACTION="android.intent.action.My_Static_Action";
    public MyStatiService(String name) {
        super(name);
    }
    public MyStatiService() {
        super("service-test");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i=new Intent(ACTION);
        sendBroadcast(i);

    }
}
