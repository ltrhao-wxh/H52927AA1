package io.dcloud.h52927aa1.Acticity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;


public class MyDoingActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_doing;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = (TextView) findViewById(R.id.tv_where);
        setIv_left(R.mipmap.ic_launcher);
        getWhere();
    }

    private void getWhere() {

        Intent intent = getIntent();
        if (intent != null) {
            String value = intent.getStringExtra("where");
            switch (value) {
                case "1"://我的发布
                    setTv_title("我的发布");
                    textView.setText("我的发布");
                    break;
                case "2"://我的收藏
                    setTv_title("我的收藏");
                    textView.setText("我的收藏");
                    break;
                case "3"://我的咨询
                    setTv_title("我的咨询");
                    textView.setText("我的咨询");
                    break;
                case "4"://我的回答
                    setTv_title("我的回答");
                    textView.setText("我的回答");
                    break;
                case "5"://我的抢赏
                    setTv_title("我的抢赏");
                    textView.setText("我的抢赏");
                    break;
                case "6"://我的悬赏
                    setTv_title("我的悬赏");
                    textView.setText("我的悬赏");
                    break;
            }

        }
    }


}
