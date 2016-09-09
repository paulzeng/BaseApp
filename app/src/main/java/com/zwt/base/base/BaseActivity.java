package com.zwt.base.base;

import android.app.Activity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.zwt.base.R;
import com.zwt.base.app.App;

/**
 * 封装activity的基类
 * Created by ZWT on 2016/9/9.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.allActivity.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark), 0);
    }
}
