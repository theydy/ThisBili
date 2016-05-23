package com.firstcode.rough.thisbili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firstcode.rough.thisbili.Class.Video;
import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.HttpCallBackListener;
import com.firstcode.rough.thisbili.Util.HttpUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout backGround;
    private EditText avNum;
    private Button downLoad;
    private String TAG="bilibilijj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        backGround = (LinearLayout) findViewById(R.id.background);
        avNum = (EditText) findViewById(R.id.av_num);
        downLoad = (Button) findViewById(R.id.download);
//        背景图片透明度
        backGround.getBackground().setAlpha(100);
        downLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download:
                String av=avNum.getText().toString();
                HttpUtil.sendRequestToGetCid(av, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String response) {
                        avInfoActivity.startThisActivity(MainActivity.this, response);
                    }

                    @Override
                    public void onInfo(Video video) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
