package com.example.myapp6.activites;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.myapp6.R;
import com.example.myapp6.services.MyTestService;

/**
 * Created by pc on 2016/5/18.
 */
public class TestServiceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                            try{
                                Thread.sleep(5000);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            Log.v("msg","5秒之后打印");
                        }
                }).start();*/
                launchMyService();
                btn_start_service.setText("start service");
            }
        });
    }
    protected void launchMyService(){
        Intent intent=new Intent(this, MyTestService.class);
        intent.putExtra("url","http://52.38.232.240:8080/jfinal_gradle/users");
        //intent.putExtra("myReceiver",receiver);
        startService(intent);
    }
 /*   private static Handler handler=new Handler();
    private ResultReceiver receiver=new ResultReceiver(handler){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==RESULT_OK){
                Log.v("activity-json",resultData.getString("myjson","error"));
            }
        }
    };*/
    BroadcastReceiver receiver=new BroadcastReceiver() {
     @Override
     public void onReceive(Context context, Intent intent) {
         Log.v("activity-json",intent.getStringExtra("myjson"));
     }
 };

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("activity","onResume");
        IntentFilter filter=new IntentFilter(MyTestService.ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("activity","onPause");
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }
}
