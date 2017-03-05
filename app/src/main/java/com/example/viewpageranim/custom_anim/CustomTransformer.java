package com.example.viewpageranim.custom_anim;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 完全自定义的的动画效果，不用修改ViewPager源码
 *
 * Translation  Scale
 * a、需要拿到当前切换的两个View，通过Map存储与获取
 * b、获取动画的梯度值：offset、offsetPixels
 */
public class CustomTransformer extends ViewPager {
    private View mLeft;
    private View mRight;

    private float mTrans;
    private float mScale;

    private static final float MIN_SCALE=0.6f;

    private Map<Integer,View> mChildren=new HashMap<>();

    public void setViewForPosition(View view,int position){
        mChildren.put(position,view);
    }

    public void removeViewFromPosition(Integer position){
        mChildren.remove(position);
    }

    public CustomTransformer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        Log.e("TAG","position="+position+", offset="+offset+", offsetPixels="+offsetPixels);
        Log.e("TAG",getCurrentItem()+","+getChildCount());

        /**
         * 拿到当前的左右两个View。
         * 无论左滑还是右滑，左边的View位置都是position,右边就是position+1
         */
        mLeft=mChildren.get(position);
        mRight=mChildren.get(position+1);
        
        animStack(mLeft,mRight,offset,offsetPixels);

        super.onPageScrolled(position, offset, offsetPixels);
    }

    private void animStack(View left, View right, float offset, int offsetPixels) {
        if (right!=null){
            //从0到1页，offset的值：0~1
            mScale=(1-MIN_SCALE)*offset+MIN_SCALE;
            mTrans=-getWidth()-getPageMargin()+offsetPixels;

            ViewHelper.setScaleX(right,mScale);
            ViewHelper.setScaleY(right, mScale);

            ViewHelper.setTranslationX(right,mTrans);
        }

        if (left!=null){
            left.bringToFront();
        }
    }

}
