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
 * ˵���������еĶ�̬������Ҫͨ�����ַ�ʽʵ�֣�һ����ƽ�ƣ���һ����ͼƬ�л��γɶ����� ���Ը�����Ҫ��ͼƬ��ʾ��ʽ��ͼƬ�˶��켣���д���
 */
public class BMainActivity extends Activity {
	public static String cityTest;
	/*
	 * �����������Զ���
	 * ******************************************************************
	 * *********************
	 */
	private RelativeLayout today_yubao; // ����
	public static ForecastHandler forecastHandler; // �������ݣ����½���
	// �л�ͼƬ��ʱ����handler��ͼƬ���
	public static Timer weather_timer;
	private Day_Lei_Handler day_lei_handler;
	private Night_Qing_Handler night_qing_handler;
	private Day_Rain_Handler Day_Rain_Handler;
	private Day_Snow_Handler Day_Snow_Handler;
	private Day_RainSnow_Handler Day_RainSnow_Handler;
	private Day_Wu_Handler Day_Wu_Handler;
	// ƽ�Ƶ�ͼƬ
	private MovingPictureView w1_move1, w1_move2, w1_move3, w1_move4, w1_move5,
			w2_move1, w2_move2, w2_move3, w2_move4, w2_move5, w3_move1,
			w3_move2, w3_move3, w3_move4, w3_move5, w4_move1, w4_move2,
			w4_move3, w4_move4, w4_move5, w5_move1, w5_move2, w5_move3,
			w5_move4, w5_move5, w6_move1, w6_move2, w6_move3, w6_move4,
			w6_move5, w7_move1, w7_move2, w7_move3, w7_move4, w7_move5;
	private ImageView m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
	public static int imgIndex;
	// ƽ�Ƶ�ͼƬ���ڲ���
	private RelativeLayout weather_move1, weather_move2, weather_move3,
			weather_move4, weather_move5, weather_move6, weather_move7,
			weather_move8, weather_move9, weather_move10;
	// �л���ͼƬ���ڲ���
	private RelativeLayout weather_qing, weather_day_duoyun, weather_day_yin,
			weather_night_yin, weather_wu, weather_mai, weather_sha;

	// ʾ��
	private int nowindex = 10; // ��һ������Ĭ�������10
	@SuppressLint("ResourceAsColor")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // ������Ŀ����
		/**
		 * ���ݽ�������
		 */
		// String
		// xml="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"wind\":{\"windspeed\":\"4.7\",\"direct\":\"���Ϸ�\",\"power\":\"2��\",\"offset\":null},\"time\":\"16:00:00\",\"weather\":{\"humidity\":\"44\",\"img\":\"1\",\"info\":\"����\",\"temperature\":\"33\"},\"dataUptime\":1466497681,\"date\":\"2016-06-21\",\"city_code\":\"101010100\",\"city_name\":\"����\",\"week\":2,\"moon\":\"����ʮ��\"},\"life\":{\"date\":\"2016-6-21\",\"info\":{\"kongtiao\":[\"����ʱ�俪��\",\"�����ȣ��ټ��Ͻ�����Ľϸ������������е�Щ���ȣ������Ҫ�ʵ���������յ��������¶ȡ�\"],\"yundong\":[\"�ϲ���\",\"�н�ˮ���Ƽ��������ڽ��н��������˶�������ֻ����˶�����ע��Я����߲�ע����������\"],\"ziwaixian\":[\"�е�\",\"���е�ǿ�������߷������������ʱ����Ϳ��SPF����15��PA+�ķ�ɹ����Ʒ����ñ�ӡ�̫������\"],\"ganmao\":[\"�ٷ�\",\"���������������ˣ�������ð���ʽϵ͡�������ⳤ�ڴ��ڿյ������У��Է���ð��\"],\"xiche\":[\"����\",\"����ϴ����δ��24Сʱ�����꣬����ڴ��ڼ�ϴ������ˮ��·�ϵ���ˮ���ܻ��ٴ�Ū�����İ�����\"],\"wuran\":[\"��\",\"�������������ڿ�����Ⱦ��ϡ�͡���ɢ����������������������\"],\"chuanyi\":[\"����\",\"�������ȣ������Ŷ�������ȹ���̿㡢����T�����������ļ���װ��\"]}},\"weather\":[{\"date\":\"2016-06-21\",\"info\":{\"night\":[\"4\",\"������\",\"23\",\"�޳�������\",\"΢��\",\"19:46\"],\"day\":[\"4\",\"������\",\"34\",\"�޳�������\",\"΢��\",\"04:46\"]},\"week\":\"��\",\"nongli\":\"����ʮ��\"},{\"date\":\"2016-06-22\",\"info\":{\"dawn\":[\"4\",\"������\",\"23\",\"�޳�������\",\"΢��\",\"19:46\"],\"night\":[\"4\",\"������\",\"24\",\"�޳�������\",\"΢��\",\"19:46\"],\"day\":[\"4\",\"������\",\"34\",\"�޳�������\",\"΢��\",\"04:46\"]},\"week\":\"��\",\"nongli\":\"����ʮ��\"},{\"date\":\"2016-06-23\",\"info\":{\"dawn\":[\"4\",\"������\",\"24\",\"�޳�������\",\"΢��\",\"19:46\"],\"night\":[\"4\",\"������\",\"23\",\"�޳�������\",\"΢��\",\"19:46\"],\"day\":[\"4\",\"������\",\"35\",\"�޳�������\",\"΢��\",\"04:46\"]},\"week\":\"��\",\"nongli\":\"����ʮ��\"},{\"date\":\"2016-06-24\",\"info\":{\"dawn\":[\"4\",\"������\",\"23\",\"�޳�������\",\"΢��\",\"19:46\"],\"night\":[\"0\",\"��\",\"21\",\"�޳�������\",\"΢��\",\"19:46\"],\"day\":[\"1\",\"����\",\"31\",\"�޳�������\",\"΢��\",\"04:46\"]},\"week\":\"��\",\"nongli\":\"���¶�ʮ\"},{\"date\":\"2016-06-25\",\"info\":{\"dawn\":[\"0\",\"��\",\"21\",\"�޳�������\",\"΢��\",\"19:46\"],\"night\":[\"0\",\"��\",\"22\",\"�޳�������\",\"΢��\",\"19:47\"],\"day\":[\"0\",\"��\",\"34\",\"�޳�������\",\"΢��\",\"04:47\"]},\"week\":\"��\",\"nongli\":\"����إһ\"},{\"date\":\"2016-06-26\",\"info\":{\"night\":[\"1\",\"����\",\"22\",\"\",\"΢��\",\"19:30\"],\"day\":[\"1\",\"����\",\"36\",\"\",\"΢��\",\"07:30\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-06-27\",\"info\":{\"night\":[\"1\",\"����\",\"21\",\"\",\"4��\",\"19:30\"],\"day\":[\"1\",\"����\",\"35\",\"\",\"4��\",\"07:30\"]},\"week\":\"һ\",\"nongli\":\"����إ��\"}],\"pm25\":{\"key\":\"\",\"show_desc\":0,\"pm25\":{\"curPm\":\"118\",\"pm25\":\"89\",\"pm10\":\"118\",\"level\":3,\"quality\":\"�����Ⱦ\",\"des\":\"�׸���Ⱥ֢״����ȼӾ磬������Ⱥ���ִ̼�֢״�������ͯ�������˼����ಡ�ͺ���ϵͳ��������Ӧ���ٳ�ʱ�䡢��ǿ�ȵĻ��������\"},\"dateTime\":\"2016��06��21��16ʱ\",\"cityName\":\"����\"},\"date\":null,\"isForeign\":0}},\"error_code\":0}";
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
		if (weather.equals("����") || weather.equals("��")) {
			nowindex = 10;
		} else if (weather.equals("����") || weather.equals("��")) {
			nowindex = 11;
		} else if (weather.equals("����") || weather.equals("��")) {

			nowindex = 12;
		} else if (weather.equals("ҹ��")) {

			nowindex = 13;
		} else if (weather.equals("����")) {

			nowindex = 14;
		} else if (weather.equals("����")) {

			nowindex = 15;
		} else if (weather.equals("��ɳ")) {

			nowindex = 16;
		} else if (weather.equals("��ѩ")) {

			nowindex = 17;
		} else if (weather.equals("��ѩ")) {
			nowindex = 18;
		} else if (weather.equals("�׵�")) {

			nowindex = 19;
		} else if (weather.equals("ҹ��")) {

			nowindex = 20;
		} else if (weather.equals("����") || weather.contains("��")) {
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

		// ������Ҫ�õ���ƽ�Ƶ�ͼƬ���غã����ݵ�ǰ������ѡ����ʾ��ЩͼƬ��
		// ��������ֻΪweather_qing��weather_day_yin���������������ƽ��ͼƬ�������Ϊ������������������Ҫ��ͼƬ��Ȼ�����.
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

	// ����Ԥ������
	class ForecastHandler extends Handler {
		// ��������
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ʾ�⴦��----------
			switch (msg.what) {
			case 10:
				// tv_title_update.setText("����\n����");
				day_qing();
				return;
			case 11:
				// tv_title_update.setText("����\n����");
				day_duoyun();
				return;
			case 12:
				// tv_title_update.setText("����\n����");
				day_yin();
				return;
			case 13:
				// tv_title_update.setText("����\nҹ��");
				night_yin();
				return;
			case 14:
				// tv_title_update.setText("����\n����");
				day_wu();
				return;
			case 15:
				// tv_title_update.setText("����\n����");
				day_mai();
				return;
			case 16:
				// tv_title_update.setText("����\n��ɳ");
				day_sha();
				return;
			case 17:
				// tv_title_update.setText("����\n��ѩ");
				day_snow();
				return;
			case 18:
				// tv_title_update.setText("����\n��ѩ");
				day_rainsnow();
				return;
			case 19:
				// tv_title_update.setText("����\n�׵�");
				day_lei();
				return;
			case 20:
				// tv_title_update.setText("����\nҹ��");
				night_qing();
				return;
			case 21:
				// tv_title_update.setText("����\n����");
				day_rain();
				return;
			default:
				break;
			}
			// ʾ�⴦��----------
		}
	}

	// 10����_��
	public void day_qing() {
		wordBlack();
		showweather("day_qing");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_a1);
		if (!w1_move1.isstarted) {
			new Thread(w1_move1).start();// ÿһ���ƶ���ͼƬ����һ���߳�
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
	}

	// 11����_����
	public void day_duoyun() {
		wordBlack();
		showweather("day_duoyun");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_c1);
		if (!w1_move1.isstarted) {
			new Thread(w1_move1).start();// ���������������һ��������ͼƬ��Ҳ���Ը����Լ���Ҫ����Ҫ��ͼƬ
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
		// new Thread(w2_move1).start();
		// new Thread(w2_move2).start();
		// new Thread(w2_move3).start();
		// new Thread(w2_move4).start();
		// new Thread(w2_move5).start();
	}

	// 12����
	public void day_yin() {
		wordWhite();
		showweather("day_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_d1);
		if (!w3_move1.isstarted) {
			new Thread(w3_move1).start();
			new Thread(w3_move2).start();
		}
	}

	// 13ҹ������
	public void night_yin() {
		wordWhite();
		showweather("night_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_l1);
		// new Thread(w4_move1).start();
		// new Thread(w4_move2).start();
	}

	// 14����
	public void day_wu() {
		wordBlack();
		showweather("day_wu");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_i1);
		// new Thread(w5_move1).start();
		// new Thread(w5_move2).start();
	}

	// 15��
	public void day_mai() {
		wordBlack();
		showweather("day_mai");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_j1);
	}

	// 16��ɳ
	public void day_sha() {
		wordBlack();
		showweather("day_sha");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_k1);
	}

	// 17ѩ
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

	// 18���ѩ
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

	// 19����
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

	// 20ҹ��_��
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

	// 21��
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
			// ����UI
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

	// ������ɫ��ɫ
	public void wordBlack() {
	};

	// ������ɫ��ɫ
	public void wordWhite() {
	};

	// ��ʾĳһ����,��ʾ֡����������other��
	public void showweather(String weather) {
		initWeatherLayout();
		if (weather.equals("day_qing")) {
			weather_qing.setVisibility(View.VISIBLE);
		} else if (weather.equals("day_duoyun")) {
			weather_qing.setVisibility(View.VISIBLE);// û��Ϊweather_day_duoyun���ͼƬ��������ʱ�õ���day_qing�����е�ͼƬ��������Լ���create�����м��أ�������Ϳ�����ʾ��
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

	// ��ʼ����������
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
