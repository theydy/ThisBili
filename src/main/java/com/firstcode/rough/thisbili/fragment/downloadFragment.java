package com.firstcode.rough.thisbili.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firstcode.rough.thisbili.Class.FileVideo;
import com.firstcode.rough.thisbili.R;
import com.firstcode.rough.thisbili.Util.DownloadAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class downloadFragment extends Fragment {

    private ListView downloadList;
    private DownloadAdapter adapter;
    private List<FileVideo> dataList = new ArrayList<FileVideo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        downloadList =(ListView) view.findViewById(R.id.download_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDataList();
        adapter = new DownloadAdapter(getActivity(),R.layout.item,dataList);
        downloadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileVideo video = dataList.get(position);
                //-------------
                Log.d("helloworld",video.getName());
                String sts=video.getName().substring(video.getName().length()-3,video.getName().length());
                if(sts.equals("mp3")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://"+video.getPath()),"audio/MP3");
                    startActivity(intent);
                }else if(sts.equals("mp4")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //__神秘，为什么用Uri.parse(video.getPath())不行__
                    intent.setDataAndType(Uri.fromFile(new File(video.getPath()))
                            ,"video/mp4");
                    startActivity(intent);
                }
            }
        });
        downloadList.setAdapter(adapter);
    }

    public void initDataList(){
        File filepath = new File(Environment.getExternalStorageDirectory()+"/ThisBili/Mydownload");
        if(Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)){
            File[] files = filepath.listFiles();
            if(files.length>0){
                for(File file :files){
                    FileVideo video = new FileVideo();
                    video.setName(file.getName());
                    video.setPath(file.getPath());
                    video.setSize(file.length()+"");
                    dataList.add(video);
                }
            }
        }
    }
}
