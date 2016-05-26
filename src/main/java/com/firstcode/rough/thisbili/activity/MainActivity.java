package com.firstcode.rough.thisbili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firstcode.rough.thisbili.Class.Video;
import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.HttpCallBackListener;
import com.firstcode.rough.thisbili.Util.HttpUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout backGround;
    private EditText avNum;
    private Button check;
    private String TAG="bilibilijj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        backGround = (LinearLayout) findViewById(R.id.background);
        avNum = (EditText) findViewById(R.id.av_num);
        check = (Button) findViewById(R.id.check);
//        背景图片透明度
        backGround.getBackground().setAlpha(100);
        check.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    Toast.makeText(this,"找不到视频", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check:
                String av=avNum.getText().toString();
//                String appkey="c1b107428d337928";
//                String secretkey="ea85624dfcf12d7cc7b2b3a94fac1f2c";
//                String sign="";
//                sign= MD5.getMd5("appkey="+appkey+"&id="+av+"&page=1"+secretkey);
//                String address = "?appkey="+ appkey +"&id="+av+"&page=1&"+sign;

                HttpUtil.sendRequestToGetCid(av, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String response) {
//                        avInfoActivity.startThisActivity(MainActivity.this, response);
                        Intent intent = new Intent(MainActivity.this , avInfoActivity.class);
                        intent.putExtra("response",response);
                        startActivityForResult(intent,1);
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
