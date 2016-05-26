package com.firstcode.rough.thisbili.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rough on 2016/5/26.
 */
public class MD5 {
   public static String getMd5(String request){
        try{
            byte[] btInput = request.getBytes("UTF8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md=mdInst.digest();
            int j = md.length;
            String result="";
            for (int i = 0; i < j; i++) {
                result += Integer.toHexString(
                        (0x000000ff & md[i]) | 0xffffff00).substring(6);
            }
            return result;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
