package com.techelevator.npgeek.weather;

import java.util.ArrayList;
import java.util.List;

public class Weather {

	private String code;
	private int forecastDay;
	private double low;
	private double high;
	private String forecast;
	private boolean tempType = true;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getForecastDay() {
		return forecastDay;
	}

	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}

	public double getLow() {
		if (tempType == false) {
			return toCelcius(this.low);
		} else return low;
	}

	public void setLow(double low) {
			this.low = low;
		
	}

	public double getHigh() {
		if (tempType == false) {
			return toCelcius(this.high);
		} else return high;
	}

	public void setHigh(double high) {
			this.high = high;
		
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public boolean isTempType() {
		return tempType;
	}

	public void setTempType(boolean tempType) {
		this.tempType = tempType;
	}

	public List<String> getAdvisoryList() {
		
			List<String> listAdvisory = new ArrayList<String>();
			
			String weatherForecast = this.forecast;
			
			if (weatherForecast.equals("snow")) {
				listAdvisory.add("Pack snowshoes!");
			} else if (weatherForecast.equals("rain")) {
				listAdvisory.add("Pack rain gear and wear waterproof shoes!");
			} else if (weatherForecast.equals("thunderstorms")) {
				listAdvisory.add("Seek shelter and avoid hiking on exposed ridges! ");
			} else if (weatherForecast.equals("sunny")) {
				listAdvisory.add("Pack sunblock! ");
			}
			
			if ((this.tempType == false && this.toCelcius(this.high) - this.toCelcius(this.low) > 20) || (this.tempType == true && this.high - this.low > 20) ) {
				listAdvisory.add("Wear breathable layers! ");
			} else if ((this.tempType == false && this.toCelcius(this.high) > 23) || (this.tempType == true && this.high > 75)) {
				listAdvisory.add("Bring an extra gallon of water!");
			} else if ((this.tempType == false && this.toCelcius(this.low) < -6) || (this.tempType == true && this.low < 20)) {
				listAdvisory.add("WARNING! It is dangerous to expose yourself to temperatures this low!");
			}
		return listAdvisory;
	}
	
	private double toCelcius(double temperature) {
		return (temperature - 32) / 1.8;	
	}


}
