package io.dcloud.h52927aa1.Acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;

public class XieyiActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_xieyi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIv_left(R.drawable.back);
        setTv_title("找熟人用户协议");

    }
}
