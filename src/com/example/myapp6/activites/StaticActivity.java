package com.example.myapp6.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.myapp6.R;
import com.example.myapp6.services.MyStatiService;
import com.example.myapp6.services.MyTestService;

import java.lang.reflect.AccessibleObject;

/**
 * Created by pc on 2016/5/23.
 */
public class StaticActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMyService();
            }
        });
    }
    protected void launchMyService(){
        Intent intent=new Intent(StaticActivity.this, MyStatiService.class);
        startService(intent);
    }
}
