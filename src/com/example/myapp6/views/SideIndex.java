package com.example.myapp6.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import com.example.myapp6.adapter.ContactAdapter;

/**
 * Created by pc on 2016/4/14.
 */
public class SideIndex extends View {
    char[] letters;
    SectionIndexer sectionIndexer;
    ListView lv_tongxunlu;
    public SideIndex(Context context) {
        super(context);
        initLetters();
    }

    public SideIndex(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


    }

    public SideIndex(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLetters();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int y=(int)event.getY();
        int index=y/(getMeasuredHeight()/letters.length);
        if(index>=letters.length){
            index=letters.length-1;
        }
        if(index<0){
            index=0;
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
            if(sectionIndexer==null){
                sectionIndexer=(SectionIndexer)lv_tongxunlu.getAdapter();
            }
            int position=sectionIndexer.getPositionForSection(letters[index]);
            if(position==-1){
                return true;
            }
            lv_tongxunlu.setSelection(position);
        }
        if(event.getAction()==MotionEvent.ACTION_POINTER_UP){

        }
        return true;
    }
    public void setListView(ListView listView){
        lv_tongxunlu=listView;
        ContactAdapter adapter=(ContactAdapter)listView.getAdapter();
        sectionIndexer=adapter;
    }
    private void initLetters(){
        letters=new char[26];
        for(int i=0;i<26;i++){
            letters[i]=(char)('A'+i);
        }
    }
    protected void onDraw(Canvas canvas){
        Paint paint=new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        int width=getMeasuredWidth()/2;
        int height=getMeasuredHeight()/letters.length;
        for(int i=0;i<letters.length;i++){
            canvas.drawText(String.valueOf(letters[i]),width,(i+1)*height,paint);
        }
        invalidate();
        super.onDraw(canvas);
    }

    }

