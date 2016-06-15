package com.example.myapp6.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pc on 2016/5/24.
 */
public class BootReceiver extends BroadcastReceiver {
    private static String TAG="boot-Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"启动推送消息");

    }
}
