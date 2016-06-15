package com.example.myapp6.activites;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.myapp6.R;
import com.example.myapp6.services.DownloadService;
import okhttp3.HttpUrl;

/**
 * Created by pc on 2016/6/7.
 */
public class DwonloadActivity extends Activity {
    ProgressBar pb_load;
    Button btn_load;
    ImageView img_network;
    MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_img);
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("android.intent.action.MY_RECEIVER");
        registerReceiver(receiver,filter);
        pb_load=(ProgressBar)findViewById(R.id.pb_loading);
        img_network=(ImageView)findViewById(R.id.img_network);
        final String imgUrl="https://i.imgur.com/tGbaZCY.jpg";
        btn_load=(Button)findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_load.setVisibility(View.VISIBLE);
                startService(new Intent(getApplicationContext(),DownloadService.class));
            }
        });
    }
    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            int progress=bundle.getInt("progress");
            pb_load.setProgress(progress);
            if(progress>=100){
                byte[]bitmapData=bundle.getByteArray("bitmap");
                Bitmap bitmap= BitmapFactory.decodeByteArray(bitmapData,0,bitmapData.length);
                img_network.setImageBitmap(bitmap);
            }
        }
    }
}
