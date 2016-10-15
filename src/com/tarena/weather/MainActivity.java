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
	
	//һ�����͵Ĳ��ֶ������һ��
	private ImageView iv_left;
	private ImageView iv_right;
	//weather
	private CircleSegmentBar csb;
	private TextView tv_update_time;
	private TextView tv_week;
	private TextView tv_quality;
	
	
	private Result result;
	
	String json="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"city_code\":\"101190801\",\"city_name\":\"����\",\"date\":\"2016-09-20\",\"time\":\"22:00:00\",\"week\":2,\"moon\":\"���¶�ʮ\",\"dataUptime\":1474382165,\"weather\":{\"temperature\":\"18\",\"humidity\":\"68\",\"info\":\"��\",\"img\":\"0\"},\"wind\":{\"direct\":\"����\",\"power\":\"0��\",\"offset\":null,\"windspeed\":null}},\"life\":{\"date\":\"2016-9-20\",\"info\":{\"chuanyi\":[\"��\",\"�����ȣ������Ŷ�ȹ���̿㡢�̱����ס�T�����ļ���װ��\"],\"ganmao\":[\"���׷�\",\"��ҹ�²�ϴ󣬽��׷�����ð�����ʵ������·������ʽ�����������ע�������\"],\"kongtiao\":[\"���ٿ���\",\"�����е������ʣ�һ�㲻��Ҫ�����յ���\"],\"wuran\":[\"�ϲ�\",\"���������ϲ����ڿ�����Ⱦ��ϡ�͡���ɢ����������ʵ���������ʱ�䡣\"],\"xiche\":[\"������\",\"������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣\"],\"yundong\":[\"������\",\"�����Ϻã������˶���ע���ɹ���Ƽ������������˶���\"],\"ziwaixian\":[\"�е�\",\"���е�ǿ�������߷������������ʱ����Ϳ��SPF����15��PA+�ķ�ɹ����Ʒ����ñ�ӡ�̫������\"]}},\"weather\":[{\"date\":\"2016-09-20\",\"info\":{\"day\":[\"0\",\"��\",\"26\",\"������\",\"΢��\",\"05:58\"],\"night\":[\"1\",\"����\",\"14\",\"����\",\"΢��\",\"18:11\"]},\"week\":\"��\",\"nongli\":\"���¶�ʮ\"},{\"date\":\"2016-09-21\",\"info\":{\"dawn\":[\"1\",\"����\",\"14\",\"����\",\"΢��\",\"18:11\"],\"day\":[\"1\",\"����\",\"27\",\"����\",\"΢��\",\"05:58\"],\"night\":[\"0\",\"��\",\"15\",\"���Ϸ�\",\"΢��\",\"18:09\"]},\"week\":\"��\",\"nongli\":\"����إһ\"},{\"date\":\"2016-09-22\",\"info\":{\"dawn\":[\"0\",\"��\",\"15\",\"���Ϸ�\",\"΢��\",\"18:09\"],\"day\":[\"0\",\"��\",\"28\",\"���Ϸ�\",\"΢��\",\"05:59\"],\"night\":[\"0\",\"��\",\"17\",\"���Ϸ�\",\"΢��\",\"18:08\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-09-23\",\"info\":{\"dawn\":[\"0\",\"��\",\"17\",\"���Ϸ�\",\"΢��\",\"18:08\"],\"day\":[\"0\",\"��\",\"29\",\"���Ϸ�\",\"3-4 ��\",\"06:00\"],\"night\":[\"1\",\"����\",\"18\",\"\",\"΢��\",\"18:06\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-09-24\",\"info\":{\"dawn\":[\"1\",\"����\",\"18\",\"�޳�������\",\"΢��\",\"18:06\"],\"day\":[\"2\",\"��\",\"28\",\"\",\"΢��\",\"06:00\"],\"night\":[\"1\",\"����\",\"19\",\"\",\"΢��\",\"18:05\"]},\"week\":\"��\",\"nongli\":\"����إ��\"}],\"pm25\":{\"key\":\"Xuzhou\",\"show_desc\":0,\"pm25\":{\"curPm\":\"66\",\"pm25\":\"40\",\"pm10\":\"74\",\"level\":2,\"quality\":\"��\",\"des\":\"���Խ��ܵģ�����������ĳ����Ⱦ���ر����е������⣬�Թ��ڽ���û��Σ����\"},\"dateTime\":\"2016��09��20��22ʱ\",\"cityName\":\"����\"},\"jingqu\":\"\",\"date\":\"\",\"isForeign\":\"0\"}},\"error_code\":0}";
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
		showWeatherBack("����");
		
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
							Toast.makeText(getApplicationContext(), "����ʧ��", 1).show();
						}
					};
					StringRequest stringRequest=new StringRequest(
							url,listener, elistener);
					
					rqueue.add(stringRequest);
					//�������ݲ���ʾ
					result=ProcessWeather.process(json);
					RealTimeWeather w=result.getRes().getData().getRealtime().getRealTimeWeather();
					RealTime   rt=result.getRes().getData().getRealtime();
					tv_update_time.setText("����ʱ��:"+rt.getTime());
					setTem(Integer.parseInt(w.getTemperature()));
					tv_week.setText(numToWeek(rt.getWeek()));
					PM25  pm25=result.getRes().getData().getPm25();
					tv_quality.setText(w.getInfo()+"|��������:"+pm25.getQuality());
			        
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
		//Handler  ������Ϣ�ʹ�����Ϣ
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
							csb.setText(progress*50/100+"��C");
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
		csb.setCircleViewPadding(2);//ԲȦ�е�view���
		csb.setWidthProgressBackground(25);//������view�������
		csb.setWidthProgressBarLine(25);//���������
		csb.setStartPositionInDegrees(ProgressStartPoint.BOTTOM);//���ÿ�ʼλ�ã����п���
		csb.setLinearGradientProgress(true);
	}

	@Override
	public void onClick(View v) {
		
	}
	private String numToWeek(String num){
		if(num!=null&&num.equals("1")){
			return "����һ";
		}else if(num!=null&&num.equals("2")){
			return "���ڶ�";
		}else if(num!=null&&num.equals("3")){
			return "������";
		}else if(num!=null&&num.equals("4")){
			return "������";
		}else if(num!=null&&num.equals("5")){
			return "������";
		}else if(num!=null&&num.equals("6")){
			return "������";
		}else if(num!=null&&num.equals("7")){
			return "������";
		}
		return "������";
	}
}
