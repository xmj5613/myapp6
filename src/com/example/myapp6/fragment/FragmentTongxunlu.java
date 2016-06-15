package com.example.myapp6.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.myapp6.MyComparator;
import com.example.myapp6.R;
import com.example.myapp6.adapter.ContactAdapter;
import com.example.myapp6.adapter.MySimpleAdapter;
import com.example.myapp6.models.Contact;
import com.example.myapp6.views.SideIndex;

import java.util.*;

/**
 * Created by pc on 2016/3/30.
 */
public class FragmentTongxunlu extends Fragment {
    private ListView lv_tongxunlu;
    SideIndex sideIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tongxunlu,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_tongxunlu=(ListView) view.findViewById(R.id.lv_tongxunlu);
        sideIndex=(SideIndex)view.findViewById(R.id.sideIndex);
        /*//创建list<Map><String,String>对象
        List<Map<String,String>> data=new ArrayList<>();
        //组织数据
        for(int i=1;i<=100;i++){
            Map<String,String> map=new HashMap<>();
            map.put("img",String.valueOf(R.drawable.ic_launcher));
            map.put("name","name"+i);
            map.put("phone","1331451231"+i);
            data.add(map);
        }
        //创建from字符串数组
        String[]from=new String[]{"img","name","phone"};
        //创建to整型数组
        int[]to=new int[]{R.id.iv_img,R.id.tv_name,R.id.tv_phone};
        //创建数据源对y象
        MySimpleAdapter adapter=new MySimpleAdapter(getActivity(),data,R.layout.tongxunlu_item,from,to);
        //ListView指定数据源*/
        Random random=new Random();
        String str=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Contact> list=new ArrayList<>();
        for(int i=0;i<100;i++){
            char ch=str.charAt(random.nextInt(26));
            Contact contact=new Contact(ch+"",""+ch+i);
            list.add(contact);
        }
        Collections.sort(list,new MyComparator());
        ContactAdapter adapter=new ContactAdapter(getActivity(),list);
        lv_tongxunlu.setAdapter(adapter);
        sideIndex.setListView(lv_tongxunlu);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if(hidden==true){
            Log.v("hidden","隐藏通讯录Fragment");
        }
        if(hidden==false){
            Log.v("hidden","显示通讯录Fragment");
        }*/
    }
}
