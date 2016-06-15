package com.example.myapp6.adapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.myapp6.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2016/4/11.
 */
public class MySimpleAdapter extends SimpleAdapter {
    private LayoutInflater inflater;
    private List<Map<String, String>> data;
    private static class ViewHolder{
        ImageView iv_img;
        TextView tv_name;
        TextView tv_phone;
    }
    public MySimpleAdapter(Context context, List<Map<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.tongxunlu_item,parent,false);
           // viewHolder.iv_img=(ImageView)convertView.findViewById(R.id.iv_img);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            //viewHolder.tv_phone=(TextView)convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
    } else{
        viewHolder=(ViewHolder) convertView.getTag();
    }
        viewHolder.iv_img.setImageResource(Integer.parseInt(data.get(position).get("img")));
        viewHolder.tv_name.setText(data.get(position).get("name"));
        viewHolder.tv_phone.setText(data.get(position).get("phone"));
        return super.getView(position,convertView,parent);
    }
}
