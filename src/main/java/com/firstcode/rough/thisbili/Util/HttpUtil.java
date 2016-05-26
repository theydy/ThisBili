package com.firstcode.rough.thisbili.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rough on 2016/5/21.
 */
public class HttpUtil {
    public static void sendRequestToGetCid(final String avNum,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL address = new URL("http://www.bilibilijj.com/Api/AvToCid/"+avNum);
                    connection = (HttpURLConnection) address.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(response.toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
//    public static void test(final String url, final HttpCallBackListener listener){
//        new Thread(new Runnable() {
//
//            HttpURLConnection connection=null;
//            @Override
//            public void run() {
//                int ts = (int) new Date().getTime();
//                try{
//                    Log.d("helloworld","--------------------------------------------");
//                    URL address = new URL("http://api.bilibili.cn/view"+url+"&ts="+ts);
//                    Log.d("sign_lll", address.toString());
//                    connection = (HttpURLConnection) address.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(5000);
//                    connection.setReadTimeout(5000);
//                    connection.setRequestProperty("user-agent","ThisBiLi Android Client/1.0.0(hh@qq.com)");
//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while((line=reader.readLine())!=null){
//                        response.append(line);
//                    }
//                    if(listener!=null){
//                        listener.onFinish(response.toString());
//                    }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }finally{
//                    if(connection!=null){
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
}
