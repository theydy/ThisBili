package com.firstcode.rough.thisbili.Util;

import com.firstcode.rough.thisbili.Class.Video;

/**
 * Created by Rough on 2016/5/21.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onInfo(Video video);
    void onError(Exception e);
}
