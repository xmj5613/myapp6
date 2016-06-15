package com.example.myapp6.views;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.myapp6.R;

/**
 * Created by pc on 2016/5/5.
 */
public class ProgressBarActivity extends Activity {
    ProgressBar pb_load;
    Button bt_load;
    Handler handler;
    //int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        pb_load=(ProgressBar)findViewById(R.id.pb_load);
        bt_load=(Button)findViewById(R.id.bt_load);
       /* handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x110){
                    pb_load.setProgress(progress);
                }
                if(msg.what==0x120){
                    Toast.makeText(ProgressBarActivity.this,"加载完成",Toast.LENGTH_LONG).show();
                }
            }
        };*/
        bt_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressAsyncTask().execute();
               /* pb_load.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            int random=(int)(Math.random()*10);
                            progress+=random;
                            handler.sendEmptyMessage(0x110);
                            if(progress>=100) break;
                            SystemClock.sleep(1000);
                        }
                        handler.sendEmptyMessage(0x120);
                    }
                }).start();*/
            }
        });
    }
    class ProgressAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            int progress=0;
            while (true){
                int random=(int)(Math.random()*10);
                progress+=random;
                publishProgress(progress);
                if(progress>=100) break;
                SystemClock.sleep(1000);
            }
            return "success";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_load.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result=="success"){
                Toast.makeText(ProgressBarActivity.this,"加载完成",Toast.LENGTH_LONG).show();
            }
        }

    }
}
