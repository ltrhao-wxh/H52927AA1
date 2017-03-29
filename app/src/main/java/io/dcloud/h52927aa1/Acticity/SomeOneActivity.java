package io.dcloud.h52927aa1.Acticity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import io.dcloud.h52927aa1.Adapter.MyFragmentPagerAdapter;
import io.dcloud.h52927aa1.R;


public class SomeOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_one);
        init();
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_id);
        tabLayout.setupWithViewPager(viewPager);
    }
}
