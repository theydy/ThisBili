package com.firstcode.rough.thisbili.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.TabFactory;
import com.firstcode.rough.thisbili.fragment.downloadFragment;
import com.firstcode.rough.thisbili.fragment.homeFragement;

public class MainActivity extends AppCompatActivity{

    private LinearLayout backGround;
    private EditText avNum;
    private Button check;
    private String TAG="bilibilijj";

    private TabHost tabHost;
    private Fragment contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backGround = (LinearLayout) findViewById(R.id.background);
        getSupportActionBar().hide();

        tabHost = (TabHost)findViewById(R.id.tab_host);
        tabHost.setup();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction();
                if(TextUtils.equals("first",tabId)){
                    //
                    contents = new homeFragement();
                }else if(TextUtils.equals("second",tabId)){
                    //
                    contents = new downloadFragment();
                }
                fragmentTransaction.replace(android.R.id.tabcontent, contents,"frag");
                fragmentTransaction.commit();
            }
        });
        tabHost.addTab(tabHost.newTabSpec("first").setIndicator("主页").setContent(new TabFactory(this)));
        tabHost.addTab(tabHost.newTabSpec("second").setIndicator("下载").setContent(new TabFactory(this)));

//        背景图片透明度
        backGround.getBackground().setAlpha(100);

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

}
