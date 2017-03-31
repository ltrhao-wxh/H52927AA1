package io.dcloud.h52927aa1.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import io.dcloud.h52927aa1.R;


/**
 * Created by wing on 11/4/16.
 */

public class HolderFragment extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.fragment_holder, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = (WebView) view.findViewById(R.id.my_web);
        mWebView.loadUrl("http://drrr.com");

        init();
    }

    // 返回键按下时会被调用
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            
            return true;
        }
        return false;
    }

    private void init() {
        WebSettings settings = mWebView.getSettings();
        settings.setSupportZoom(false);

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setUseWideViewPort(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
    }

    public static HolderFragment newInstance() {
        Bundle args = new Bundle();
        HolderFragment fragment = new HolderFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
