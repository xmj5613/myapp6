package com.example.myapp6.models;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by pc on 2016/5/12.
 */
public class HttpUtils {
    public static String getString(String address) {
        StringBuffer resultStr=null;
        InputStream is=null;
        try {
            resultStr=new StringBuffer();
            URL myurl=new URL(address);
            HttpURLConnection connection=(HttpURLConnection) myurl.openConnection();
            is=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String readStr="";
            while ((readStr=br.readLine())!=null){
                resultStr.append(readStr);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr.toString();
    }
    public static String postData(String url, List<NameValuePair> params){
        try {
            HttpEntity requesEntity=new UrlEncodedFormEntity(params);
            HttpPost httpPost=new HttpPost(url);
            httpPost.setEntity(requesEntity);
            HttpClient httpClient=new DefaultHttpClient();
            HttpResponse httpResponse=httpClient.execute(httpPost);
            if (httpResponse==null) return "";
            HttpEntity responseEntity=httpResponse.getEntity();
            InputStream inputStream=responseEntity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer=new StringBuffer();
            String line="";
            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
