package com.example.myapp6.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by pc on 2016/5/23.
 */
public class MyOrderService extends IntentService {
    public static final String ACTION="android.intent.action.My_Order_Action";
    public MyOrderService(String name) {
        super(name);
    }
    public MyOrderService() {
        super("service-test");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg=intent.getStringExtra("msg");
        Intent i=new Intent(ACTION);
        i.putExtra("msg",msg);
        sendOrderedBroadcast(i,"com.my.order.receiver.permission");
    }
}

