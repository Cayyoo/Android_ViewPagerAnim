package com.example.viewpageranim.official_anim;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;

//由谷歌官方提供,只有在3.0以上有效果
//从http://developer.android.com/training/animation/screen-slide.html获取
//涉及了属性动画，注意最低的API版本
//但是可以利用ninooldandroids此开源项目来支持3.0以下的属性动画（被注释掉的就是属性动画实现的，而与之替代的就是利用ninooldandroids实现的）
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        Log.d("测试","view="+view+",position="+position);

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setAlpha(0);
            ViewHelper.setAlpha(view, 0);

        } else if (position <= 0) { // [-1,0], A椤祊osition: 0.0~-1
            // Use the default slide transition when moving to the left page
//            view.setAlpha(1);
//            view.setTranslationX(0);
//            view.setScaleX(1);
//            view.setScaleY(1);
            ViewHelper.setAlpha(view, 1);
            ViewHelper.setTranslationX(view, 0);
            ViewHelper.setScaleX(view, 1);
            ViewHelper.setScaleY(view, 1);

        } else if (position <= 1) { // (0,1], B椤祊osition: 1~0.0
            // Fade the page out.
//            view.setAlpha(1 - position);
            ViewHelper.setAlpha(view, 1 - position);

            // Counteract the default slide transition
//            view.setTranslationX(pageWidth * -position);
            ViewHelper.setTranslationX(view, pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position)); //scaleFactor: 0.75~1
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