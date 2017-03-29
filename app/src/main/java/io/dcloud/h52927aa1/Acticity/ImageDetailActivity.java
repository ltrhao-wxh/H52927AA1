package io.dcloud.h52927aa1.Acticity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.dcloud.h52927aa1.R;


public class ImageDetailActivity extends AppCompatActivity {

    private List<View> imageViewList=new ArrayList<>();
    private ViewPager viewPager;
    private TextView textView;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        init();
    }

    private void init() {
        viewPager= (ViewPager) findViewById(R.id.vp);
        textView= (TextView) findViewById(R.id.tv_count);
        Intent intent=getIntent();
        int index=intent.getIntExtra("index",0);
        list= (List<String>) intent.getSerializableExtra("imageList");
        textView.setText((index+1)+"/"+imageViewList.size());

        for (String i:list) {
            View view=getLayoutInflater().inflate(R.layout.item_touchimageview,null);
            // ImageView imagview= (ImageView) view.findViewById(R.id.iv_images);会出现IllegalStateException,you must call removeView() after.....

            imageViewList.add(view);
        }

        MyVeiwPagerAdapter adapter = new MyVeiwPagerAdapter();
        viewPager.setAdapter(adapter);

        //首次进入需要显式的图片
        loadImage(index,imageViewList.get(index));
        //设置展示的位置
        viewPager.setCurrentItem(index);

        //翻页
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                loadImage(position,imageViewList.get(position));
                textView.setText((position+1)+"/"+imageViewList.size());
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private void loadImage(int index, final View imageView){
        String url=list.get(index);
        final ImageView imageView1= (ImageView) imageView.findViewById(R.id.iv_touch);
        x.image().loadDrawable(url, ImageOptions.DEFAULT, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable drawable) {
                imageView1.setImageDrawable(drawable);
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
    class  MyVeiwPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
