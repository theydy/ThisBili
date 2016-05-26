package com.firstcode.rough.thisbili.Util;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.firstcode.rough.thisbili.Class.Video;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rough on 2016/5/22.
 */
public class Util {
    public static void handleResponse(final String response,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Log.d("helloworld",response);
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("list");
                    JSONObject list = array.getJSONObject(0);
                    Video video = new Video();
                    URL url1=null;
                    if(!object.getString("img").equals("")){
                        url1 = new URL(object.getString("img"));
                        Bitmap bitmap1 = BitmapFactory.decodeStream(url1.openStream());
                        video.setImg(bitmap1);
                    }else{
                        video.setImg(null);
                    }
                    if(!object.getString("upimg").equals("")){
                        URL url2 = new URL(object.getString("upimg"));
                        Bitmap bitmap2 = BitmapFactory.decodeStream(url2.openStream());
                        video.setUpimg(bitmap2);
                    }else{
                        video.setUpimg(null);
                    }
                    video.setAv(object.getString("av"));
                    video.setTitle(object.getString("title"));
                    video.setDesc(object.getString("desc"));
                    video.setUp(object.getString("up"));
                    video.setUpsign(object.getString("upsign"));
                    video.setMaxpage(object.getInt("maxpage"));
                    video.setCid(list.getString("CID"));
                    video.setMp3url(list.getString("Mp3Url"));
                    video.setMp3Length(list.getString("Mp3Length"));
                    video.setMp4url(list.getString("Mp4Url"));
                    video.setMp4Length(list.getString("Mp4Length"));
                    if(listener!=null){
                        listener.onInfo(video);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                    listener.onError(e);
                }
            }
        }).start();
    }
    public static void downLoad(final String address, final String type,
                                final NotificationCompat.Builder builder, final NotificationManager manager,
                                final int msgNum, final String av,final int sumLength){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpURLConnection connection = (HttpURLConnection) (new URL(address)).openConnection();
                    InputStream in = connection.getInputStream();
                    String dirName= Environment.getExternalStorageDirectory()+"/ThisBili/Mydownload";
                    String fileName=dirName+"/"+av+ type;
                    Notification notification;
                    File f = new File(dirName);
                    if(!f.exists()){
                        f.mkdirs();
                    }
                    File file = new File(fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    byte[] bs = new byte[1024*2];
                    int len;
                    int countlen=0;
                    OutputStream out = new FileOutputStream(file);
                    while((len=in.read(bs))!= -1){
                        out.write(bs,0,len);
                        countlen+=len;
//                        builder.setProgress(sumLength,countlen%sumLength,false);
//                        notification=builder.build();
//                        notification.flags = Notification.FLAG_AUTO_CANCEL;
//                        manager.notify(msgNum,notification);
                    }
                    builder.setContentText("下载完毕").setProgress(100,100,true);
                    notification=builder.build();
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    manager.notify(msgNum,notification);
                    in.close();
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
