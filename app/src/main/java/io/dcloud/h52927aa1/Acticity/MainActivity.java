package io.dcloud.h52927aa1.Acticity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.wingsofts.byeburgernavigationview.ByeBurgerBehavior;

import java.util.ArrayList;
import java.util.List;

import io.dcloud.h52927aa1.Fragment.HolderFragment;
import io.dcloud.h52927aa1.Fragment.HomeFragment;
import io.dcloud.h52927aa1.Fragment.MeFragment;
import io.dcloud.h52927aa1.Fragment.MineFragment;
import io.dcloud.h52927aa1.R;


public class MainActivity extends AppCompatActivity {
    private Fragment fg;    // fg记录当前的Fragment
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private BottomNavigationView mNavigationView;
    private ByeBurgerBehavior mBehavior;
    private Toolbar mToolbar;
    private TextView cloud,tb_title;
    private boolean faOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoordinatorLayout view = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(view);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        initData();
        initView();
    }


    /**
     * 监听来自Fragment的返回键
     *
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (fg instanceof HolderFragment) {
            ((HolderFragment) fg).onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        tb_title= (TextView) findViewById(R.id.tb_title);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavigationView = (BottomNavigationView) findViewById(R.id.bye_burger);
        cloud = (TextView) findViewById(R.id.cloud);//蒙板
//        mBehavior = ByeBurgerBehavior.from(mFloatButton);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

//        /**
//         * 蒙板的点击
//         */
//
//        cloud.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                closeMenu(mFloatButton);
//            }
//        });
//        /**
//         * 蒙板内的点击事件  点击关闭
//         */
//        mFloatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!faOpened) {
//                    openMenu(view);
//                } else {
//                    closeMenu(view);
//                }
//            }
//        });
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().equals("咨询")) {
                            ByeBurgerBehavior.from(mToolbar).show();
                            mToolbar.setTitle("");
                            tb_title.setText("咨询");
                            mViewPager.setCurrentItem(0);
                        } else if (item.getTitle().equals("悬赏")) {
                            ByeBurgerBehavior.from(mToolbar).hide();
                            mViewPager.setCurrentItem(1);
                        } else if (item.getTitle().equals("我的")) {
                            ByeBurgerBehavior.from(mToolbar).show();
                            mToolbar.setTitle("");
                            tb_title.setText("我的");
                            mViewPager.setCurrentItem(2);
                        }
                        return true;
                    }
                });

    }

    /**
     * 点击打开蒙板
     */
    private void openMenu(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, -115, -135);
        animator.setDuration(500);
        animator.start();
        cloud.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        cloud.startAnimation(alphaAnimation);
        faOpened = true;
    }

    /**
     * 关闭蒙板
     *
     * @param view
     */

    private void closeMenu(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", -135, 20, 0);
        animator.setDuration(500);
        animator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        cloud.startAnimation(alphaAnimation);
        cloud.setVisibility(View.GONE);
        faOpened = false;

    }


    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());

        fragmentList.add(HolderFragment.newInstance());

        fragmentList.add(MineFragment.newInstance());

    }
//
//    /**
//     * toolbar搜索框
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search, menu);
//
//        //设置搜索输入框的步骤
//
//        //1.查找指定的MemuItem
////        MenuItem menuItem = menu.findItem(R.id.tb_search);
//
//      /*  //2.设置SearchView v4 包方式
//        View view = SearchViewCompat.newSearchView(this);
////        menuItem.setActionView(view);
//        MenuItemCompat.setActionView(menuItem, view);*/
//
//        //2.设置SearchView v7包方式
////        View view = MenuItemCompat.getActionView(menuItem);
////        if (view != null) {
////            searchView = (SearchView) view;
//        //4.设置SearchView 的查询回调接口
////            searchView.setOnQueryTextListener(this);
//
//        //在搜索输入框没有显示的时候 点击Action ,回调这个接口，并且显示输入框
////            searchView.setOnSearchClickListener();
//        //当自动补全的内容被选中的时候回调接口
////            searchView.setOnSuggestionListener();
//
//        //可以设置搜索的自动补全，或者实现搜索历史
////            searchView.setSuggestionsAdapter();
//
////        }
//
//        return true;
//
//    }
//
//    /**
//     * 当用户在输入法中点击搜索按钮时,或者输入回车时,调用这个方法，发起实际的搜索功能
//     *
//     * @param query
//     * @return
//     */
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//
//
////        searchView.clearFocus();
//        return true;
//    }
//
//    /**
//     * 每一次输入字符，都会调用这个方法，实现搜索的联想功能
//     *
//     * @param newText
//     * @return
//     */
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//        return true;
//    }


}
