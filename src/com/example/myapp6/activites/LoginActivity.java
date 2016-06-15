package com.example.myapp6.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapp6.Main;
import com.example.myapp6.R;
import com.example.myapp6.models.HttpUtils;
import com.example.myapp6.models.ResponseData;
import com.example.myapp6.utils.JsonUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ResponseDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/5/31.
 */
public class LoginActivity extends Activity{
    EditText et_pwd1,et_id1;
    private static final int REQUESTCODE=0x110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_pwd1=(EditText)findViewById(R.id.et_pwd1);
        et_id1=(EditText)findViewById(R.id.et_id1);
        Button bt_login=(Button)findViewById(R.id.bt_login);
        /*final String url="http://52.38.232.240:8080/jfinal_gradle/sendpost";
        final String uuid=et_id1.getText().toString();
        final String userPwd=et_pwd1.getText().toString();*/
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=et_id1.getText().toString();
                String pwd=et_pwd1.getText().toString();
                String url="http://52.38.232.240:8080/jfinal_gradle/apkusers/login";
                new PostTask().execute(url,id,pwd);
                /*OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
                        .add("uuid",uuid)
                        .add("userPwd",userPwd)
                        .build();
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("failure","获取数据失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Intent intent=new Intent(getApplication(), Main.class);
                        startActivity(intent);
                    }
                });*/
            }
        });
        Button btn_reg=(Button)findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivityForResult(intent,REQUESTCODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode==0x101){

        }
        if(requestCode==REQUESTCODE&&requestCode==0x111){
            if(intent!=null){
                Bundle bundle=intent.getExtras();
                if(bundle!=null){
                    String userId=bundle.getString("userId","1001");
                    String usesPwd=bundle.getString("userPwd","1234");
                    et_id1.setText(userId);
                    et_pwd1.setText(usesPwd);
                }
            }
        }
    }

    class PostTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params=new ArrayList<>();
            NameValuePair nvp1=new BasicNameValuePair("uuid",strings[1]);
            NameValuePair nvp2=new BasicNameValuePair("userPwd",strings[2]);
            params.add(nvp1);
            params.add(nvp2);
            return HttpUtils.postData(strings[0],params);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null&&!s.isEmpty()){
                ResponseData rd=(ResponseData) JsonUtils.jsonToOjbect(s,ResponseData.class);
                if(rd.getSuccess()){
                    Intent intent=new Intent(getApplication(), Main.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),rd.getMsg().toString(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
