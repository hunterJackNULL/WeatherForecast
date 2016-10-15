package com.tarena.weather.entity;

public class Result {
	private String reason;
	private String error_code ;
	private Res res;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public Res getRes() {
		return res;
	}
	public void setRes(Res res) {
		this.res = res;
	}
	@Override
	public String toString() {
		return "Result [reason=" + reason + ", error_code=" + error_code + "]";
	}
	
}
