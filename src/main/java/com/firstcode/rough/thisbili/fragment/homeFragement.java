package com.firstcode.rough.thisbili.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firstcode.rough.thisbili.Class.Video;
import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.HttpCallBackListener;
import com.firstcode.rough.thisbili.Util.HttpUtil;
import com.firstcode.rough.thisbili.activity.avInfoActivity;


public class homeFragement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private EditText avNum;
    private Button check;
    private String TAG="bilibilijj";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragement, container ,false);
        avNum = (EditText) view.findViewById(R.id.av_num);
        check = (Button) view.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.check:
                        String av=avNum.getText().toString();
                        HttpUtil.sendRequestToGetCid(av, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String response) {
                                Intent intent = new Intent(getActivity(),avInfoActivity.class);
                                intent.putExtra("response",response);
                                getActivity().startActivityForResult(intent,1);
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
        });
        return view;
    }

}
