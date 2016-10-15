package com.tarena.weather.entity;

public class RealTime {
	private String city_name;
	private String city_code;
	private String date;
	private String time;
	private String week;
	private String moon;
	private String dataUptime;
	private RealTimeWeather realTimeWeather;
	private Wind wind;
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMoon() {
		return moon;
	}
	public void setMoon(String moon) {
		this.moon = moon;
	}
	public String getDataUptime() {
		return dataUptime;
	}
	public void setDataUptime(String dataUptime) {
		this.dataUptime = dataUptime;
	}
	public RealTimeWeather getRealTimeWeather() {
		return realTimeWeather;
	}
	public void setRealTimeWeather(RealTimeWeather realTimeWeather) {
		this.realTimeWeather = realTimeWeather;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	@Override
	public String toString() {
		return "RealTime [city_name=" + city_name + ", city_code=" + city_code
				+ ", date=" + date + ", time=" + time + ", week=" + week
				+ ", moon=" + moon + ", dataUptime=" + dataUptime + "]";
	}
	

}
