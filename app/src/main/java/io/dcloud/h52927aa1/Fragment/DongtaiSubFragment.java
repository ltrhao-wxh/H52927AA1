package io.dcloud.h52927aa1.Fragment;

import android.os.Bundle;
import android.view.View;

import io.dcloud.h52927aa1.Base.Constants;


/**
 * Created by wuxiaohui on 17/3/23.
 */

public class DongtaiSubFragment extends BaseFragment {
    @Override
    protected int getBodyView() {
        return 0;
    }

    @Override
    protected void init(View root) {

    }

    public static DongtaiSubFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(Constants.PUT_INT,id);
        DongtaiSubFragment fragment = new DongtaiSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
