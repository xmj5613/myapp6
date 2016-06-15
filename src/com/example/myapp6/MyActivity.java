package com.example.myapp6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapp6.database.UserDatabaseHelper;
import com.example.myapp6.models.Users;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Button btn_qd,btn_tg,btn_tgall,btn_like,btn_xg,btn_sc,skip;
    private EditText et_id,et_name,et_pwd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et_id=(EditText)findViewById(R.id.et_id);
        et_name=(EditText)findViewById(R.id.et_name);
        et_pwd=(EditText)findViewById(R.id.et_pwd);

        skip=(Button)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MyActivity.this,Main.class);
                startActivity(intent);
                finish();
            }
        });

        btn_sc=(Button)findViewById(R.id.btn_sc);
        btn_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id.getText().toString().isEmpty()){
                    Toast.makeText(MyActivity.this,"没有id",Toast.LENGTH_LONG).show();
                    return;
                }
                UserDatabaseHelper mydatabase=UserDatabaseHelper.getInstance(getApplicationContext());
                long flag=mydatabase.deleteUsersByUid(et_id.getText().toString());

                if(flag>0){
                    Toast.makeText(MyActivity.this,"数据删除成功",Toast.LENGTH_LONG).show();
                }else if(flag==0){
                    Toast.makeText(MyActivity.this,"数据删除失败",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MyActivity.this,"数据异常，请联系管理员",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_xg=(Button)findViewById(R.id.btn_xg);
        btn_xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id.getText().toString().isEmpty()){
                    Toast.makeText(MyActivity.this,"没有id",Toast.LENGTH_LONG).show();
                    return;
                }
                Users users=new Users(et_id.getText().toString(),et_name.getText().toString(),et_pwd.getText().toString());
                UserDatabaseHelper mydatabase=UserDatabaseHelper.getInstance(getApplicationContext());
                long flag=mydatabase.UpdateUsersByUid(users);

                if(flag>0){
                    Toast.makeText(MyActivity.this,"数据修改成功",Toast.LENGTH_LONG).show();
                }else if(flag==0){
                    Toast.makeText(MyActivity.this,"数据修改失败",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MyActivity.this,"数据异常，请联系管理员",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_like=(Button)findViewById(R.id.btn_like);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Users> list=new ArrayList<Users>();
                list=UserDatabaseHelper.getInstance(getApplicationContext()).rawQueryUsersByUidorUname(et_id.getText().toString(),et_name.getText().toString());
                if(list==null){
                    Toast.makeText(MyActivity.this,"SQL格式有问题",Toast.LENGTH_LONG).show();
                }else if(list.size()>0){
                    for(int i=0;i<list.size();i++){
                        Users users=list.get(i);
                        Log.v(String.valueOf(i+1), users.getUid()+" "+ users.getUname()+" "+ users.getUpwd());
                    }

                    }else{
                    Toast.makeText(MyActivity.this,"没有数据",Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_tgall=(Button)findViewById(R.id.btn_tgall);
        btn_tgall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Users> list=new ArrayList<Users>();
                list=UserDatabaseHelper.getInstance(getApplicationContext()).queryAllUsers();
                if(list==null){
                    Toast.makeText(MyActivity.this,"SQL格式有问题",Toast.LENGTH_LONG).show();
                }else if(list.size()>0){
                    for(int i=0;i<list.size();i++){
                        Users users=list.get(i);
                        Log.v(String.valueOf(i+1), users.getUid()+" "+ users.getUname()+" "+ users.getUpwd());
                    }
                }

            }
        });
        btn_qd=(Button)findViewById(R.id.btn_qd);
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users users=new Users(et_id.getText().toString(),et_name.getText().toString(),et_pwd.getText().toString());
                UserDatabaseHelper mydatabase=UserDatabaseHelper.getInstance(getApplicationContext());
                long flag=mydatabase.add(users);
                if(flag>0){
                    Toast.makeText(MyActivity.this,"成功添加数据",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_tg=(Button)findViewById(R.id.btn_tg);
        btn_tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users users=new Users();
                users=UserDatabaseHelper.getInstance(getApplicationContext()).queryUsersByUid(et_id.getText().toString().trim());
                Toast.makeText(MyActivity.this,users.getUid()+","+users.getUname()+","+users.getUpwd(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
