package com.example.myapp6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.myapp6.models.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/3/23.
 */
public class UserDatabaseHelper extends SQLiteOpenHelper {
    //定义数据库变量名
    private static final String  DATABASE_NAME="mydata";
    //定义数据库版本号
    private static final int DATABASE_VERSION=1;
    //定义数据表变量名
    private static final String TABLE_USERS="users";
    //定义表字段名
    private static final String USERS_UID="uid";
    private static final String USERS_UNAME="uname";
    private static final String USERS_UPWD="upwd";
    private static UserDatabaseHelper myInstance;
    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //定义创建数据表的SQL语句
        String SQL="CREATE TABLE "+TABLE_USERS+
                "("+
                USERS_UID+" TEXT PRIMARY KEY,"+
                USERS_UNAME+" TEXT,"+
                USERS_UPWD+" TEXT"+
                ")";
        //执行SQL语句
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    //版本变更时候调用的方法，先删除以前的数据表，再重新创建被删的数据表
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oidVersion, int newVersion) {
        //判断是否存在该数据表,存在则删除
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_USERS+" IF EXISTS");
        //执行创建表的方法
        onCreate(sqLiteDatabase);
        //！！注意该方法需要通过触发getWritableDatabase()或getReadableDatabase()才执行！！！！
    }
    public static synchronized UserDatabaseHelper getInstance(Context context){
        if(null==myInstance){
            myInstance=new UserDatabaseHelper(context.getApplicationContext());
        }
        return myInstance;
    }
    //定义添加数据进数据表的方法
    public long add(Users users){
        long flag=0;
        SQLiteDatabase db=getWritableDatabase();
        //事务开启
        db.beginTransaction();
        try {
            //该类相当于为key-value
            ContentValues values=new ContentValues();
            values.put(USERS_UID,users.getUid());
            values.put(USERS_UNAME,users.getUname());
            values.put(USERS_UPWD,users.getUpwd());
            //执行SQLiteDatabase中的添加数据方法，并返回一个long的值,-1为添加数据失败,大于0则为添加成功
            flag=db.insert(TABLE_USERS,null,values);
            //设置事务
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.v("inset error",e.getMessage());
        }
        finally {
            //关闭事务和数据库
            db.endTransaction();
            db.close();
        }
        return flag;
    }
    public Users rawqueryUsersByUid(String uid){
        Users users=new Users();
       String query=String.format("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_UID+"=%s",uid);
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                users.setUid(cursor.getString(cursor.getColumnIndex(USERS_UID)));
                users.setUname(cursor.getString(cursor.getColumnIndex(USERS_UNAME)));
                users.setUpwd(cursor.getString(cursor.getColumnIndex(USERS_UPWD)));
            }
        }catch(Exception e){
            Log.v("query error",e.getMessage());
        }
        finally {
            if(cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
            db.close();
        }
        return users;
    }
    public Users queryUsersByUid(String uid){
        Users users=new Users();
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.query(TABLE_USERS,null,USERS_UID+"=?",new String[]{uid},null,null,null);
            if(cursor.moveToFirst()){
                users.setUid(cursor.getString(cursor.getColumnIndex(USERS_UID)));
                users.setUname(cursor.getString(cursor.getColumnIndex(USERS_UNAME)));
                users.setUpwd(cursor.getString(cursor.getColumnIndex(USERS_UPWD)));
            }
        }catch (Exception e){
            Log.v("query error",e.getMessage());
        }
        finally {
            if(null!=cursor&&!cursor.isClosed()){
                cursor.close();
            }
            db.close();
        }
        return users;
    }

    public List<Users> queryAllUsers(){
       List<Users> list=new ArrayList<>();
        String QUERY_USERS=String.format("SELECT * FROM "+TABLE_USERS);
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(QUERY_USERS,null);
            if(cursor.moveToFirst()){
                do {
                    Users users = new Users();
                    users.setUid(cursor.getString(cursor.getColumnIndex(USERS_UID)));
                    users.setUname(cursor.getString(cursor.getColumnIndex(USERS_UNAME)));
                    users.setUpwd(cursor.getString(cursor.getColumnIndex(USERS_UPWD)));
                    list.add(users);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.v("query error",e.getMessage());
        }
        finally {
            if(null!=cursor&&!cursor.isClosed()){
                cursor.close();
            }
            db.close();
        }
        return list;
    }

    public List<Users> rawQueryUsersByUidorUname(String uid,String uname){
        List<Users> list=new ArrayList<>();
        String QUERY_USERS=String.format("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_UID+" LIKE %s OR "+USERS_UNAME+" LIKE %s","'%"+uid+"%'","'%"+uname+"%'");
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(QUERY_USERS,null);
            if(cursor.moveToFirst()){
                do {
                    Users users = new Users();
                    users.setUid(cursor.getString(cursor.getColumnIndex(USERS_UID)));
                    users.setUname(cursor.getString(cursor.getColumnIndex(USERS_UNAME)));
                    users.setUpwd(cursor.getString(cursor.getColumnIndex(USERS_UPWD)));
                    list.add(users);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.v("query error",e.getMessage());
            return null;
        }
        finally {
            if(null!=cursor&&!cursor.isClosed()){
                cursor.close();
            }
            db.close();
        }
        return list;
    }
    public long UpdateUsersByUid(Users users){
        if(users==null){
            return 0;
        }
        ContentValues values=new ContentValues();
        values.put(USERS_UID,users.getUid());
        values.put(USERS_UNAME,users.getUname());
        values.put(USERS_UPWD,users.getUpwd());
        long flaot=0;
        SQLiteDatabase db=getWritableDatabase();
        db.beginTransaction();
        try{
            flaot=db.update(TABLE_USERS,values,USERS_UID+"=?",new String[]{users.getUid()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.v("update error",e.getMessage());
            return -1;
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return flaot;
    }

    public long deleteUsersByUid(String uid){
        long flaot=0;
        SQLiteDatabase db=getWritableDatabase();
        db.beginTransaction();
        try{
            flaot=db.delete(TABLE_USERS,USERS_UID+"=?",new String[]{uid});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.v("delete error",e.getMessage());
            return -1;
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return flaot;
    }
}
