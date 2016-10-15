package com.tarena.weather.adapter;

import java.util.ArrayList;

import com.tarena.weather.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class HoriListVAdapter extends BaseAdapter{
//context layout  data
	private ArrayList<Weather>  weather;
	private Context context;
	private int     resource;
	{
		weather=new ArrayList<HoriListVAdapter.Weather>();
		Weather wea=new Weather();
		wea.image=R.drawable.a300;
		wea.tem="33¡ã/22¡ã";
		wea.weather="Çç";
		wea.week="ÐÇÆÚÒ»";
		weather.add(wea);weather.add(wea);weather.add(wea);weather.add(wea);weather.add(wea);
		weather.add(wea);weather.add(wea);
	}
	public HoriListVAdapter(Context context,int resource){
		this.context=context;
		this.resource=resource;
	}
	@Override
	public int getCount() {
		return weather.size();
	}

	@Override
	public Object getItem(int position) {
		return weather.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder  vh;
		if(convertView==null){
			convertView=View.inflate(context, resource, null);
			vh=new ViewHolder();
			vh.iv=(ImageView) convertView.findViewById(R.id.iv_futureweather);
			vh.tv1=(TextView)convertView.findViewById(R.id.tv_future_weather);
			vh.tv2=(TextView)convertView.findViewById(R.id.tv_future_tem);
			vh.tv3=(TextView)convertView.findViewById(R.id.tv_future_week);
			convertView.setTag(vh);
		}
		vh=(ViewHolder) convertView.getTag();
		vh.iv.setImageResource(weather.get(position).image);
		vh.tv1.setText(weather.get(position).weather);
		vh.tv2.setText(weather.get(position).tem);
		vh.tv3.setText(weather.get(position).week);
		if(position==weather.size()-1){
			convertView.findViewById(R.id.view_line).setVisibility(View.GONE);
		}else{
			convertView.findViewById(R.id.view_line).setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	class ViewHolder{
		ImageView iv;
		TextView  tv1;
		TextView  tv2;
		TextView  tv3;
	}
	class Weather{
		int image;
		String tem;
		String week;
		String weather;
	}

}

