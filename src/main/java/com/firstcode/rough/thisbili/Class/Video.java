package com.firstcode.rough.thisbili.Class;

import android.graphics.Bitmap;

/**
 * Created by Rough on 2016/5/22.
 */
public class Video {
    private String av;
    private String title;
    private String desc;
    private Bitmap img;
    private String up;
    private String upsign;
    private Bitmap upimg;
    private int maxpage;
    private String cid;
    private String mp3url;
    private String mp3Length;
    private String mp4url;
    private String mp4Length;

    public void Video(){

    }
    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getUpsign() {
        return upsign;
    }

    public void setUpsign(String upsign) {
        this.upsign = upsign;
    }

    public Bitmap getUpimg() {
        return upimg;
    }

    public void setUpimg(Bitmap upimg) {
        this.upimg = upimg;
    }

    public int getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(int maxpage) {
        this.maxpage = maxpage;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMp3url() {
        return mp3url;
    }

    public void setMp3url(String mp3url) {
        this.mp3url = mp3url;
    }

    public String getMp3Length() {
        return mp3Length;
    }

    public void setMp3Length(String mp3Length) {
        this.mp3Length = mp3Length;
    }

    public String getMp4url() {
        return mp4url;
    }

    public void setMp4url(String mp4url) {
        this.mp4url = mp4url;
    }

    public String getMp4Length() {
        return mp4Length;
    }

    public void setMp4Length(String mp4Length) {
        this.mp4Length = mp4Length;
    }
}
