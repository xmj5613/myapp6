package com.example.myapp6.fragment;

import android.animation.*;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp6.R;
import com.example.myapp6.views.MyOndraw;
import com.squareup.picasso.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pc on 2016/3/30.
 */
public class FragmentWo extends Fragment {
    private String data="";
    private Button bt_alpha,btn_scale,btn_rotate,btn_translate,btn_loading;
    private ImageView iv_animation;
    private TextView  tv_animation;
    //private Animation alphaAnimation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wo,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_alpha=(Button)view.findViewById(R.id.bt_alpha);
        btn_scale=(Button)view.findViewById(R.id.btn_scale);
        btn_rotate=(Button)view.findViewById(R.id.btn_rotate);
        btn_translate=(Button)view.findViewById(R.id.btn_translate);
        btn_loading=(Button)view.findViewById(R.id.btn_loading);
        iv_animation=(ImageView)view.findViewById(R.id.iv_animation);
        tv_animation=(TextView)view.findViewById(R.id.tv_animation);
        //给alphaanimation加载动画
        //alphaAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_animation);
        //给按钮注册OnClickListener事件则需要调用startAnimation()方法
       /* bt_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_animation.startAnimation(alphaAnimation);
            }
        });*/
        //给按钮注册OnTouchListener事件调用setAnimation()方法
        /*bt_alpha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                iv_animation.setAnimation(alphaAnimation);
                return false;
            }
        });*/
        final AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setRepeatCount(3);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        bt_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_animation.startAnimation(alphaAnimation);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(iv_animation,View.ALPHA,0.0f,1.0f);
                objectAnimator.setDuration(10000);
                objectAnimator.setRepeatCount(2);
                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                objectAnimator.start();

            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Animation scaleAnimation=AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
                //iv_animation.startAnimation(scaleAnimation);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(iv_animation,View.SCALE_X,0.0f,1.5f,1.0f);
                objectAnimator.setDuration(10000);
                objectAnimator.start();
            }
        });
        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Animation rotateAnimation=AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
                //iv_animation.startAnimation(rotateAnimation);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(iv_animation,View.ROTATION,0.0f,290.0f,0.0f);
                objectAnimator.setDuration(10000);
                objectAnimator.start();
            }
        });
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Animation translateAnimation=AnimationUtils.loadAnimation(getActivity(),R.anim.translate);
                //iv_animation.startAnimation(translateAnimation);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(iv_animation,View.TRANSLATION_X,0.0f,300.0f,100.0f,200.0f,150.0f);
                objectAnimator.setDuration(10000);
                objectAnimator.start();
            }
        });
        btn_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_animation.setImageResource(R.drawable.loading_animation);
                //AnimationDrawable animationDrawable=(AnimationDrawable)iv_animation.getDrawable();
                //alphaAnimation.start();
                AnimatorSet animatorSet=new AnimatorSet();//动画集合
                ObjectAnimator scaleXAnimator=ObjectAnimator.ofFloat(tv_animation,View.SCALE_X,0.0f,1.5f,1.0f);
                scaleXAnimator.setDuration(10000);
                ObjectAnimator scaleYAnimator=ObjectAnimator.ofFloat(tv_animation,View.SCALE_Y,0.0f,1.5f,1.0f);
                scaleYAnimator.setDuration(10000);
                ObjectAnimator objectAnimator=ObjectAnimator.ofInt(tv_animation,"BackgroundColor",0xffabcaaa,0xffff00ff,0xff00ffff,0xffffff00);
                objectAnimator.setDuration(10000);
                objectAnimator.setEvaluator(new ArgbEvaluator());
                List<Animator> list=new ArrayList<Animator>();
                list.add(scaleXAnimator);
                list.add(scaleYAnimator);
                list.add(objectAnimator);
                //animatorSet.playTogether(scaleXAnimator,scaleYAnimator,objectAnimator);
                //animatorSet.playTogether(collection);同时执行多个动画
                animatorSet.playSequentially(list);//有顺序执行动画
                animatorSet.start();
            }
        });
        //Toast.makeText(getActivity(),data,Toast.LENGTH_LONG).show();
    }
    /*public void showMsg(String msg){
        data=msg;
        Log.v("show",data);
    }*/
}
