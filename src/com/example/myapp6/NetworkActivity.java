package com.example.myapp6;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.audiofx.BassBoost;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import okhttp3.*;
import java.io.IOException;


/**
 * Created by pc on 2016/5/9.
 */
public class NetworkActivity extends Activity {
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_img);
        final ImageView img_network=(ImageView)findViewById(R.id.img_network);
        final ProgressBar pb_loading=(ProgressBar)findViewById(R.id.pb_loading);
        Button btn_load=(Button)findViewById(R.id.btn_load);
        final String imgURL="https://i.imgur.com/tGbaZCY.jpg";
        // new NetworkAsyncTask(img_network,pb_loading).execute(imgURL);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new NetworkAsyncTask(img_network,pb_loading).execute(imgURL);
                //Pocasso第三方类库
               // Picasso.with(getApplication()).load(imgURL).into(img_network);将会报主线程的错误，因为子线程不能改变界面
                //调用Picasso另外一个into方法，则可以改变主线程
                Picasso.with(getApplicationContext()).load(imgURL).into(img_network, new Callback() {
                    @Override
                    public void onSuccess() {
                        if(pb_loading!=null){
                            pb_loading.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {
                        Log.v("error","报错");
                    }
                });
            }
        });

            //OkHttp第三方类库
            String url="http://52.38.232.240:8080/jfinal_gradle/users";
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("error","error");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.v("json",response.body().string());
                }
            });
            if(isNetworkConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"当前网络可用！",Toast.LENGTH_LONG).show();
        }else{
                Dialog dialog=new AlertDialog.Builder(NetworkActivity.this)
                        .setTitle("网络问题")
                        .setMessage("是否打开网络设置界面?")
                        .setIcon(R.drawable.ic_launcher)
                        .setCancelable(false)
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Settings.ACTION_SETTINGS);
                                startActivity(intent);
                            }
                        }).create();
           dialog.show();
            return;
        }
    }
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null){
            return info.isAvailable();
        }
        return false;
    }
    /*class NetworkAsyncTask extends AsyncTask<String,Integer,Bitmap>{
        ImageView myImg;
        ProgressBar pb_loading;
        public NetworkAsyncTask(ImageView myImg, ProgressBar pb_loading) {
            this.myImg=myImg;this.pb_loading=pb_loading;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_loading.setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String... addresses) {
            Bitmap bitmap=null;
            InputStream is=null;
            OutputStream os=null;
            try{
                URL url= new URL(addresses[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                is=connection.getInputStream();
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                os=new BufferedOutputStream(bos);
                int total=connection.getContentLength();
                int current=0;
                int read=0;
                byte[]data=new byte[1024];
                while ((read=is.read(data))!=-1){
                    os.write(data,0,read);
                    current=current+read;
                    int progress=(int)((float)current/total*100);
                    publishProgress(progress);
                }
                os.flush();
                byte[]BitmapData=bos.toByteArray();
                bitmap=BitmapFactory.decodeByteArray(BitmapData,0,BitmapData.length);
                //bitmap= BitmapFactory.decodeStream(is);
            }catch(IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_loading.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            myImg.setImageBitmap(bitmap);
            pb_loading.setVisibility(View.GONE);
        }

    }*/
}
