package com.zwt.base.common.utils;


import android.app.Activity;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by ZWT on 2016/7/11.
 */
public class ThirdLoginUtils {

    public static void init(){
        PlatformConfig.setWeixin("wxfcedb10a01b7dbc0", "ec2f6326e217c270a3a00047b2b8fcc8");
        //微信 appid appsecret
        PlatformConfig.setQQZone("1105533460", "6t6CqahPpkDEo5mC");
        // QQ和Qzone appid appkey
    }

    public static void loginByQQ(Activity cx, UMAuthListener umAuthListener){
        init();
        UMShareAPI mShareAPI = UMShareAPI.get(cx);
        /**begin invoke umeng api**/
        mShareAPI.deleteOauth(cx, SHARE_MEDIA.QQ, umAuthListener);
    }

    public static void loginByWX(Activity cx, UMAuthListener umAuthListener){
        init();
        UMShareAPI mShareAPI = UMShareAPI.get(cx);
        /**begin invoke umeng api**/
        mShareAPI.deleteOauth(cx, SHARE_MEDIA.WEIXIN, umAuthListener);
    }
}
