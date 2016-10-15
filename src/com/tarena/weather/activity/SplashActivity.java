package com.tarena.weather.activity;

import com.tarena.weather.MainActivity;
import com.tarena.weather.R;
import com.tarena.weather.adapter.SplashVPAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	private boolean isFirstOpen = true;
	SharedPreferences sp;
	private ViewPager vp_splash;
	private LinearLayout ll_splash;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
		// 偏好设置
		sp = getSharedPreferences("config", MODE_PRIVATE);
		isFirstOpen = sp.getBoolean("FIRST_OPEN", true);
		if (isFirstOpen == false) {
			ll_splash.setVisibility(View.GONE);
			findViewById(R.id.tv_welcome).setVisibility(View.VISIBLE);
			// UI机制 UI线程(主线程) 工作线程
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(2000);
					// 启动主activity
					runOnUiThread(new Runnable() {
						public void run() {
							startActivity(new Intent(SplashActivity.this,
									MainActivity.class));
							SplashActivity.this.finish();
						}
					});
					/*
					 * try { Thread.sleep(2000); } catch (InterruptedException
					 * e) { e.printStackTrace(); }
					 */
				}
			}).start();
		} else {
			ll_splash.setVisibility(View.VISIBLE);
			final ImageView iv1 = (ImageView) findViewById(R.id.iv_point1);
			final ImageView iv2 = (ImageView) findViewById(R.id.iv_point2);
			final ImageView iv3 = (ImageView) findViewById(R.id.iv_point3);

			vp_splash = (ViewPager) findViewById(R.id.vp_splash);
			vp_splash.setAdapter(new SplashVPAdapter(SplashActivity.this));
			SharedPreferences.Editor spe = sp.edit();
			spe.putBoolean("FIRST_OPEN", false);
			spe.commit();
			vp_splash.setOnPageChangeListener(new OnPageChangeListener() {
				// 当某个页面被选中的时候执行的方法
				@Override
				public void onPageSelected(int position) {
					switch (position) {
					case 0:
						iv1.setImageResource(R.drawable.point_checked);
						iv2.setImageResource(R.drawable.point_nocheked);
						iv3.setImageResource(R.drawable.point_nocheked);
						break;
					case 1:
						iv1.setImageResource(R.drawable.point_nocheked);
						iv2.setImageResource(R.drawable.point_checked);
						iv3.setImageResource(R.drawable.point_nocheked);
						break;
					case 2:
						iv1.setImageResource(R.drawable.point_nocheked);
						iv2.setImageResource(R.drawable.point_nocheked);
						iv3.setImageResource(R.drawable.point_checked);
						break;
					}
				}

				@Override
				// 当页面滚动的时候会执行
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					Log.i("TAG", "onPageScrolled()");
				}

				@Override
				// 滚动状态发生改变的时候会执行
				public void onPageScrollStateChanged(int arg0) {
					Log.i("TAG", "onPageScrollStateChanged()");
				}
			});

		}

		/*
		 * if(isFirstOpen==true){ ms=5000; }else{ ms=2000; }
		 */

		// startActivity
	}

}
