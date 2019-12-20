package com.techelevator.npgeek.weather;

import java.util.List;

public interface WeatherDao {

	List<Weather> getWeatherByCode(String code, String tempType);

}
