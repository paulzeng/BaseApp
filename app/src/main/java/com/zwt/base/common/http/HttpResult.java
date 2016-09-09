package com.zwt.base.common.http;

import com.zwt.base.base.BaseBean;

/**
 * @author zhudehao
 * @version $Rev$
 * @time 2016/3/18 10:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class HttpResult<T> extends BaseBean{
    public boolean Success;

    public int Code;

    public String Message;

    public int Count;

    public String Contents;
    //用来模仿Data
    public T Object;


}
