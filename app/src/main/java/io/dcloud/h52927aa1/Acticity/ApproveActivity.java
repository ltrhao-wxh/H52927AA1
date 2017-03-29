package io.dcloud.h52927aa1.Acticity;

import android.os.Bundle;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;


/**
 * 老熟认证界面
 */
public class ApproveActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_approve;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTv_title("老熟认证");
        setIv_left(R.drawable.back);
    }
}
