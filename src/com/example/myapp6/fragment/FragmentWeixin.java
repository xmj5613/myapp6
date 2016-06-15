package com.example.myapp6.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp6.Main;
import com.example.myapp6.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/3/30.
 */
public class FragmentWeixin extends Fragment {
    private ListView lv_weixin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weixin,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView=(TextView) view.findViewById(R.id.tv_msg);
        Toast.makeText(getActivity(),new Main().getParentdata(),Toast.LENGTH_LONG).show();
        lv_weixin=(ListView)view.findViewById(R.id.lv_weixin);
        //组织数据
        Resources res=getResources();
        String[]list=res.getStringArray(R.array.myAraay);
       /* String[] list=new String[20];
        for(int i=0;i<20;i++){
            list[i]="李"+(i+1);
        }*/
        /*List<String> list=new ArrayList<>();
        for(int i=1;i<=20;i++){
            list.add("张"+i);
        }*/
        //创建ArrayAdapter对象
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.weixin_item,R.id.tv_name,list);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        lv_weixin.setAdapter(adapter);
    }
}
