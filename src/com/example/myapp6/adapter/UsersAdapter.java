package com.example.myapp6.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapp6.R;
import com.example.myapp6.models.Users;

import java.util.List;

/**
 * Created by pc on 2016/4/7.
 */
public class UsersAdapter extends ArrayAdapter<Users> {
    private static class ViewHolder{
        TextView uid;
        TextView uname;
        TextView upwd;
    }
    public UsersAdapter(Context context, int resource, List<Users> list) {
        super(context, resource,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Users users=getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.faxian_item,parent,false);
            viewHolder.uid=(TextView) convertView.findViewById(R.id.uid);
            viewHolder.uname=(TextView) convertView.findViewById(R.id.uname);
            viewHolder.upwd=(TextView) convertView.findViewById(R.id.upwd);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.uid.setText(users.getUid());
        viewHolder.uname.setText(users.getUname());
        viewHolder.upwd.setText(users.getUpwd());
        if(position==6){
            viewHolder.uname.setText("随便");
            viewHolder.uname.setTextColor(Color.RED);
        }else{
            viewHolder.uname.setTextColor(Color.WHITE);
        }
        return convertView;
    }
}
