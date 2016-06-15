package com.example.myapp6.activites;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.drm.DrmStore;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.example.myapp6.Login;
import com.example.myapp6.NetworkActivity;
import com.example.myapp6.R;

import java.io.IOException;

/**
 * Created by pc on 2016/5/26.
 */
public class Work4Activity extends Activity {
    public ProgressBar pb_download,pb_download1;
    public RemoteViews rv;
    public int progress,stop;
    public  NotificationCompat.Builder notiBuilder;
    public NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work4_progressbar_view);
        pb_download = (ProgressBar) findViewById(R.id.pb_download);
        pb_download1 = (ProgressBar) findViewById(R.id.pb_download1);
        Button btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress > 100) {
                    Intent intent = new Intent(Work4Activity.this, Login.class);
                    startActivity(intent);
                }
            }
        });
        Button btn_download = (Button) findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressAsyncTask().execute();
                Intent intent=new Intent(getApplicationContext(), NetworkActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0x100,intent , PendingIntent.FLAG_CANCEL_CURRENT);
                notiBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setContentTitle("这是通知标题")
                                .setContentText("这是通知内容")
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true);
                rv = new RemoteViews(getApplicationContext().getPackageName(), R.layout.work4_notification_view);
                rv.setImageViewResource(R.id.iv_noti, R.drawable.ic_launcher);
                rv.setTextViewText(R.id.tv_noti_title, "文件名:" + Math.random());
                rv.setProgressBar(R.id.pb_download1, 100, progress, false);
                notiBuilder.setContent(rv);

                notificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                notificationManager.notify(1, notiBuilder.build());
            }
        });
    }

    class ProgressAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_download.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            progress=0;
            stop=1;
            while (true){
                int random=(int)(Math.random()*10);
                if(stop==1) {
                    progress += random;
                    publishProgress(progress);
                    if (progress >= 100) break;
                    SystemClock.sleep(1000);
                }
            }
            return "success";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_download.setProgress(values[0]);
            rv.setProgressBar(R.id.pb_download1,100,progress,false);
            notiBuilder.setContent(rv);
            notificationManager.notify(1,notiBuilder.build());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result=="success"){
                Toast.makeText(Work4Activity.this,"加载完成",Toast.LENGTH_LONG).show();
                Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                MediaPlayer mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(getApplication(),alert);
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    mMediaPlayer.prepare();     //后面的是try 和catch ，自动添加的
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mMediaPlayer.start();
            }
        }

    }
}
