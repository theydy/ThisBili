package com.firstcode.rough.thisbili.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firstcode.rough.thisbili.Class.Video;
import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.HttpCallBackListener;
import com.firstcode.rough.thisbili.Util.Util;


public class avInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private TextView desc;
    private ImageView img;
    private Button downloadMp3;
    private Button downloadMp4;
    private RelativeLayout infobackground;
    private Video video=null;
    private String mp3url=null;
    private String mp4url=null;
    private float mp3Length=0;
    private float mp4Length=0;
    private ProgressDialog progressDialog=null;
    private String av=null;
    private NotificationManager manager;
    private String TAG="bilibilijj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_info);
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE) ;
        infobackground = (RelativeLayout) findViewById(R.id.infobackground);
        infobackground.getBackground().setAlpha(100);
        title = (TextView) findViewById(R.id.title);
        desc =(TextView) findViewById(R.id.desc);
        img =(ImageView) findViewById(R.id.img);
        downloadMp3 =(Button) findViewById(R.id.download_mp3);
        downloadMp4 =(Button) findViewById(R.id.download_mp4);
        downloadMp3.setOnClickListener(this);
        downloadMp4.setOnClickListener(this);
        showProgress();
        read(getIntent());
    }
    public void showProgress(){
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }
    public void closeProgress(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
    public void read(final Intent intent){
        Util.handleResponse(intent.getStringExtra("response"), new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onInfo(final Video video) {
                if(video!=null){
                    runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    int kb = 1024*1024;
                                    title.setText(video.getTitle());
                                    desc.setText(video.getDesc());
                                    if(video.getImg()!=null){
                                        img.setImageBitmap(video.getImg());
                                    }else{
                                        img.setImageResource(R.drawable.pixiv_2_dh);
                                    }
                                    if(!video.getMp3Length().equals("null")){
                                        mp3Length = Integer.valueOf(video.getMp3Length());
                                        downloadMp3.setText("下载:音频大小："+String.format("%.2f",mp3Length/kb)+"m");
//                                        Log.d(TAG,"!=null mp3length = "+video.getMp3Length());
                                    }else{
                                        downloadMp3.setText("本视频无法下载");
                                    }
                                    if(!video.getMp4Length().equals("null")){
                                         mp4Length = Integer.valueOf(video.getMp4Length());
                                         downloadMp4.setText("下载:视频大小："+String.format("%.2f",mp4Length/kb)+"m");
                                       // Log.d(TAG,"!=null mp4length = "+video.getMp4Length());
                                    }else{
                                        downloadMp4.setText("本视频无法下载");
                                    }
                                    downloadMp3.setVisibility(View.VISIBLE);
                                    downloadMp4.setVisibility(View.VISIBLE);
                                    av = video.getAv();
                                    mp3url = video.getMp3url();
                                    mp4url = video.getMp4url();
                                    closeProgress();
                                    Log.d(TAG,"title = "+video.getTitle());
                                    Log.d(TAG,"desc = "+video.getDesc());
                                    Log.d(TAG,"mp3length = "+video.getMp3Length());
                                    Log.d(TAG,"mp4length = "+video.getMp4Length());
                                    Log.d(TAG,"mp3url = "+mp3url);
                                    Log.d(TAG,"mp4url = "+mp4url);
                                }
                            }
                    );
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgress();
                        Intent intent1 = new Intent();
                        setResult(RESULT_OK,intent1);
                        finish();
                    }
                });
            }
        });
    }

//    public static void startThisActivity(Context context, String response){
//        Intent intent = new Intent(context, avInfoActivity.class);
//        intent.putExtra("response",response);
//    }

    @Override
    public void onClick(View v) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.pixiv_2_xh)
                .setContentTitle("开始下载"+av)
                .setContentText("downloading");
        Intent intent = new Intent(this, downLoadActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(downLoadActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        switch(v.getId()){
            case R.id.download_mp3:

                if(!mp3url.equals("null")&&mp3Length>0){
                    Toast.makeText(this,"开始下载", Toast.LENGTH_SHORT).show();
                    manager.notify(1,notification);
                    Util.downLoad(mp3url, ".mp3", builder,manager,1, av, (int) Math.floor(mp3Length));
                }else{
                    Toast.makeText(this,"无法下载", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.download_mp4:
                if(!mp4url.equals("null")&&mp4Length>0){
                    Toast.makeText(this,"开始下载", Toast.LENGTH_SHORT).show();
                    manager.notify(2,notification);
                    Util.downLoad(mp4url, ".mp4",builder,manager, 2, av, (int) Math.floor(mp4Length));
                }else{
                    Toast.makeText(this,"无法下载", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
