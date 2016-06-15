package com.example.myapp6.views;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pc on 2016/4/19.
 */
public class MyOndraw extends View {
    public MyOndraw(Context context) {
        super(context);
    }

    public MyOndraw(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyOndraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        //绘制圆形
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredWidth()/2,220,paint);

        //绘制直线
        //canvas.drawLine(30,30,100,100,paint);
        //canvas.drawLines(new float[]{30,30,60,60,100,100,150,150,200,200,250,250,300,300,350,350},paint);
        //canvas.drawLines(new float[]{30,30,60,60,100,100,150,150,200,200,250,250,300,300,350,350},4,8,paint);
        //绘制矩形
        //canvas.drawRect(100,100,300,500,paint);
        //RectF rect=new RectF(100,100,300,500);
        //canvas.drawRect(rect,paint);
        //canvas.drawRoundRect(rect,20,20,paint);
        //绘制点
        //canvas.drawPoint(100,100,paint);
        //canvas.drawPoints(new float[]{30,30,60,60,100,100,150,150,200,200,250,250,300,300,350,350},paint);
        //canvas.drawPoints(new float[]{30,30,60,60,100,100,150,150,200,200,250,250,300,300,350,350},4,4,paint);

        //绘制椭圆，该椭圆为rectF对象形成的矩形的内椭圆
        //RectF rectF=new RectF(100,200,300,500);
        //canvas.drawOval(rectF,paint);
        //绘制圆弧
        //RectF rectF=new RectF(100,100,150,150);
        //canvas.drawArc(rectF,0,180,false,paint);false不闭口
        //canvas.drawArc(rectF,0,180,true,paint);
        //绘制路径
        /*Path linePath=new Path();
        //绘制直线路径
        linePath.moveTo(30,30);//起始点
        linePath.lineTo(200,200);
        linePath.lineTo(300,150);
        linePath.lineTo(150,100);
        linePath.close();//将起始点去最后一个点连接
        canvas.drawPath(linePath,paint);*/
        //绘制矩形路径
        /*Path rectPath=new Path();
        int x=getMeasuredWidth()/2;
        int y=getMeasuredHeight()/2;
        rectPath.addRect(x-200,y-200,x+200,y+200, Path.Direction.CW);//CW表示顺时针 ,CCW表示逆时针
        canvas.drawPath(rectPath,paint);
        String str="android编程入门教学";
        paint.setColor(Color.GREEN);
        paint.setTextSize(50);
        canvas.drawTextOnPath(str,rectPath,0,0,paint);//在矩形路径上绘制文字
        invalidate();*///重新绘制
    }
}
