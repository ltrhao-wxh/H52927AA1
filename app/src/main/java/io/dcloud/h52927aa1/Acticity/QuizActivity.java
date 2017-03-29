package io.dcloud.h52927aa1.Acticity;

import android.os.Bundle;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;


/**
 * 问答设置
 */
public class QuizActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_quiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIv_left(R.mipmap.ic_launcher);
        setTv_title("问答设置");
        init();
    }

    private void init() {

    }
}
