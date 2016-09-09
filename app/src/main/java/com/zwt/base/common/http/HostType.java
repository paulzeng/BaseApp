package com.zwt.base.common.http;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author liuhai
 * @version $Rev$
 * @time 2016/4/15 15:31
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2016/4/15$
 * @updateDes ${TODO}
 */
public class HostType {
    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 2;

    /**服务器地址
     *
     */
    @HostTypeChecker
    public static final int BASE_PATH = 1;

    /**
     * 本地服务器地址
     */
    @HostTypeChecker
    public static final int BASE_LOCAL_PATH = 2;


    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({BASE_PATH, BASE_LOCAL_PATH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }
}
