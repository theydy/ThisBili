package com.firstcode.rough.thisbili.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private Button check;
    private RelativeLayout infobackground;
    private Video video=null;
    private String mp3url=null;
    private String mp4url=null;
    private String av=null;
    private String TAG="bilibilijj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_info);
        infobackground = (RelativeLayout) findViewById(R.id.infobackground);
        infobackground.getBackground().setAlpha(100);
        title = (TextView) findViewById(R.id.title);
        desc =(TextView) findViewById(R.id.desc);
        img =(ImageView) findViewById(R.id.img);
        check =(Button) findViewById(R.id.check);
        check.setOnClickListener(this);
        read(getIntent());
    }

    public void read(Intent intent){
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
                                    title.setText(video.getTitle());
                                    desc.setText(video.getDesc());
                                    img.setImageBitmap(video.getImg());
                                    check.setText("下载:视频大小："+video.getMp3Length()+"k");
                                    check.setVisibility(View.VISIBLE);
                                    av = video.getAv();
                                    mp3url = video.getMp3url();
                                    mp4url = video.getMp4url();
                                    Log.d(TAG,"title = "+video.getTitle());
                                    Log.d(TAG,"desc = "+video.getDesc());
                                    Log.d(TAG,"mp3length = "+video.getMp3Length());
                                    Log.d(TAG,"mp3url = "+mp3url);
                                    Log.d(TAG,"mp4url = "+mp4url);
                                }
                            }
                    );
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public static void startThisActivity(Context context, String response){
        Intent intent = new Intent(context, avInfoActivity.class);
        intent.putExtra("response",response);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.check:
                Toast.makeText(this,"开始下载", Toast.LENGTH_SHORT).show();
                Util.downLoad(mp3url,av);
                break;
            default:
                break;
        }
    }
}
