package com.example.myapp6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pc on 2016/4/1.
 */
public class Welcom extends Activity {
    private Button bt_skip1;
    Thread thread=null;
    Handler handler;
    TextView tv_count;
    int count=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcone);
        tv_count=(TextView)findViewById(R.id.tv_count);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x110){
                    //tv_count.setText(String.valueOf(count));
                    tv_count.setText(String.valueOf(msg.arg1));
                }
                if(msg.what==0x120){
                    Intent intent=new Intent(Welcom.this,Login.class);//跳转
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread=new Thread(new Runnable(){
        @Override
            public void run() {
                try {
                    while (count>0) {
                        Message msg=new Message();
                        msg.what=0x110;
                        msg.arg1=count;

                        handler.sendMessage(msg);
                        //handler.sendEmptyMessage(0x110);
                        thread.sleep(1000);
                        count--;
                    }
                    handler.sendEmptyMessage(0x120);
                   /* Intent intent=new Intent(Welcom.this,Login.class);//跳转
                    startActivity(intent);
                    finish();*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        bt_skip1=(Button) findViewById(R.id.bt_skip1);
        bt_skip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thread!=null&&thread.isAlive()){
                    thread.interrupt();//结束线程
                }
                Intent intent=new Intent(Welcom.this,Login.class);
                startActivity(intent);//执行意图
                finish();//调用该方法后不能返回上一个页面

            }
        });
    }

}
