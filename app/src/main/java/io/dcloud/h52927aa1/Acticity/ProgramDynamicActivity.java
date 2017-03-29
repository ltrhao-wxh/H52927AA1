package io.dcloud.h52927aa1.Acticity;


import android.os.Bundle;
import android.widget.ImageView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.dcloud.h52927aa1.Base.BaseActivity;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Utils.LoadImageUtils;


public class ProgramDynamicActivity extends BaseActivity {
    private static String TAG = "--VIDEO--";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://baobab.wdjcdn.com/1455782903700jy.mp4";

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;
    private ImageView mStart;

    @Override
    protected int getContentView() {
        return R.layout.activity_program_dynamic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVideoConfig();
    }

    private void initVideoConfig() {
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.my_video);
        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        LoadImageUtils.loadImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",jcVideoPlayerStandard.thumbImageView);
    }


}