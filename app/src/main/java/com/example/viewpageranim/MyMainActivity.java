package com.example.viewpageranim;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.viewpageranim.custom_anim.CustomTransformer;
import com.example.viewpageranim.modify_source_code_anim.RotatePageTransformer;
import com.example.viewpageranim.official_anim.DepthPageTransformer;
import com.example.viewpageranim.official_anim.ZoomOutPageTransformer;

public class MyMainActivity extends Activity {
	private CustomTransformer mViewPager;

	private int[] mImgIds = new int[] { R.drawable.guide_image1,
			R.drawable.guide_image2, R.drawable.guide_image3 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_my);

		mViewPager=(CustomTransformer) findViewById(R.id.id_viewpager);

		/**
		* 为Viewpager添加切换动画效果(仅支持Android3.0以上版本,因为涉及了属性动画，而属性动画是Android3.0开始的)
		* 只需要setPageTransformer方法即可
		* 第一个参数：true if the supplied PageTransformer requires page views to be drawn from last to first instead of first to last
		*/
		//mViewPager.setPageTransformer(true, new DepthPageTransformer());//布局文件中使用原始的ViewPager即可
		//mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());//布局文件中使用原始的ViewPager即可

		/**
		* 使用支持3.0一下的开源项目ninooldandroids实现（在libs中添加nineoldandroids.jar包）
		* 但是因为ViewPager的setPageTransformer方法中判断了SDK是否>=11，所以需要自定义一个ViewPager，在这里为ModifiedOfficialViewPager
		*/
		//要兼容到3.0以下，布局文件中使用ModifiedOfficialViewPager，否则使用原始ViewPager
		mViewPager.setPageTransformer(true, new RotatePageTransformer());

		mViewPager.setAdapter(new PagerAdapter() {

			/**
			 * Create the page for the given position
			 *
			 * Returns:an Object representing the new page.This does not
			 * need to be a View, but can be some other container of the page.
			 */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView imageView=new ImageView(MyMainActivity.this);
				imageView.setImageResource(mImgIds[position]);
				imageView.setScaleType(ScaleType.CENTER_CROP);//防止图片变形
				container.addView(imageView);//container-容器

				mViewPager.setViewForPosition(imageView,position);

				return imageView;
			}

			/**
			 * Remove a page for the given position
			 */
			@Override
			public void destroyItem(ViewGroup container, int position,Object object) {
				container.removeView((View)object);
				mViewPager.removeViewFromPosition(position);
			}

			/**
			 * Determines whether a page View is associated with a specific key object as returned by 
			 * instantiateItem(ViewGroup, int)
			 *
			 * Returns:true if view is associated with the key object object
			 */
			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view==object;
			}

			/**
			 * Return the number of views available
			 */
			@Override
			public int getCount() {
				return mImgIds.length;
			}
		});
	}
}
