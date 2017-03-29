package io.dcloud.h52927aa1.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxhl.core.utils.CoreUtil;
import com.wxhl.core.utils.IntentUtil;

import io.dcloud.h52927aa1.Acticity.AboutAppActivity;
import io.dcloud.h52927aa1.Acticity.ApproveActivity;
import io.dcloud.h52927aa1.Acticity.LoginActivity;
import io.dcloud.h52927aa1.Acticity.MyDoingActivity;
import io.dcloud.h52927aa1.Acticity.PeopleMessageActivity;
import io.dcloud.h52927aa1.Acticity.QuizActivity;
import io.dcloud.h52927aa1.R;
import io.dcloud.h52927aa1.Weiget.CacheDataManager;

/**
 * Created by wuxiaohui on 17/3/17.
 */

public class MeFragment extends Fragment implements View.OnClickListener {


    private Intent intent;
    private ImageView iv_login;
    private TextView tv_login, tv_clean;
    private LinearLayout authentication;
    private LinearLayout myDoing;
    private LinearLayout myCollect, clean;
    private LinearLayout answer;
    private LinearLayout reward;
    private LinearLayout offer;//老熟认证 发布 收藏 咨询 回答 抢赏 奖赏
    private LinearLayout quiz, about, account;
    private Button button;
    private String[] items = {"退出熟人", "退出账号"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, null);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
//        String head= Message.head;
//        if (head!=null){
//            iv_login.setImageDrawable(Drawable.createFromPath(head));
//        }else {
//            iv_login.setImageResource(R.mipmap.head_nor);
//        }
        tv_clean = (TextView) view.findViewById(R.id.tv_clean);
        try {
            tv_clean.setText(CacheDataManager.getTotalCacheSize(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void init(View view) {
        //点击头像登陆  点击登陆文字登陆
        iv_login = (ImageView) view.findViewById(R.id.iv_head);
        iv_login.setImageResource(R.mipmap.head_nor);
        tv_login = (TextView) view.findViewById(R.id.tv_head);
        iv_login.setOnClickListener(this);
        tv_login.setOnClickListener(this);


        view.findViewById(R.id.btn_out).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                outShuren();
                                break;
                            case 1:
                                outLogin();
                                break;

                            default:
                                break;
                        }
                    }
                });

                builder.show();
            }
        });

        //点击老熟认证
        authentication = (LinearLayout) view.findViewById(R.id.ll_authentication);
        authentication.setOnClickListener(this);

        //点击我的发布
        myDoing = (LinearLayout) view.findViewById(R.id.ll_myDoing);
        myDoing.setOnClickListener(this);
        //点击我的收藏
        myCollect = (LinearLayout) view.findViewById(R.id.ll_myCollect);
        myCollect.setOnClickListener(this);
        //点击我的咨询
        LinearLayout consult = (LinearLayout) view.findViewById(R.id.ll_consult);
        consult.setOnClickListener(this);
        //点击我的回答
        answer = (LinearLayout) view.findViewById(R.id.ll_answer);
        answer.setOnClickListener(this);
        //点击我的抢赏
        reward = (LinearLayout) view.findViewById(R.id.ll_reward);
        reward.setOnClickListener(this);
        //点击我的悬赏
        offer = (LinearLayout) view.findViewById(R.id.ll_offer);
        offer.setOnClickListener(this);
        //点击问答设置
        quiz = (LinearLayout) view.findViewById(R.id.ll_questions);
        quiz.setOnClickListener(this);
        //点击关于
        about = (LinearLayout) view.findViewById(R.id.ll_about);
        about.setOnClickListener(this);
        //账户信息
        account = (LinearLayout) view.findViewById(R.id.ll_account);
        account.setOnClickListener(this);
        //清除缓存
        clean = (LinearLayout) view.findViewById(R.id.ll_clean);
        clean.setOnClickListener(this);


    }

    /**
     * 退出登陆更换账号
     */

    private void outLogin() {
        CoreUtil.finishActivityList();
        IntentUtil.startActivity(getActivity(), LoginActivity.class);
//        FileLocalCache.delSerializableData(getContext(), ConstantUtil.FILE_NAME);
    }

    /**
     * 退出熟人
     */

    private void outShuren() {
        CoreUtil.exitApp();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_head://点击头像登陆
                IntentUtil.startActivity(getContext(), LoginActivity.class);
                break;
            case R.id.tv_head://点击文字登陆
                IntentUtil.startActivity(getContext(), LoginActivity.class);
                break;
            case R.id.ll_authentication://点击老熟认证
                IntentUtil.startActivity(getContext(), ApproveActivity.class);
                break;
            case R.id.ll_myDoing://点击我的发布
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "1");
                startActivity(intent);
                break;
            case R.id.ll_myCollect://点击收藏
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "2");
                startActivity(intent);
                break;
            case R.id.ll_consult://点击咨询
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "3");
                startActivity(intent);
                break;
            case R.id.ll_answer://点击回答
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "4");
                startActivity(intent);
                break;
            case R.id.ll_reward://点击抢赏
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "5");
                startActivity(intent);
                break;
            case R.id.ll_offer://点击悬赏
                intent = new Intent(getContext(), MyDoingActivity.class);
                intent.putExtra("where", "6");
                startActivity(intent);
                break;
            case R.id.ll_questions://点击我的回答设置
                IntentUtil.startActivity(getContext(), QuizActivity.class);
                break;
            case R.id.ll_about:
                IntentUtil.startActivity(getContext(), AboutAppActivity.class);
                break;
            case R.id.ll_account:
                IntentUtil.startActivity(getContext(), PeopleMessageActivity.class);
                break;
            case R.id.ll_clean://清除缓存
                new Thread(new clearCache()).start();
                break;
            default:
                break;
        }
    }

    /**
     * 清理缓存
     */

    class clearCache implements Runnable {

        @Override

        public void run() {

            try {

                CacheDataManager.clearAllCache(getActivity());

                Thread.sleep(3000);

                if (CacheDataManager.getTotalCacheSize(getActivity()).startsWith("0")) {


                    handler.sendEmptyMessage(0);

                }

            } catch (Exception e) {

                return;

            }

        }

    }

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case 0:

                    Toast.makeText(getActivity(), "清理完成", Toast.LENGTH_SHORT).show();

                    try {

                        tv_clean.setText(CacheDataManager.getTotalCacheSize(getActivity()));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

            }

        }


    };


    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
