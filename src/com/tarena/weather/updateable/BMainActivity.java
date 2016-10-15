package com.tarena.weather.updateable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import com.tarena.weather.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 说明：本例中的动态天气主要通过两种方式实现，一种是平移，另一种是图片切换形成动画。 可以根据需要对图片显示方式和图片运动轨迹进行处理。
 */
public class BMainActivity extends Activity {
	public static String cityTest;
	/*
	 * 天气背景属性定义
	 * ******************************************************************
	 * *********************
	 */
	private RelativeLayout today_yubao; // 背景
	public static ForecastHandler forecastHandler; // 加载数据，更新界面
	// 切换图片定时器、handler、图片标号
	public static Timer weather_timer;
	private Day_Lei_Handler day_lei_handler;
	private Night_Qing_Handler night_qing_handler;
	private Day_Rain_Handler Day_Rain_Handler;
	private Day_Snow_Handler Day_Snow_Handler;
	private Day_RainSnow_Handler Day_RainSnow_Handler;
	private Day_Wu_Handler Day_Wu_Handler;
	// 平移的图片
	private MovingPictureView w1_move1, w1_move2, w1_move3, w1_move4, w1_move5,
			w2_move1, w2_move2, w2_move3, w2_move4, w2_move5, w3_move1,
			w3_move2, w3_move3, w3_move4, w3_move5, w4_move1, w4_move2,
			w4_move3, w4_move4, w4_move5, w5_move1, w5_move2, w5_move3,
			w5_move4, w5_move5, w6_move1, w6_move2, w6_move3, w6_move4,
			w6_move5, w7_move1, w7_move2, w7_move3, w7_move4, w7_move5;
	private ImageView m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
	public static int imgIndex;
	// 平移的图片所在布局
	private RelativeLayout weather_move1, weather_move2, weather_move3,
			weather_move4, weather_move5, weather_move6, weather_move7,
			weather_move8, weather_move9, weather_move10;
	// 切换的图片所在布局
	private RelativeLayout weather_qing, weather_day_duoyun, weather_day_yin,
			weather_night_yin, weather_wu, weather_mai, weather_sha;

	// 示意
	private int nowindex = 10; // 第一个天气默认序号是10
	@SuppressLint("ResourceAsColor")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏项目标题
		/**
		 * 数据解析测试
		 */
		// String
		// xml="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"wind\":{\"windspeed\":\"4.7\",\"direct\":\"西南风\",\"power\":\"2级\",\"offset\":null},\"time\":\"16:00:00\",\"weather\":{\"humidity\":\"44\",\"img\":\"1\",\"info\":\"多云\",\"temperature\":\"33\"},\"dataUptime\":1466497681,\"date\":\"2016-06-21\",\"city_code\":\"101010100\",\"city_name\":\"北京\",\"week\":2,\"moon\":\"五月十七\"},\"life\":{\"date\":\"2016-6-21\",\"info\":{\"kongtiao\":[\"部分时间开启\",\"天气热，再加上近两天的较高温天气您将感到些燥热，因此需要适当开启制冷空调来降低温度。\"],\"yundong\":[\"较不宜\",\"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。\"],\"ziwaixian\":[\"中等\",\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"],\"ganmao\":[\"少发\",\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"],\"xiche\":[\"不宜\",\"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。\"],\"wuran\":[\"良\",\"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。\"],\"chuanyi\":[\"炎热\",\"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。\"]}},\"weather\":[{\"date\":\"2016-06-21\",\"info\":{\"night\":[\"4\",\"雷阵雨\",\"23\",\"无持续风向\",\"微风\",\"19:46\"],\"day\":[\"4\",\"雷阵雨\",\"34\",\"无持续风向\",\"微风\",\"04:46\"]},\"week\":\"二\",\"nongli\":\"五月十七\"},{\"date\":\"2016-06-22\",\"info\":{\"dawn\":[\"4\",\"雷阵雨\",\"23\",\"无持续风向\",\"微风\",\"19:46\"],\"night\":[\"4\",\"雷阵雨\",\"24\",\"无持续风向\",\"微风\",\"19:46\"],\"day\":[\"4\",\"雷阵雨\",\"34\",\"无持续风向\",\"微风\",\"04:46\"]},\"week\":\"三\",\"nongli\":\"五月十八\"},{\"date\":\"2016-06-23\",\"info\":{\"dawn\":[\"4\",\"雷阵雨\",\"24\",\"无持续风向\",\"微风\",\"19:46\"],\"night\":[\"4\",\"雷阵雨\",\"23\",\"无持续风向\",\"微风\",\"19:46\"],\"day\":[\"4\",\"雷阵雨\",\"35\",\"无持续风向\",\"微风\",\"04:46\"]},\"week\":\"四\",\"nongli\":\"五月十九\"},{\"date\":\"2016-06-24\",\"info\":{\"dawn\":[\"4\",\"雷阵雨\",\"23\",\"无持续风向\",\"微风\",\"19:46\"],\"night\":[\"0\",\"晴\",\"21\",\"无持续风向\",\"微风\",\"19:46\"],\"day\":[\"1\",\"多云\",\"31\",\"无持续风向\",\"微风\",\"04:46\"]},\"week\":\"五\",\"nongli\":\"五月二十\"},{\"date\":\"2016-06-25\",\"info\":{\"dawn\":[\"0\",\"晴\",\"21\",\"无持续风向\",\"微风\",\"19:46\"],\"night\":[\"0\",\"晴\",\"22\",\"无持续风向\",\"微风\",\"19:47\"],\"day\":[\"0\",\"晴\",\"34\",\"无持续风向\",\"微风\",\"04:47\"]},\"week\":\"六\",\"nongli\":\"五月廿一\"},{\"date\":\"2016-06-26\",\"info\":{\"night\":[\"1\",\"多云\",\"22\",\"\",\"微风\",\"19:30\"],\"day\":[\"1\",\"多云\",\"36\",\"\",\"微风\",\"07:30\"]},\"week\":\"日\",\"nongli\":\"五月廿二\"},{\"date\":\"2016-06-27\",\"info\":{\"night\":[\"1\",\"多云\",\"21\",\"\",\"4级\",\"19:30\"],\"day\":[\"1\",\"多云\",\"35\",\"\",\"4级\",\"07:30\"]},\"week\":\"一\",\"nongli\":\"五月廿三\"}],\"pm25\":{\"key\":\"\",\"show_desc\":0,\"pm25\":{\"curPm\":\"118\",\"pm25\":\"89\",\"pm10\":\"118\",\"level\":3,\"quality\":\"轻度污染\",\"des\":\"易感人群症状有轻度加剧，健康人群出现刺激症状。建议儿童、老年人及心脏病和呼吸系统疾病患者应减少长时间、高强度的户外锻炼。\"},\"dateTime\":\"2016年06月21日16时\",\"cityName\":\"北京\"},\"date\":null,\"isForeign\":0}},\"error_code\":0}";
		// process=new Process();
		// try {
		// process.processData(xml);
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
	
	}
	public void loadLayout(int layout) {
		setContentView(layout);
		initBackGroundView();
	}
	public void showWeatherBack(String weather) {
		if (weather.equals("晴天") || weather.equals("晴")) {
			nowindex = 10;
		} else if (weather.equals("多云") || weather.equals("云")) {
			nowindex = 11;
		} else if (weather.equals("阴天") || weather.equals("阴")) {

			nowindex = 12;
		} else if (weather.equals("夜阴")) {

			nowindex = 13;
		} else if (weather.equals("大雾")) {

			nowindex = 14;
		} else if (weather.equals("雾霾")) {

			nowindex = 15;
		} else if (weather.equals("风沙")) {

			nowindex = 16;
		} else if (weather.equals("下雪")) {

			nowindex = 17;
		} else if (weather.equals("雨雪")) {
			nowindex = 18;
		} else if (weather.equals("雷电")) {

			nowindex = 19;
		} else if (weather.equals("夜晴")) {

			nowindex = 20;
		} else if (weather.equals("下雨") || weather.contains("雨")) {
			nowindex = 21;
		}
		Message msg = new Message();
		Bundle b = new Bundle();
		msg.what = nowindex;
		msg.setData(b);
		forecastHandler.sendMessage(msg);
	}

	@SuppressLint("ResourceAsColor")
	private void initBackGroundView() {
		today_yubao = (RelativeLayout) findViewById(R.id.today_yubao);

		weather_qing = (RelativeLayout) findViewById(R.id.weather_qing);
		weather_day_duoyun = (RelativeLayout) findViewById(R.id.weather_day_duoyun);
		weather_day_yin = (RelativeLayout) findViewById(R.id.weather_day_yin);
		weather_night_yin = (RelativeLayout) findViewById(R.id.weather_night_yin);
		weather_wu = (RelativeLayout) findViewById(R.id.weather_wu);
		weather_mai = (RelativeLayout) findViewById(R.id.weather_mai);
		weather_sha = (RelativeLayout) findViewById(R.id.weather_sha);

		// 将所有要用到的平移的图片加载好，根据当前天气，选择显示哪些图片，
		// 我在这里只为weather_qing何weather_day_yin这两个天气添加了平移图片，你可以为其他的天气加上你需要的图片，然后好用.
		w1_move1 = new MovingPictureView(this, R.drawable.yjjc_h_a3, -300, 10,
				40);
		w1_move2 = new MovingPictureView(this, R.drawable.yjjc_h_a3, 250, 10,
				40);
		w1_move3 = new MovingPictureView(this, R.drawable.yjjc_h_a4, 480, 40,
				40);
		weather_qing.removeAllViews();
		weather_qing.addView(w1_move1);
		weather_qing.addView(w1_move2);
		weather_qing.addView(w1_move3);

		w3_move1 = new MovingPictureView(this, R.drawable.yjjc_h_d2, -250, 0,
				30);
		w3_move2 = new MovingPictureView(this, R.drawable.yjjc_h_d3, 180, 60,
				40);
		weather_day_yin.addView(w3_move1);
		weather_day_yin.addView(w3_move2);

		weather_move1 = (RelativeLayout) findViewById(R.id.weather_move1);
		weather_move2 = (RelativeLayout) findViewById(R.id.weather_move2);
		weather_move3 = (RelativeLayout) findViewById(R.id.weather_move3);
		weather_move4 = (RelativeLayout) findViewById(R.id.weather_move4);
		weather_move5 = (RelativeLayout) findViewById(R.id.weather_move5);
		weather_move6 = (RelativeLayout) findViewById(R.id.weather_move6);
		weather_move7 = (RelativeLayout) findViewById(R.id.weather_move7);
		weather_move8 = (RelativeLayout) findViewById(R.id.weather_move8);
		weather_move9 = (RelativeLayout) findViewById(R.id.weather_move9);
		weather_move10 = (RelativeLayout) findViewById(R.id.weather_move10);
		m1 = (ImageView) findViewById(R.id.m1);
		m2 = (ImageView) findViewById(R.id.m2);
		m3 = (ImageView) findViewById(R.id.m3);
		m4 = (ImageView) findViewById(R.id.m4);
		m5 = (ImageView) findViewById(R.id.m5);
		m6 = (ImageView) findViewById(R.id.m6);
		m7 = (ImageView) findViewById(R.id.m7);
		m8 = (ImageView) findViewById(R.id.m8);
		m9 = (ImageView) findViewById(R.id.m9);
		m10 = (ImageView) findViewById(R.id.m10);
		day_lei_handler = new Day_Lei_Handler(this);
		night_qing_handler = new Night_Qing_Handler(this);
		Day_Rain_Handler = new Day_Rain_Handler(this);
		Day_Snow_Handler = new Day_Snow_Handler(this);
		Day_RainSnow_Handler = new Day_RainSnow_Handler(this);
		Day_Wu_Handler = new Day_Wu_Handler(this);

		forecastHandler = new ForecastHandler();
	}

	// 加载预报数据
	class ForecastHandler extends Handler {
		// 接受数据
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 示意处理----------
			switch (msg.what) {
			case 10:
				// tv_title_update.setText("天气\n晴天");
				day_qing();
				return;
			case 11:
				// tv_title_update.setText("天气\n多云");
				day_duoyun();
				return;
			case 12:
				// tv_title_update.setText("天气\n阴天");
				day_yin();
				return;
			case 13:
				// tv_title_update.setText("天气\n夜阴");
				night_yin();
				return;
			case 14:
				// tv_title_update.setText("天气\n大雾");
				day_wu();
				return;
			case 15:
				// tv_title_update.setText("天气\n雾霾");
				day_mai();
				return;
			case 16:
				// tv_title_update.setText("天气\n风沙");
				day_sha();
				return;
			case 17:
				// tv_title_update.setText("天气\n下雪");
				day_snow();
				return;
			case 18:
				// tv_title_update.setText("天气\n雨雪");
				day_rainsnow();
				return;
			case 19:
				// tv_title_update.setText("天气\n雷电");
				day_lei();
				return;
			case 20:
				// tv_title_update.setText("天气\n夜晴");
				night_qing();
				return;
			case 21:
				// tv_title_update.setText("天气\n下雨");
				day_rain();
				return;
			default:
				break;
			}
			// 示意处理----------
		}
	}

	// 10白天_晴
	public void day_qing() {
		wordBlack();
		showweather("day_qing");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_a1);
		if (!w1_move1.isstarted) {
			new Thread(w1_move1).start();// 每一个移动的图片都是一个线程
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
	}

	// 11白天_多云
	public void day_duoyun() {
		wordBlack();
		showweather("day_duoyun");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_c1);
		if (!w1_move1.isstarted) {
			new Thread(w1_move1).start();// 这里的天气用了上一个天气的图片，也可以根据自己需要用想要的图片
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
		// new Thread(w2_move1).start();
		// new Thread(w2_move2).start();
		// new Thread(w2_move3).start();
		// new Thread(w2_move4).start();
		// new Thread(w2_move5).start();
	}

	// 12阴天
	public void day_yin() {
		wordWhite();
		showweather("day_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_d1);
		if (!w3_move1.isstarted) {
			new Thread(w3_move1).start();
			new Thread(w3_move2).start();
		}
	}

	// 13夜晚阴天
	public void night_yin() {
		wordWhite();
		showweather("night_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_l1);
		// new Thread(w4_move1).start();
		// new Thread(w4_move2).start();
	}

	// 14大雾
	public void day_wu() {
		wordBlack();
		showweather("day_wu");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_i1);
		// new Thread(w5_move1).start();
		// new Thread(w5_move2).start();
	}

	// 15霾
	public void day_mai() {
		wordBlack();
		showweather("day_mai");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_j1);
	}

	// 16风沙
	public void day_sha() {
		wordBlack();
		showweather("day_sha");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_k1);
	}

	// 17雪
	public void day_snow() {
		wordBlack();
		showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_g1);
		m1.setImageResource(R.drawable.yjjc_h_g2);
		m2.setImageResource(R.drawable.yjjc_h_g3);
		m3.setImageResource(R.drawable.yjjc_h_g4);
		m4.setImageResource(R.drawable.yjjc_h_g5);
		Day_Snow_Timer chage = new Day_Snow_Timer();
		Thread chageimg = new Thread(chage);
		chageimg.start();
	}

	// 18雨夹雪
	public void day_rainsnow() {
		wordWhite();
		showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_h1);
		m1.setImageResource(R.drawable.yjjc_h_h2);
		m2.setImageResource(R.drawable.yjjc_h_h3);
		m3.setImageResource(R.drawable.yjjc_h_h4);
		Day_RainSnow_Timer chage = new Day_RainSnow_Timer();
		Thread chageimg = new Thread(chage);
		chageimg.start();
	}

	// 19雷雨
	public void day_lei() {
		wordWhite();
		showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_f1);
		m1.setImageResource(R.drawable.yjjc_h_f2);
		m2.setImageResource(R.drawable.yjjc_h_f3);
		m3.setImageResource(R.drawable.yjjc_h_f4);
		m4.setImageResource(R.drawable.yjjc_h_f5);
		m5.setImageResource(R.drawable.yjjc_h_f6);
		m6.setImageResource(R.drawable.yjjc_h_f7);
		m7.setImageResource(R.drawable.yjjc_h_f8);
		Day_Lei_Timer chage = new Day_Lei_Timer();
		Thread chageimg = new Thread(chage);
		chageimg.start();
	}

	// 20夜晚_晴
	public void night_qing() {
		wordWhite();
		showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_b1);
		m1.setImageResource(R.drawable.yjjc_h_b2);
		m2.setImageResource(R.drawable.yjjc_h_b3);
		m3.setImageResource(R.drawable.yjjc_h_b4);
		m4.setImageResource(R.drawable.yjjc_h_b5);
		m5.setImageResource(R.drawable.yjjc_h_b6);
		Night_Qing_Timer chage = new Night_Qing_Timer();
		Thread chageimg = new Thread(chage);
		chageimg.start();
	}

	// 21雨
	public void day_rain() {
		wordWhite();
		showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_e1);
		m1.setImageResource(R.drawable.yjjc_h_e2);
		m2.setImageResource(R.drawable.yjjc_h_e3);
		m3.setImageResource(R.drawable.yjjc_h_e4);
		m4.setImageResource(R.drawable.yjjc_h_e5);
		Day_Rain_Timer chage = new Day_Rain_Timer();
		Thread chageimg = new Thread(chage);
		chageimg.start();
	}

	class Day_Rain_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 3) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.Day_Rain_Handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 300);
		}
	}

	class Night_Qing_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 4) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.night_qing_handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 1 * 500);
		}
	}

	class Day_Wu_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 4) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.Day_Wu_Handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 1 * 500);
		}
	}

	class Day_Lei_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 15) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.day_lei_handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 1 * 200);
		}
	}

	class Day_Snow_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 3) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.Day_Snow_Handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 300);
		}
	}

	class Day_RainSnow_Timer implements Runnable {
		@Override
		public void run() {
			if (BMainActivity.this.weather_timer != null) {
				BMainActivity.this.weather_timer.cancel();
				System.gc();
			}
			BMainActivity.this.weather_timer = new Timer();
			TimerTask t = new TimerTask() {
				@Override
				public void run() {
					if (BMainActivity.imgIndex > 2) {
						BMainActivity.imgIndex = 0;
					}
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("index", String.valueOf(BMainActivity.imgIndex));
					msg.setData(b);
					BMainActivity.imgIndex += 1;
					BMainActivity.this.Day_RainSnow_Handler.sendMessage(msg);
				}
			};
			BMainActivity.this.weather_timer.schedule(t, 0, 300);
		}
	}

	class Day_Snow_Handler extends Handler {
		private Activity context;

		public Day_Snow_Handler() {
		}

		public Day_Snow_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else if (msgindex == 3) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move4.setVisibility(View.VISIBLE);
			} else {// if(msgindex == 4){
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			}
		}
	}

	class Day_Wu_Handler extends Handler {
		private Activity context;

		public Day_Wu_Handler() {
		}

		public Day_Wu_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move5.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else if (msgindex == 3) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move4.setVisibility(View.VISIBLE);
			} else if (msgindex == 4) {
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move5.setVisibility(View.VISIBLE);
			} else {

			}
		}
	}

	class Day_RainSnow_Handler extends Handler {
		private Activity context;

		public Day_RainSnow_Handler() {
		}

		public Day_RainSnow_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else {

			}
		}
	}

	class Day_Rain_Handler extends Handler {
		private Activity context;

		public Day_Rain_Handler() {
		}

		public Day_Rain_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else if (msgindex == 3) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move4.setVisibility(View.VISIBLE);
			} else {// if(msgindex == 4){
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			}
		}
	}

	class Night_Qing_Handler extends Handler {
		private Activity context;

		public Night_Qing_Handler() {
		}

		public Night_Qing_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move5.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else if (msgindex == 3) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move4.setVisibility(View.VISIBLE);
			} else if (msgindex == 4) {
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move5.setVisibility(View.VISIBLE);
			} else { // if(msgindex == 5){
				weather_move5.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			}
		}
	}

	class Day_Lei_Handler extends Handler {
		private Activity context;

		public Day_Lei_Handler() {
		}

		public Day_Lei_Handler(Activity context) {
			this.context = context;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 更新UI
			int msgindex = 20;
			Bundle b = msg.getData();
			if (b.getString("index") != null) {
				msgindex = Integer.parseInt(b.getString("index"));
			}
			if (msgindex == 0) {
				weather_move7.setVisibility(View.INVISIBLE);
				weather_move1.setVisibility(View.VISIBLE);
			} else if (msgindex == 1) {
				weather_move1.setVisibility(View.INVISIBLE);
				weather_move2.setVisibility(View.VISIBLE);
			} else if (msgindex == 2) {
				weather_move2.setVisibility(View.INVISIBLE);
				weather_move3.setVisibility(View.VISIBLE);
			} else if (msgindex == 3) {
				weather_move3.setVisibility(View.INVISIBLE);
				weather_move4.setVisibility(View.VISIBLE);
			} else if (msgindex == 4) {
				weather_move4.setVisibility(View.INVISIBLE);
				weather_move5.setVisibility(View.VISIBLE);
			} else if (msgindex == 5) {
				weather_move5.setVisibility(View.INVISIBLE);
				weather_move6.setVisibility(View.VISIBLE);
			} else if (msgindex == 6) {
				weather_move6.setVisibility(View.INVISIBLE);
				weather_move7.setVisibility(View.VISIBLE);
			} else {
				weather_move7.setVisibility(View.INVISIBLE);
			}
		}
	}

	// 字体颜色黑色
	public void wordBlack() {
	};

	// 字体颜色白色
	public void wordWhite() {
	};

	// 显示某一天气,显示帧天气传参数other。
	public void showweather(String weather) {
		initWeatherLayout();
		if (weather.equals("day_qing")) {
			weather_qing.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_duoyun")) {
			weather_qing.setVisibility(View.VISIBLE);// 没有为weather_day_duoyun添加图片，所以暂时用的是day_qing天气中的图片。你可以自己在create方法中加载，在这里就可以显示了
			weather_day_duoyun.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_yin")) {
			weather_day_yin.setVisibility(View.VISIBLE);
		} else if (weather.equals("night_yin")) {
			weather_night_yin.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_wu")) {
			weather_wu.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_mai")) {
			weather_mai.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_sha")) {
			weather_sha.setVisibility(View.VISIBLE);
		} else {

		}
	}

	// 初始化天气布局
	public void initWeatherLayout() {
		if (BMainActivity.this.weather_timer != null) {
			BMainActivity.this.weather_timer.cancel();
		}
		weather_qing.setVisibility(View.INVISIBLE);
		weather_day_duoyun.setVisibility(View.INVISIBLE);
		weather_day_yin.setVisibility(View.INVISIBLE);
		weather_night_yin.setVisibility(View.INVISIBLE);
		weather_wu.setVisibility(View.INVISIBLE);
		weather_mai.setVisibility(View.INVISIBLE);
		weather_sha.setVisibility(View.INVISIBLE);
		weather_move1.setVisibility(View.INVISIBLE);
		weather_move2.setVisibility(View.INVISIBLE);
		weather_move3.setVisibility(View.INVISIBLE);
		weather_move4.setVisibility(View.INVISIBLE);
		weather_move5.setVisibility(View.INVISIBLE);
		weather_move6.setVisibility(View.INVISIBLE);
		weather_move7.setVisibility(View.INVISIBLE);
		weather_move8.setVisibility(View.INVISIBLE);
		weather_move9.setVisibility(View.INVISIBLE);
		weather_move10.setVisibility(View.INVISIBLE);
	}
}
