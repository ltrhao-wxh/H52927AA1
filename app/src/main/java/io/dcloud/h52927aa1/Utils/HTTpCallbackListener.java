package io.dcloud.h52927aa1.Utils;

/**
 * Created by wuxiaohui on 17/3/10.
 */

interface HTTpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
