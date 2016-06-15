package com.example.myapp6.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.myapp6.R;
import com.example.myapp6.adapter.UsersAdapter;
import com.example.myapp6.models.InitTask;
import com.example.myapp6.models.Users;
import com.example.myapp6.utils.DialogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/3/30.
 */
public class FragmentFaxian extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.faxian,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toast.makeText(getActivity(),getArguments().getString("key1","")+" "+getArguments().getInt("key2",0),Toast.LENGTH_LONG).show();
        ListView lv_faxian=(ListView) view.findViewById(R.id.lv_faxian);
        ProgressBar pb_loading=(ProgressBar)view.findViewById(R.id.pb_loading1);
        Dialog dialog= DialogUtils.getLoadingDialog(getActivity());
        String url="http://52.38.232.240:8080/jfinal_gradle/users";
        new InitTask(getActivity(),pb_loading,lv_faxian,1,dialog).execute(url);

       /* List<Users> list=new ArrayList<>();
        for(int i=1;i<100;i++){
            Users users=new Users();
            users.setUid("100"+i);
            users.setUname("name"+i);
            users.setUpwd("pwd"+i);
            list.add(users);
        }
        ArrayAdapter<Users> adapter=new UsersAdapter(getActivity(),R.layout.tongxunlu_item,list);
        lv_faxian.setAdapter(adapter);*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if(hidden==true){
            Log.v("hidden","隐藏发现Fragment");
        }
        if(hidden==false){
            Log.v("hidden","显示发现Fragment");
        }*/
    }
   /* class UsersAdapter extends ArrayAdapter<Users>{
        public UsersAdapter(Context context, int resource, List<Users> list) {
            super(context, resource, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Users users=getItem(position);
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(getContext());
                convertView=inflater.inflate(R.layout.faxian_item,parent,false);
            }
            TextView uid=(TextView) convertView.findViewById(R.id.uid);
            TextView uname=(TextView) convertView.findViewById(R.id.uname);
            TextView upwd=(TextView) convertView.findViewById(R.id.upwd);
            uid.setText(users.getUid());
            uname.setText(users.getUname());
            upwd.setText(users.getUpwd());
            return convertView;
        }
    }*/
}
