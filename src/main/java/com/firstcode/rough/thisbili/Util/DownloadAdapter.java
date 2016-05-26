package com.firstcode.rough.thisbili.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firstcode.rough.thisbili.Class.FileVideo;
import com.firstcode.rough.thisbili.R;

import java.util.List;

/**
 * Created by Rough on 2016/5/26.
 */

public class DownloadAdapter extends ArrayAdapter<FileVideo>{

    private int resourceId ;
    private Context context;
    public DownloadAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileVideo video = getItem(position);
        View view;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else{
            view = convertView;
        }
        TextView downloadName= (TextView) view.findViewById(R.id.download_name);
        TextView downloadSize= (TextView) view.findViewById(R.id.download_size);
        TextView downloadPath= (TextView) view.findViewById(R.id.download_path);
        downloadName.setText(video.getName());
        downloadSize.setText(video.getSize());
        downloadPath.setText(video.getPath());
        return view;
    }
}
