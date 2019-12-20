package com.techelevator.npgeek.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDao implements WeatherDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDao(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	@Override
	public List <Weather> getWeatherByCode(String code, String tempType) {
		List<Weather> weathers = new ArrayList<Weather>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ?", code); 
		
		while(rows.next()) {
			Weather weather = mapRowToWeather(rows, tempType);
			weathers.add(weather);
		}
		
		return weathers;
	}

	private Weather mapRowToWeather(SqlRowSet row, String tempType) {
		Weather weather = new Weather();
		
		weather.setTempType(convertToBoolean(tempType));
		weather.setCode(row.getString("parkcode"));
		weather.setForecastDay(row.getInt("fivedayforecastvalue"));
		weather.setLow(row.getDouble("low"));
		weather.setHigh(row.getDouble("high"));
		weather.setForecast(row.getString("forecast"));
		return weather;
	}
	
	private boolean convertToBoolean(String tempType) {
		if (tempType.equals("Fahrenheit")) {
			return true;
		} else return false;
	}
	
		
		
	
	
}
