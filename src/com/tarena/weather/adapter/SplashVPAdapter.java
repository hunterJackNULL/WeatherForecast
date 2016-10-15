package com.tarena.weather.adapter;

import com.tarena.weather.MainActivity;
import com.tarena.weather.R;
import com.tarena.weather.activity.SplashActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SplashVPAdapter extends PagerAdapter{
	//context data  layout  
	int images[]={R.drawable.welcome_1,R.drawable.welcome_2,R.drawable.welcome_3};
	int layout=R.layout.splash_vp_page;
	private Context context;
	public SplashVPAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		return 3;
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	//类似于BaseAdapter中的getView方法
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view=View.inflate(context, layout, null);
		ImageView  iv_vp=(ImageView) view.findViewById(R.id.iv_vp);
		iv_vp.setImageResource(images[position]);
		if(position==2){
			Button  btn=(Button) view.findViewById(R.id.btn_splash);
			btn.setVisibility(View.VISIBLE);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					context.startActivity(new Intent(context,MainActivity.class));
				    ((SplashActivity)context).finish();
				}
			});
		}else{
			Button  btn=(Button) view.findViewById(R.id.btn_splash);
			btn.setVisibility(View.GONE);
		}
		container.addView(view);
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		//super.destroyItem(container, position, object);
		container.removeView((View)object);
	}
}
