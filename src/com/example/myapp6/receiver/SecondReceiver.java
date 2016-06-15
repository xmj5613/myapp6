package com.example.myapp6.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by pc on 2016/5/23.
 */
public class SecondReceiver extends BroadcastReceiver {
    private static String TAG="second-Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=getResultExtras(true).getString("msg");
        Log.v(TAG,msg);
        Bundle bundle=new Bundle();
        bundle.putString("msg",msg+" "+TAG);
        setResultExtras(bundle);
    }
}
