package com.example.myapp6.activites;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import com.example.myapp6.NetworkActivity;
import com.example.myapp6.R;
/**
 * Created by pc on 2016/5/25.
 */
public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setText("通知");
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), NetworkActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0x100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Builder notiBuilder=
                        new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("这是通知标题")
                        .setContentText("这是通知内容")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                RemoteViews rv=new RemoteViews(getApplicationContext().getPackageName(),R.layout.notification_view);
                rv.setImageViewResource(R.id.iv_noti,R.drawable.ic_launcher);
                rv.setTextViewText(R.id.tv_noti_title,"标题"+Math.random());
                rv.setTextViewText(R.id.tv_noti_content,"内容"+Math.random());

                notiBuilder.setContent(rv);
                NotificationManager notificationManager=
                        (NotificationManager)getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                notificationManager.notify(1,notiBuilder.build());
            }
        });
    }
}
