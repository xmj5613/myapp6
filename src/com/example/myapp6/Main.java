package com.example.myapp6;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.myapp6.fragment.FragmentFaxian;
import com.example.myapp6.fragment.FragmentTongxunlu;
import com.example.myapp6.fragment.FragmentWeixin;
import com.example.myapp6.fragment.FragmentWo;
import com.example.myapp6.models.HttpUtils;
import com.example.myapp6.models.ResponseData;
import com.example.myapp6.utils.JsonUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/3/29.
 */
public class Main extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    FragmentWeixin weixin=null;
    FragmentTongxunlu tongxunlu=null;
    FragmentFaxian faxian=null;
    FragmentWo wo=null;
    RadioGroup rg_navigation;
    private String parentdata="parent数据";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dibumenu);
        rg_navigation=(RadioGroup)findViewById(R.id.rg_navigation);
        rg_navigation.setOnCheckedChangeListener(this);
        weixin=new FragmentWeixin();
        ActionBar actionBar=getActionBar();
        actionBar.setTitle("微信应用");
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        if(weixin.isAdded()){
            ft.show(weixin).commit();
        }else{
            ft.replace(R.id.fl_container,weixin).commit();
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
        if(weixin!=null){
            if(weixin.isAdded())
                ft.hide(weixin);
        }
        if(tongxunlu!=null){
            if(tongxunlu.isAdded())
                ft.hide(tongxunlu);
        }
        if(faxian!=null){
            if(faxian.isAdded())
                ft.hide(faxian);
        }
        if(wo!=null){
            if(wo.isAdded())
                ft.hide(wo);
        }
        if(id==R.id.re_weixin){
            if(weixin==null)
                weixin=new FragmentWeixin();
            if(weixin.isAdded())
                ft.show(weixin);
            else
            ft.add(R.id.fl_container,weixin);
        }
        if(id==R.id.re_tongxunlu){
            if(tongxunlu==null)
                tongxunlu=new FragmentTongxunlu();
            if(tongxunlu.isAdded())
                ft.show(tongxunlu);
            else
                ft.add(R.id.fl_container, tongxunlu);
        }
        if(id==R.id.re_faxian){
            if(faxian==null) {
                faxian = new FragmentFaxian();
               /* Bundle bundle = new Bundle();
                bundle.putString("key1", "这是传递给发现Fragment的字符串");
                bundle.putInt("key2", 110);
                faxian.setArguments(bundle);*/
            }
            if(faxian.isAdded())
                ft.show(faxian);
            else
                ft.add(R.id.fl_container,faxian);
        }
        if(id==R.id.re_wo){
            if(wo==null) {
                wo = new FragmentWo();
               // wo.showMsg("这个是 parent字符");
            }
            if(wo.isAdded())
                ft.show(wo);
            else
                ft.add(R.id.fl_container,wo);
        }
        ft.commit();

        /*if(id==R.id.re_weixin){
            if(weixin==null)
                weixin=new FragmentWeixin();
                ft.replace(R.id.fl_container,weixin).commit();
        }
        if(id==R.id.re_tongxunlu){
            if(tongxunlu==null)
                tongxunlu=new FragmentTongxunlu();
            ft.replace(R.id.fl_container,tongxunlu).commit();
        }
        if(id==R.id.re_faxian){
            if(faxian==null)
                faxian=new FragmentFaxian();
            ft.replace(R.id.fl_container,faxian).commit();
        }
        if(id==R.id.re_wo){
            if(wo==null)
                wo=new FragmentWo();
            ft.replace(R.id.fl_container,wo).commit();
        }*/
    }
    public String getParentdata(){
        return parentdata;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId()==R.id.menu_chang_other){
            Toast.makeText(getApplicationContext(),"点击了修改其他",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.menu_chang_pwd){
            String url="http://52.38.232.240:8080/jfinal_gradle/apkusers/update";
            new PostTask().execute(url,"1002","12345");
        }
        return true;
    }
    class PostTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params=new ArrayList<>();
            NameValuePair nvp1=new BasicNameValuePair("users.uuid",strings[1]);
            NameValuePair nvp2=new BasicNameValuePair("users.userPwd",strings[2]);
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
                    Toast.makeText(getApplicationContext(),"密码修改成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"密码修改失败",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
