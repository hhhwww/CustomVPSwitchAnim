package com.xd.customvpswitchanim;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class DepthPageTransformer implements ViewPager.PageTransformer, MyCustomViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {

        Log.e("sg", "View:" + view + ",Position:" + position);

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(view, 0);
//                view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]   A页 没有变化
            // Use the default slide transition when moving to the left page
            ViewHelper.setAlpha(view, 1);
//            ViewHelper.setTranslationX(view, 1);
            ViewHelper.setScaleX(view, 1);
            ViewHelper.setScaleY(view, 1);
//                view.setAlpha(1);
//                view.setTranslationX(0);
//                view.setScaleX(1);
//                view.setScaleY(1);

        } else if (position <= 1) { // (0,1]        B页，坐标从1~0
            // Fade the page out.
//            view.setAlpha(1 - position);
            ViewHelper.setAlpha(view, 1 - position);    // 透明度 0~1

            // Counteract the default slide transition
//            view.setTranslationX(pageWidth * -position);
            ViewHelper.setTranslationX(view, pageWidth * -position); //X轴的位置，-Width ~ 0

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE                       //X轴和Y轴的缩放程度0.75 ~ 1
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
            ViewHelper.setScaleX(view, scaleFactor);
            ViewHelper.setScaleY(view, scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setAlpha(0);
            ViewHelper.setAlpha(view, 0);
        }
    }
}