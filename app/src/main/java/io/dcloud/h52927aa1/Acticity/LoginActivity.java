package io.dcloud.h52927aa1.Acticity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxhl.core.utils.IntentUtil;
import com.wxhl.core.utils.L;
import com.wxhl.core.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.dcloud.h52927aa1.Base.Constants;
import io.dcloud.h52927aa1.Bean.Phone;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.network.RequestAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private Activity thisActivity=LoginActivity.this;
    private OkHttpClient client;
    private Button login;
    private TextView yanzheng,xieyi;
    private EditText userNameView;
    private EditText passwordView;
    private Phone phone;
    private List<Phone> list;
    private ImageView weChat, backFinish;


    //IWXAPI是第三方app和微信通信的openapi接口
    private IWXAPI api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //通过WXAPIFactory工厂，获取IWXAPI实例
        api = WXAPIFactory.createWXAPI(this, Constants.APPID,true);
        //将应用的appid注册到微信
        api.registerApp(Constants.APPID);
        init();
        initOkHttp();

    }


    /**
     * 初始化组件
     */
    private void init() {
        userNameView = (EditText) findViewById(R.id.et_account);
        passwordView = (EditText) findViewById(R.id.et_passWord);
        yanzheng = (TextView) findViewById(R.id.btn_yanzhengma);
        login = (Button) findViewById(R.id.btn_login);
        weChat = (ImageView) findViewById(R.id.weChatLogin);
        backFinish = (ImageView) findViewById(R.id.backFinish);
        xieyi= (TextView) findViewById(R.id.tv_xieyi);
        xieyi.setOnClickListener(this);

        backFinish.setOnClickListener(this);
        weChat.setOnClickListener(this);
        login.setOnClickListener(this);
        yanzheng.setOnClickListener(this);


    }

    /**
     * 登陆与注册的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yanzhengma:
                if (userNameView.getText().toString().isEmpty()) {
                    T.showShort(LoginActivity.this, "手机号码不能为空");
                    return;
                }
                /**
                 * 手机号验证
                 */
                String log = "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
                Pattern pattern = Pattern.compile(log);
                Matcher matcher = pattern.matcher((userNameView.getText().toString()));
                if (!matcher.matches()) {
                    Toast.makeText(LoginActivity.this, "手机号码不存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    v.setEnabled(false);
                    timer.start();
                    T.showShort(LoginActivity.this, "验证码已发送,请注意查收");
                    PostMessage();
                }
                break;
            case R.id.btn_login://登陆按钮的点击事件
//                phone.getVerify();  服务器储存的验证码
//                passwordView.getText().toString();  用户输入的验证码
                if (phone.getVerify().equals(passwordView.getText().toString())) {
                    if (phone.getMessage().equals("3")) {
                        IntentUtil.startActivity(thisActivity,PeopleMessageActivity.class);
                    } else {
                        T.showShort(thisActivity, "登陆成功");
                        IntentUtil.startActivity(thisActivity,MainActivity.class);
                    }
                } else {
                    T.showShort(thisActivity, "请输入正确的验证码");
                }
                break;
            case R.id.weChatLogin://第三方登陆
                T.showShort(thisActivity,"微信登陆");
                weCahtLogin();
                break;
            case R.id.backFinish://点击返回键
                finish();
                break;
            case R.id.tv_xieyi://点击协议
                IntentUtil.startActivity(LoginActivity.this,XieyiActivity.class);
                break;
            default:
                break;
        }

    }

    /**
     * 微信第三方登陆初始化
     */

    private void weCahtLogin() {

        final SendAuth.Req req = new SendAuth.Req();

        //授权读取用户信息
        req.scope = "snsapi_userinfo";

        //自定义信息
        req.state = "wechat_sdk_demo_test";

        //向微信发送请求
        api.sendReq(req);

    }

    /**
     * 验证码倒计时
     */

    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            yanzheng.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            yanzheng.setEnabled(true);
            yanzheng.setText("获取验证码");
        }
    };

    /**
     * 初始化okHttp
     */
    private void initOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 给服务器发送post请求  用户手机号和随即验证码
     */
    private void PostMessage() {
        phone = new Phone();//初始化bean
        int numcode = (int) ((Math.random() * 9 + 1) * 100000);//六位数随机数
        L.e(numcode);
        String iphone = userNameView.getText().toString();//获取ed内输入的内容
        phone.setVerify(String.valueOf(numcode));//将随机数进行纪录
        L.i(phone.getVerify());
        L.e(iphone);


        RequestBody requestBodyPost = new FormBody.Builder()
                .add("mobile", iphone)
                .add("verify", String.valueOf(numcode))
                .build();
        Request requestPost = new Request.Builder()
                .url(RequestAPI.BASE_URL + RequestAPI.POSTLOGIN)///url拼接
                .post(requestBodyPost)
                .build();
        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestDate = response.body().string();
                list = parserJsonWhitGson(requestDate);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        if (phone.getMessage().equals("3")) {
//                            T.showShort(thisActivity, "新用户");
//                        } else {
//                            T.showShort(thisActivity, "旧用户");
//                            IntentUtil.startActivity(thisActivity,MainActivity.class);
//                        }
                    }
                });
            }
        });
    }

    /**
     * 解析数据
     * <p>
     * public static String userid;
     * public static String status;
     * public static String message;
     *
     * @param requestDate
     * @return
     */
    private List<Phone> parserJsonWhitGson(String requestDate) {
        List<Phone> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(requestDate);

            Phone phone = new Phone();
            int userid = jsonObject.getInt("userid");
            String status = jsonObject.getString("status");
            String message = jsonObject.getString("message");


            phone.setUserid(userid);
            phone.setStatus(status);
            phone.setMessage(message);

            list.add(phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        finish();
    }


}
