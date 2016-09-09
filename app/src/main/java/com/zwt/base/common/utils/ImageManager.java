package com.zwt.base.common.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zwt.base.R;


/**
 * 图片异步缓存配置
 *
 * @author thomas
 */
public class ImageManager {

    public static ImageLoader getInstance() {
        return ImageLoader.getInstance();
    }

    private static DisplayImageOptions defaultOptions;

    /**
     * 图片加载辅助类
     *
     * @return
     */
    public static DisplayImageOptions getDefaultOptions() {
        if (defaultOptions == null) {
            defaultOptions = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.default_image)
                    .showImageOnFail(R.mipmap.default_image)
                    .showImageOnLoading(R.mipmap.default_image)
                    .bitmapConfig(Bitmap.Config.RGB_565)// 设置为RGB565比起默认的ARGB_8888要节省大量的内存
                    .delayBeforeLoading(100)// 载入图片前稍做延时可以提高整体滑动的流畅度
                    .cacheInMemory(true).cacheOnDisc(true).build();
        }
        return defaultOptions;
    }


}
