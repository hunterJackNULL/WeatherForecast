package com.tarena.weather.entity;

public class Wind {
	private String direct;
	private String power;
	private String offet;
	private String windspeed;
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getOffet() {
		return offet;
	}
	public void setOffet(String offet) {
		this.offet = offet;
	}
	public String getWindspeed() {
		return windspeed;
	}
	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}
	@Override
	public String toString() {
		return "Wind [direct=" + direct + ", power=" + power + ", offet="
				+ offet + ", windspeed=" + windspeed + "]";
	}
	
}
