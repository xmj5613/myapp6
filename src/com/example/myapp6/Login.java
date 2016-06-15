package com.example.myapp6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapp6.activites.RegActivity;
import com.example.myapp6.database.UserDatabaseHelper;
import com.example.myapp6.models.Users;

/**
 * Created by pc on 2016/4/1.
 */
public class Login extends Activity {
    private Button bt_lx,btn_reg,bt_login;
    private EditText et_pwd1,et_id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_id1=(EditText)findViewById(R.id.et_id1);
        et_pwd1=(EditText)findViewById(R.id.et_pwd1);
        bt_login=(Button) findViewById(R.id.bt_login);
        bt_lx=(Button) findViewById(R.id.bt_lx);
        btn_reg=(Button) findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, RegActivity.class);
                startActivity(intent);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id1.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"帐号不能为空！！",Toast.LENGTH_LONG).show();
                }else{
                    if(et_pwd1.getText().toString().isEmpty()){
                        Toast.makeText(Login.this,"密码不能为空！！",Toast.LENGTH_LONG).show();
                    }else {
                        UserDatabaseHelper mydatabase = UserDatabaseHelper.getInstance(getApplicationContext());
                        Users users=new Users();
                        users = mydatabase.queryUsersByUid(et_id1.getText().toString().trim());

                        if(users.getUid()==null){
                            Toast.makeText(Login.this,"帐号不存在，请确认是否正确！！",Toast.LENGTH_LONG).show();
                            et_id1.setText("");
                            et_pwd1.setText("");
                        }else{
                            if(users.getUpwd().toString().trim().equals(et_pwd1.getText().toString().trim())){
                                Intent intent=new Intent(Login.this,MyActivity.class);
                                startActivity(intent);//执行意图
                                finish();//调用该方法后不能返回上一个页面
                            }else{
                                Toast.makeText(Login.this,"密码错误，情重新输入！！",Toast.LENGTH_LONG).show();

                                Log.v("pwd",users.getUpwd().toString().trim());
                                Log.v("pwd",et_pwd1.getText().toString().trim());
                                et_pwd1.setText("");
                            }
                        }
                    }
                }


            }
        });
    }
}
