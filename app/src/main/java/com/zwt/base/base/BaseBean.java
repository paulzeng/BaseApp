package com.zwt.base.base;

import com.google.gson.Gson;

/**
 * 提供一些公用的方法
 * Created by ZWT on 2016/9/9.
 */
public class BaseBean  {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
