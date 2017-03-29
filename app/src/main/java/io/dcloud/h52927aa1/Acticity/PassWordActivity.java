package io.dcloud.h52927aa1.Acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wxhl.core.utils.L;
import com.wxhl.core.utils.T;

import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;

public class PassWordActivity extends BaseActivity {

    private EditText pass,psw;

    @Override
    protected int getContentView() {
        return R.layout.activity_pass_word;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTv_title("设置密码");
        setIv_left(R.drawable.back);

        init();
    }

    private void init() {
        pass= (EditText) findViewById(R.id.et_account);
        psw= (EditText) findViewById(R.id.et_passWord);
        Button save= (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=pass.getText().toString();
                String b=psw.getText().toString();
                if (a.length()!=6){
                    T.showShort(PassWordActivity.this,"请输入六位密码");
                }
                if (a.equals(b)){
                    T.showShort(PassWordActivity.this,"密码相同");
                }else {
                    T.showShort(PassWordActivity.this,"密码不同");
                }
            }
        });
    }
}
