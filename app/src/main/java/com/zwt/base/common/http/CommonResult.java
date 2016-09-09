package com.zwt.base.common.http;

import com.google.gson.Gson;
import com.zwt.base.base.BaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuhai
 * @version $Rev$
 * @time 2016/3/19 14:31
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2016/3/19$
 * @updateDes ${TODO}
 */
public class CommonResult extends BaseBean{
    public boolean Success;

    public int Code;

    public String Message;

    public int Count;

    public String Contents;

    public Object Object;




}
