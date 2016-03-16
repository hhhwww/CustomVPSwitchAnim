package com.xd.customvpswitchanim;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<ImageView> mImageViews = new ArrayList<>();
    private CustomViewPager mCustomViewPager;

    private int[] mIds = {R.drawable.guide_image1, R.drawable.guide_image2
            , R.drawable.guide_image3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        initDatas();
        mViewPager.setPageTransformer(false, new DepthPageTransformer());
    }

    private void initDatas() {
        for (int i = 0; i < mIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mIds[i]);
            mImageViews.add(imageView);
        }
        mCustomViewPager = new CustomViewPager();
        mViewPager.setAdapter(mCustomViewPager);
    }

    class CustomViewPager extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews.get(position));
        }
    }
}
