package com.tarena.weather;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.natasa.progressviews.CircleSegmentBar;
import com.natasa.progressviews.utils.ProgressStartPoint;
import com.tarena.weather.R;
import com.tarena.weather.adapter.HoriListVAdapter;
import com.tarena.weather.entity.PM25;
import com.tarena.weather.entity.RealTime;
import com.tarena.weather.entity.RealTimeWeather;
import com.tarena.weather.entity.Result;
import com.tarena.weather.entity.Weather;
import com.tarena.weather.updateable.BMainActivity;
import com.tarena.weather.updateable.HorizontalListView;
import com.tarena.weather.util.ProcessWeather;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends BMainActivity implements OnClickListener{
	private HorizontalListView listview_futureweather;//    1.1.1
	
	//一种类型的布局定义放在一起
	private ImageView iv_left;
	private ImageView iv_right;
	//weather
	private CircleSegmentBar csb;
	private TextView tv_update_time;
	private TextView tv_week;
	private TextView tv_quality;
	
	
	private Result result;
	
	String json="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"city_code\":\"101190801\",\"city_name\":\"徐州\",\"date\":\"2016-09-20\",\"time\":\"22:00:00\",\"week\":2,\"moon\":\"八月二十\",\"dataUptime\":1474382165,\"weather\":{\"temperature\":\"18\",\"humidity\":\"68\",\"info\":\"晴\",\"img\":\"0\"},\"wind\":{\"direct\":\"北风\",\"power\":\"0级\",\"offset\":null,\"windspeed\":null}},\"life\":{\"date\":\"2016-9-20\",\"info\":{\"chuanyi\":[\"热\",\"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。\"],\"ganmao\":[\"较易发\",\"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。\"],\"kongtiao\":[\"较少开启\",\"您将感到很舒适，一般不需要开启空调。\"],\"wuran\":[\"较差\",\"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。\"],\"xiche\":[\"较适宜\",\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"],\"yundong\":[\"较适宜\",\"天气较好，户外运动请注意防晒。推荐您进行室内运动。\"],\"ziwaixian\":[\"中等\",\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"]}},\"weather\":[{\"date\":\"2016-09-20\",\"info\":{\"day\":[\"0\",\"晴\",\"26\",\"东北风\",\"微风\",\"05:58\"],\"night\":[\"1\",\"多云\",\"14\",\"北风\",\"微风\",\"18:11\"]},\"week\":\"二\",\"nongli\":\"八月二十\"},{\"date\":\"2016-09-21\",\"info\":{\"dawn\":[\"1\",\"多云\",\"14\",\"北风\",\"微风\",\"18:11\"],\"day\":[\"1\",\"多云\",\"27\",\"东风\",\"微风\",\"05:58\"],\"night\":[\"0\",\"晴\",\"15\",\"东南风\",\"微风\",\"18:09\"]},\"week\":\"三\",\"nongli\":\"八月廿一\"},{\"date\":\"2016-09-22\",\"info\":{\"dawn\":[\"0\",\"晴\",\"15\",\"东南风\",\"微风\",\"18:09\"],\"day\":[\"0\",\"晴\",\"28\",\"东南风\",\"微风\",\"05:59\"],\"night\":[\"0\",\"晴\",\"17\",\"东南风\",\"微风\",\"18:08\"]},\"week\":\"四\",\"nongli\":\"八月廿二\"},{\"date\":\"2016-09-23\",\"info\":{\"dawn\":[\"0\",\"晴\",\"17\",\"东南风\",\"微风\",\"18:08\"],\"day\":[\"0\",\"晴\",\"29\",\"东南风\",\"3-4 级\",\"06:00\"],\"night\":[\"1\",\"多云\",\"18\",\"\",\"微风\",\"18:06\"]},\"week\":\"五\",\"nongli\":\"八月廿三\"},{\"date\":\"2016-09-24\",\"info\":{\"dawn\":[\"1\",\"多云\",\"18\",\"无持续风向\",\"微风\",\"18:06\"],\"day\":[\"2\",\"阴\",\"28\",\"\",\"微风\",\"06:00\"],\"night\":[\"1\",\"多云\",\"19\",\"\",\"微风\",\"18:05\"]},\"week\":\"六\",\"nongli\":\"八月廿四\"}],\"pm25\":{\"key\":\"Xuzhou\",\"show_desc\":0,\"pm25\":{\"curPm\":\"66\",\"pm25\":\"40\",\"pm10\":\"74\",\"level\":2,\"quality\":\"良\",\"des\":\"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。\"},\"dateTime\":\"2016年09月20日22时\",\"cityName\":\"徐州\"},\"jingqu\":\"\",\"date\":\"\",\"isForeign\":\"0\"}},\"error_code\":0}";
	/**
	 * 
	 * [{"name","zhangsan"},{"name","lisi"},{"name","wamgwu"}]
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		//setContentView(R.layout.activity_main);
		//TextView   
		loadLayout(R.layout.activity_main);
		initView();
		iv_left.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_ATOP);
		showWeatherBack("晴天");
		
		iv_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					
					RequestQueue rqueue=Volley.newRequestQueue(MainActivity.this);
					String url="http://op.juhe.cn/onebox/weather/query"
							+ "?cityname=%E5%BE%90%E5%B7%9E&key=15b3860"
							+ "417a0875de210d562b0be2ce3";
					Listener  listener=new Listener<String>() {
						@Override
						public void onResponse(String arg0) {
							Toast.makeText(getApplicationContext(), arg0, 1).show();
						}
					};
					ErrorListener  elistener=new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError arg0) {
							Toast.makeText(getApplicationContext(), "请求失败", 1).show();
						}
					};
					StringRequest stringRequest=new StringRequest(
							url,listener, elistener);
					
					rqueue.add(stringRequest);
					//解析数据并显示
					result=ProcessWeather.process(json);
					RealTimeWeather w=result.getRes().getData().getRealtime().getRealTimeWeather();
					RealTime   rt=result.getRes().getData().getRealtime();
					tv_update_time.setText("更新时间:"+rt.getTime());
					setTem(Integer.parseInt(w.getTemperature()));
					tv_week.setText(numToWeek(rt.getWeek()));
					PM25  pm25=result.getRes().getData().getPm25();
					tv_quality.setText(w.getInfo()+"|空气质量:"+pm25.getQuality());
			        
					ArrayList<Weather>  weathers=result.getRes().getData().getWeathers();
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		setTem(32);
		listview_futureweather=(HorizontalListView) findViewById(R.id.listview_futureweather);
		//adapter   layout
		HoriListVAdapter  adapter=new HoriListVAdapter(MainActivity.this, R.layout.future_weather_item);
		listview_futureweather.setAdapter(adapter);
	}

	private void setTem(final int temperature) {
		//Handler  发送消息和处理消息
		final Handler  handler=new Handler();
		handler.postDelayed(new Runnable() {
			int progress=0;
			@Override
			public void run() {
				progress+=2;
				if(progress<=(temperature*100/50)){
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							csb.setProgress(progress);
							csb.setText(progress*50/100+"°C");
						}
					});
					handler.postDelayed(this, 100);
				}
			}
		}, 500);
	}

	private void initView() {
		iv_left=(ImageView) findViewById(R.id.iv_left);
		iv_right=(ImageView) findViewById(R.id.iv_right);
		
		csb=(CircleSegmentBar) findViewById(R.id.circleSegmentBar);
		tv_update_time=(TextView) findViewById(R.id.tv_update_time);
		tv_week=(TextView) findViewById(R.id.tv_week);
		tv_quality=(TextView) findViewById(R.id.tv_quality);
		csb.setCircleViewPadding(2);//圆圈中的view间隔
		csb.setWidthProgressBackground(25);//进度条view背景宽度
		csb.setWidthProgressBarLine(25);//进度条宽度
		csb.setStartPositionInDegrees(ProgressStartPoint.BOTTOM);//设置开始位置，可有可无
		csb.setLinearGradientProgress(true);
	}

	@Override
	public void onClick(View v) {
		
	}
	private String numToWeek(String num){
		if(num!=null&&num.equals("1")){
			return "星期一";
		}else if(num!=null&&num.equals("2")){
			return "星期二";
		}else if(num!=null&&num.equals("3")){
			return "星期三";
		}else if(num!=null&&num.equals("4")){
			return "星期四";
		}else if(num!=null&&num.equals("5")){
			return "星期五";
		}else if(num!=null&&num.equals("6")){
			return "星期六";
		}else if(num!=null&&num.equals("7")){
			return "星期日";
		}
		return "星期日";
	}
}
