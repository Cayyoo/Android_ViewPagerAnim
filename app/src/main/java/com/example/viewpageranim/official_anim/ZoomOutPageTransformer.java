package com.example.viewpageranim.official_anim;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager;
import android.view.View;

//由谷歌官方提供,,只有在3.0以上有效果
//从http://developer.android.com/training/animation/screen-slide.html获取
//涉及了属性动画，注意最低的API版本
//但是可以利用ninooldandroids此开源项目来支持3.0以下使用属性动画（被注释掉的就是属性动画实现的，而与之替代的就是利用ninooldandroids实现的）
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setAlpha(0);
            ViewHelper.setAlpha(view, 0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
//                view.setTranslationX(horzMargin - vertMargin / 2);
                ViewHelper.setTranslationX(view, horzMargin - vertMargin / 2);
            } else {
//                view.setTranslationX(-horzMargin + vertMargin / 2);
                ViewHelper.setTranslationX(view,-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
            ViewHelper.setScaleX(view, scaleFactor);
            ViewHelper.setScaleY(view, scaleFactor);

            // Fade the page relative to its size.
//            view.setAlpha(MIN_ALPHA +
//                    (scaleFactor - MIN_SCALE) /
//                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            ViewHelper.setAlpha(view, MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setAlpha(0);
            ViewHelper.setAlpha(view, 0);
        }
    }
}
