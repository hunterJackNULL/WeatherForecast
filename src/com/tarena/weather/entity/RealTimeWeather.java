package com.tarena.weather.entity;

public class RealTimeWeather {
	private String temperature;
	private String humidity;
	private String info;
	private String img;
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "RealTimeWeather [temperature=" + temperature + ", humidity="
				+ humidity + ", info=" + info + ", img=" + img + "]";
	}
	
}
