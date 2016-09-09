package com.zwt.base.common.utils;

import android.app.Activity;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * Created by ZWT on 2016/7/21.
 */
public class ShareManager {
    public static void init(){
        PlatformConfig.setWeixin("wxfcedb10a01b7dbc0", "ec2f6326e217c270a3a00047b2b8fcc8");
        //微信 appid appsecret
        PlatformConfig.setQQZone("1105582309", "JrtpP6EzTTkS2T8P");
        // QQ和Qzone appid appkey
    }

    public static void shareDefault(Activity cx, SHARE_MEDIA type, UMShareListener umShareListener){
        shareToSns(cx,type,"仪器圈，免费找货~~", "http://www.yiqiquan.net", "http://img3.duitang.com/uploads/item/201607/21/20160721094536_P5dW4.thumb.700_0.png", "找货就上仪器圈,一个专业的找货平台。",umShareListener);
    }

    public static void shareToSns(Activity cx, SHARE_MEDIA type, String title, String tagetUrl, String imgUrl, String content, UMShareListener umShareListener){
        init();
        new ShareAction(cx).setPlatform(type).setCallback(umShareListener)
                .withTitle(title)
                .withTargetUrl(tagetUrl)
                .withMedia(new UMImage(cx, imgUrl))
                .withText(content)
                .share();
    }

    public static void shareToQQ(Activity cx, String title, String tagetUrl, String imgUrl, String content, UMShareListener umShareListener){
        init();
        new ShareAction(cx).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                .withTitle(title)
                .withTargetUrl(tagetUrl)
                .withMedia(new UMImage(cx, imgUrl))
                .withText(content)
                .share();
    }

    public static void shareToWxFriend(Activity cx, String title, String tagetUrl, String imgUrl, String content, UMShareListener umShareListener){
        init();
        new ShareAction(cx).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                .withTitle(title)
                .withTargetUrl(tagetUrl)
                .withMedia(new UMImage(cx, imgUrl))
                .withText(content)
                .share();
    }

    public static void shareToWXCircle(Activity cx, String title, String tagetUrl, String imgUrl, String content, UMShareListener umShareListener){
        init();
        new ShareAction(cx).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                .withTitle(title)
                .withTargetUrl(tagetUrl)
                .withMedia(new UMImage(cx, imgUrl))
                .withText(content)
                .share();
    }
}
