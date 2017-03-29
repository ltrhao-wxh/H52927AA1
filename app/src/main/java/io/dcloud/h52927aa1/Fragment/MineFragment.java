package io.dcloud.h52927aa1.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxhl.core.utils.IntentUtil;

import io.dcloud.h52927aa1.Acticity.ApproveActivity;
import io.dcloud.h52927aa1.Acticity.GuanXiPiaoActivity;
import io.dcloud.h52927aa1.Acticity.GuanZhuActivity;
import io.dcloud.h52927aa1.Acticity.HuiDaActivity;
import io.dcloud.h52927aa1.Acticity.SeetingActivity;
import io.dcloud.h52927aa1.Acticity.ShouCangActitivy;
import io.dcloud.h52927aa1.Acticity.TiWenActivity;
import io.dcloud.h52927aa1.Acticity.XuanShangActivity;
import io.dcloud.h52927aa1.Acticity.ZiXunActivity;
import io.dcloud.h52927aa1.Acticity.QiangShangAcitivity;
import io.dcloud.h52927aa1.R;

/**
 * Created by wuxiaohui on 17/3/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout login, guanxipiao, zixun, guanzhu, shoucang, renzheng, shezhi;
    private TextView xuanshang, qiangshang, tiwen, huida;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mine, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        login = (RelativeLayout) view.findViewById(R.id.rl_login);
        guanxipiao = (RelativeLayout) view.findViewById(R.id.rl_shezhi);
        zixun = (RelativeLayout) view.findViewById(R.id.rl_zixun);
        guanzhu = (RelativeLayout) view.findViewById(R.id.rl_guanzhu);
        shoucang = (RelativeLayout) view.findViewById(R.id.rl_shoucang);
        renzheng = (RelativeLayout) view.findViewById(R.id.rl_renzheng);
        shezhi = (RelativeLayout) view.findViewById(R.id.rl_shezhi);

        xuanshang = (TextView) view.findViewById(R.id.my_xuanshang);
        qiangshang = (TextView) view.findViewById(R.id.my_qiangshang);
        tiwen = (TextView) view.findViewById(R.id.my_tiwen);
        huida = (TextView) view.findViewById(R.id.my_huida);

        xuanshang.setOnClickListener(this);
        qiangshang.setOnClickListener(this);
        tiwen.setOnClickListener(this);
        huida.setOnClickListener(this);

        guanxipiao.setOnClickListener(this);
        zixun.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        renzheng.setOnClickListener(this);
        shezhi.setOnClickListener(this);
    }


    /**
     * 点击事件的处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_xuanshang://我的悬赏
                IntentUtil.startActivity(getActivity(),XuanShangActivity.class);
                break;
            case R.id.my_qiangshang://我的抢赏
                IntentUtil.startActivity(getActivity(),QiangShangAcitivity.class);
                break;
            case R.id.my_tiwen://我的提问
                IntentUtil.startActivity(getActivity(),TiWenActivity.class);
                break;
            case R.id.my_huida://我的回答
                IntentUtil.startActivity(getActivity(),HuiDaActivity.class);
                break;
            case R.id.rl_guanxipiao://关系票
                IntentUtil.startActivity(getActivity(),GuanXiPiaoActivity.class);
                break;
            case R.id.rl_zixun://我的咨询
                IntentUtil.startActivity(getActivity(),ZiXunActivity.class);
                break;
            case R.id.rl_guanzhu://我的关注
                IntentUtil.startActivity(getActivity(),GuanZhuActivity.class);
                break;
            case R.id.rl_shoucang://我的收藏
                IntentUtil.startActivity(getActivity(),ShouCangActitivy.class);
                break;
            case R.id.rl_renzheng://熟人认证
                IntentUtil.startActivity(getActivity(),ApproveActivity.class);
                break;
            case R.id.rl_shezhi://设置
                IntentUtil.startActivity(getActivity(),SeetingActivity.class);
                break;
            default:
                break;
        }


    }


    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
