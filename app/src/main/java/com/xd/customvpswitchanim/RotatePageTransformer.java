package com.xd.customvpswitchanim;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class RotatePageTransformer implements ViewPager.PageTransformer, MyCustomViewPager.PageTransformer {
    private static final float MAX_ROTATE = 20f;

    private float mRote = 0;

    public void transformPage(View view, float position) {

        Log.e("sg", "View:" + view + ",Position:" + position);

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            //A也已经在界面外了，不让旋转即可
            ViewHelper.setRotation(view, 0);

        } else if (position <= 0) { // [-1,0]   A页 坐标从 0 ~ -1
            mRote = position * MAX_ROTATE;

            //设置旋转中心点
            ViewHelper.setPivotX(view, pageWidth / 2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());

            ViewHelper.setRotation(view, mRote);

        } else if (position <= 1) { // (0,1]        B页，坐标从 1~0
            mRote = position * MAX_ROTATE;

            //设置旋转中心点
            ViewHelper.setPivotX(view, pageWidth / 2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());

            ViewHelper.setRotation(view, mRote);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setAlpha(0);
            ViewHelper.setRotation(view, 0);
        }
    }
}