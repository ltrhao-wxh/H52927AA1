package io.dcloud.h52927aa1.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wxhl.core.utils.L;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by wuxiaohui on 17/3/27.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private String accessToken;
    private String openID;
    private String refreshToken;
    private Long expires_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int errorCode = baseResp.errCode;
        switch (errorCode) {
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                String code = ((SendAuth.Resp) baseResp).code;
                L.e(code);
//                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                break;
            default:
                break;
        }

    }

    private String getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        URI uri = URI.create(url);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                L.e(reader);
                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }

                JSONObject object = new JSONObject(sb.toString().trim());
                accessToken = object.getString("access_token");
                openID = object.getString("openid");
                refreshToken = object.getString("refresh_token");
                expires_in = object.getLong("expires_in");
                return accessToken;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
