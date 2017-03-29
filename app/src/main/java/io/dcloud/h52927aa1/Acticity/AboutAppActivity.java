package io.dcloud.h52927aa1.Acticity;

import android.os.Bundle;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;


public class AboutAppActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_aboout_app;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTv_title("关于找熟人APP");
        setIv_left(R.mipmap.ic_launcher);
    }
}
