package com.tarena.weather.entity;

public class Res {
  private Data data;

public Data getData() {
	return data;
}

public void setData(Data data) {
	this.data = data;
}

@Override
public String toString() {
	return "Res [data=" + data + "]";
}
}
