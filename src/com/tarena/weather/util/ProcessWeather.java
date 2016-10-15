package com.tarena.weather.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tarena.weather.entity.Data;
import com.tarena.weather.entity.PM25;
import com.tarena.weather.entity.RealTime;
import com.tarena.weather.entity.RealTimeWeather;
import com.tarena.weather.entity.Res;
import com.tarena.weather.entity.Result;
import com.tarena.weather.entity.Weather;
import com.tarena.weather.entity.Wind;

public class ProcessWeather {
	public static Result process(String json) throws JSONException{
		Result  result=new Result();
		JSONObject  jobj_result=new JSONObject(json);
		result.setReason(jobj_result.getString("reason"));
		result.setError_code(jobj_result.getString("error_code"));
		
		Res res=new Res();
		JSONObject jobj_res=jobj_result.getJSONObject("result");
		
		Data  data=new Data();
		JSONObject jobj_data=jobj_res.getJSONObject("data");
		data.setDate(jobj_data.getString("date"));
		data.setJingqu(jobj_data.getString("jingqu"));
		data.setIsForeign(jobj_data.getString("isForeign"));
		//Realtime解析
		RealTime realtime=new RealTime();
		JSONObject jobj_realtime=jobj_data.getJSONObject("realtime");
		realtime.setCity_code(jobj_realtime.getString("city_code"));
		realtime.setCity_name(jobj_realtime.getString("city_name"));
		realtime.setDate(jobj_realtime.getString("date"));
		realtime.setTime(jobj_realtime.getString("time"));
		realtime.setWeek(jobj_realtime.getString("week"));
		realtime.setMoon(jobj_realtime.getString("moon"));
		realtime.setDataUptime(jobj_realtime.getString("dataUptime"));
		
		RealTimeWeather realTimeWeather=new RealTimeWeather();
		JSONObject jobj_realtimeweather=jobj_realtime.getJSONObject("weather");
		realTimeWeather.setHumidity(jobj_realtimeweather.getString("humidity"));
		realTimeWeather.setTemperature(jobj_realtimeweather.getString("temperature"));
		realTimeWeather.setInfo(jobj_realtimeweather.getString("info"));
		realTimeWeather.setImg(jobj_realtimeweather.getString("img"));
		
		Wind  wind=new Wind();
		JSONObject jobj_wind=jobj_realtime.getJSONObject("wind");
		wind.setDirect(jobj_wind.getString("direct"));
		wind.setPower(jobj_wind.getString("power"));
		wind.setOffet(jobj_wind.getString("offset"));
		wind.setWindspeed(jobj_wind.getString("windspeed"));
		
		realtime.setRealTimeWeather(realTimeWeather);
		realtime.setWind(wind);
		data.setRealtime(realtime);
		
		//PM25快速解析
		PM25  pm25=new PM25();
		JSONObject  jobj_pm25=jobj_data.getJSONObject("pm25").getJSONObject("pm25");
		pm25.setQuality(jobj_pm25.getString("quality"));
		
		data.setPm25(pm25);
		
		//解析未来天气
		ArrayList<Weather>  weathers=new ArrayList<Weather>();
		JSONArray  jarray_weather=jobj_data.getJSONArray("weather");
		for(int i=0;i<jarray_weather.length();i++){
			Weather weather=new Weather();
			JSONObject  jobj_weather=jarray_weather.getJSONObject(i);
			weather.setWeek(jobj_weather.getString("week"));
			JSONObject  jobj_weatherinfo=jobj_weather.getJSONObject("info");
			JSONArray jarray_day=jobj_weatherinfo.getJSONArray("day");
		    weather.setDayWeather(jarray_day.getString(1));
		    weather.setDayTemapure(jarray_day.getString(2));
			
			JSONArray jarray_night=jobj_weatherinfo.getJSONArray("night");
			 weather.setNightTemapure(jarray_day.getString(2));
			
			weathers.add(weather);
		}
		
		
		
		
		
		data.setWeathers(weathers);
		
		res.setData(data);
		result.setRes(res);
		return result;
	}

}
