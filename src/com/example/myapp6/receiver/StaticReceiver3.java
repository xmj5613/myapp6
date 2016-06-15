package com.example.myapp6.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pc on 2016/5/23.
 */
public class StaticReceiver3 extends BroadcastReceiver {
    private static String TAG="静态注册3";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"这是Receiver3");
    }
}
