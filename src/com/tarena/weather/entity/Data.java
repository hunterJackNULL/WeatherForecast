package com.tarena.weather.entity;

import java.util.ArrayList;

public class Data {
	private String jingqu;
	private String date;
	private String isForeign;
	private RealTime realtime;
	private Life     life;
	private ArrayList<Weather> weathers;
	private PM25  pm25;
	public String getJingqu() {
		return jingqu;
	}
	public void setJingqu(String jingqu) {
		this.jingqu = jingqu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIsForeign() {
		return isForeign;
	}
	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}
	public RealTime getRealtime() {
		return realtime;
	}
	public void setRealtime(RealTime realtime) {
		this.realtime = realtime;
	}
	public Life getLife() {
		return life;
	}
	public void setLife(Life life) {
		this.life = life;
	}
	public ArrayList<Weather> getWeathers() {
		return weathers;
	}
	public void setWeathers(ArrayList<Weather> weathers) {
		this.weathers = weathers;
	}
	public PM25 getPm25() {
		return pm25;
	}
	public void setPm25(PM25 pm25) {
		this.pm25 = pm25;
	}
	@Override
	public String toString() {
		return "Data [jingqu=" + jingqu + ", date=" + date + ", isForeign="
				+ isForeign + "]";
	}
}
