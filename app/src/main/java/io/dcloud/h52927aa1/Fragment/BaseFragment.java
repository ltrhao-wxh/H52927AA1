package io.dcloud.h52927aa1.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wxhl.core.utils.L;
import com.wxhl.core.widget.TipInfoLayout;

import io.dcloud.h52927aa1.R;


/**
 * Created by wuxiaohui on 17/3/23.
 */

public abstract class BaseFragment extends Fragment {
    //    private BaseActivity mContext;

    protected Context mContext;

    public static final String KEY = "key_put_boolean";

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            boolean isHidden =   savedInstanceState.getBoolean(KEY,false);
            L.e("恢复中来" +  isHidden);
            if(isHidden){
                getFragmentManager().beginTransaction().hide(this);
            }else {
                getFragmentManager().beginTransaction().show(this);
            }

        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY,isHidden());
        L.e("保存的值  " +  isHidden());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;
    }



    //root根视图
    private View root;
    protected TipInfoLayout mTipInfoLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){

            root = inflater.inflate(R.layout.base_fragment,container,false);

            mTipInfoLayout = (TipInfoLayout) root.findViewById(R.id.tip_layout_id);
            LinearLayout body = (LinearLayout) root.findViewById(R.id.main_body_id);
            //将子Fragment视图加入到 body中
            View content = inflater.inflate(getBodyView(),null);
            body.removeAllViews();
            body.addView(content,new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT));
            init(content);
        }

        return root;
    }

    /**
     * 获取子fragment绑定的视图
     * @return
     */
    protected abstract int getBodyView();

    /**
     * 初始化
     */
    protected abstract void init(View root);
}
