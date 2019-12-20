package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.weather.JDBCWeatherDao;
import com.techelevator.npgeek.weather.Weather;
import com.techelevator.npgeek.weather.WeatherDao;

public class JDBCWeatherDaoIntegrationTest extends DAOIntegrationTest {

	private WeatherDao dao;
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_NEW_WEATHER = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES ('CVNP', 6, 20, 70, 'snow')";
	
	
	@Before
	public void setup() {
		dao = new JDBCWeatherDao(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void get_weather_by_park_code() {
		String truncateSpaceSql = "TRUNCATE weather CASCADE";
		jdbcTemplate.update(truncateSpaceSql);
		jdbcTemplate.update(INSERT_NEW_WEATHER);
		
		List<Weather> weatherReturned = dao.getWeatherByCode("CVNP", "Fahrenheit");
		
		Assert.assertNotNull(weatherReturned);
		Assert.assertEquals(1, weatherReturned.size());
	}
}
