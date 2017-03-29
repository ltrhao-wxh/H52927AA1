package io.dcloud.h52927aa1.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxhl.core.utils.CoreUtil;

import io.dcloud.h52927aa1.R;


public abstract class BaseActivity extends AppCompatActivity {


    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_title;
    private TextView tv_right;

    protected  abstract int getContentView();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoreUtil.addToActivityList(this);
        setContentView(getContentView());

        iv_left= (ImageView) findViewById(R.id.baseiv_left);
        iv_right= (ImageView) findViewById(R.id.baseiv_right1);
        tv_title= (TextView) findViewById(R.id.basetv_title);
        tv_right= (TextView) findViewById(R.id.basetv_right1);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public ImageView getIv_right() {
        return iv_right;
    }

    protected  void setIv_left(int resId){
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setImageResource(resId);

    }

    protected  void setIv_right(int resId){
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(resId);
    }
    protected  void hideTv_right(){
        tv_right.setVisibility(View.GONE);
    }
    protected  void hideIv_right(){
        iv_right.setVisibility(View.GONE);
    }



    protected  void setTv_title(CharSequence charSequence){
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(charSequence);

    }



    protected void setTv_title(CharSequence charSequence,int titleColo){
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(charSequence);
        tv_title.setTextColor(titleColo);
    }

    protected  void setTv_title(int resId){
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(resId);
    }

    protected  void setTv_right(CharSequence charSequence){
        iv_right.setVisibility(View.GONE);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(charSequence);
    }
}
