package com.example.myapp6.activites;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.myapp6.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc on 2016/6/7.
 */
public class NotigivsyionActivity extends Activity {
    ProgressBar pb_load;
    Button btn_load;
    ImageView img_network;
    NotificationManager manager;
    Notification.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_img);
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        pb_load=(ProgressBar)findViewById(R.id.pb_loading);
        img_network=(ImageView)findViewById(R.id.img_network);
        final String imgUrl="https://i.imgur.com/tGbaZCY.jpg";
        btn_load=(Button)findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NetworkAsyncTask(img_network).execute(imgUrl);
            }
        });

    }
    class NetworkAsyncTask extends AsyncTask<String,Integer,Bitmap>{
        ImageView img;
        public NetworkAsyncTask(ImageView img) {
            this.img=img;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_load.setVisibility(View.VISIBLE);
            builder=new Notification.Builder(getApplicationContext());
            builder.setContentTitle("下载图片");
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),
                    0x110,new Intent(getApplicationContext(),NotificationActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pi);
            builder.setProgress(100,0,false);
            manager.notify(1,builder.build());

        }

        @Override
        protected Bitmap doInBackground(String... addresses) {
            Bitmap bitmap=null;
            InputStream is=null;
            OutputStream os=null;
            try{
                URL url=new URL(addresses[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                is=connection.getInputStream();
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                os=new BufferedOutputStream(bos);
                int total=connection.getContentLength();
                int current=0;
                int read=0;
                byte[] data=new byte[1024];
                while ((read=is.read(data))!=-1){
                    os.write(data,0,read);
                    current+=read;
                    int progress=(int)((float)current/total*100);
                    publishProgress(progress);
                }
                os.flush();
                byte[]bitmapData=bos.toByteArray();
                bitmap= BitmapFactory.decodeByteArray(bitmapData,0,bitmapData.length);

            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if(is!=null){
                    try {
                        is.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_load.setProgress(values[0]);
            if(builder!=null){
                builder.setContentText(values[0]+"%");
                builder.setProgress(100,values[0],false);
                Notification notification=builder.build();
                notification.flags=Notification.FLAG_NO_CLEAR;
                manager.notify(1,notification);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                img.setImageBitmap(bitmap);
            }
            pb_load.setVisibility(View.GONE);
            if(builder!=null){
                builder.setContentText("下载完成");
                builder.setProgress(100,100,true);
                Notification notification=builder.build();
                notification.flags=Notification.FLAG_AUTO_CANCEL;
                notification.defaults=Notification.DEFAULT_SOUND;
                manager.notify(1,notification);
            }
        }
    }
}
