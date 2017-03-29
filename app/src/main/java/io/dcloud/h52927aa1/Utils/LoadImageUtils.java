package io.dcloud.h52927aa1.Utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by wuxiaohui on 17/3/14.
 */

public class LoadImageUtils {



    public static void loadImage(String url,final ImageView imageView){
        x.image().loadDrawable(url, ImageOptions.DEFAULT, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {

            }
        });
    }
}
