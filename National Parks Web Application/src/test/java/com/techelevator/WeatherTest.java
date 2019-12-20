package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.techelevator.npgeek.weather.Weather;


public class WeatherTest {
	
	private Weather weather;
	
	@Before
	public void setup() {
		weather = new Weather();
	}
	
	@Test
	public void false_temp_type_converts_low_to_celcius() {
		weather.setTempType(false);
		weather.setLow(32);
		double result = weather.getLow();
		Assert.assertEquals(0, result, 0.5);
	}
	
	@Test
	public void true_temp_type_returns_low_in_f() {
		weather.setTempType(true);
		weather.setLow(32);
		double result = weather.getLow();
		Assert.assertEquals(32, result, 0.5);
	}
	
	@Test
	public void false_temp_type_converts_high_to_celcius() {
		weather.setTempType(false);
		weather.setHigh(32);
		double result = weather.getHigh();
		Assert.assertEquals(0, result, 0.5);
	}
	
	@Test
	public void true_temp_type_returns_high_in_f() {
		weather.setTempType(true);
		weather.setHigh(32);
		double result = weather.getHigh();
		Assert.assertEquals(32, result, 0.5);
	}
	
	@Test
	public void snow_forecast_and_neutral_temperatures_advises_snowshoes() {
		List<String> resultList = new ArrayList<String>();
		List<String> expected = Arrays.asList("Pack snowshoes!");
		weather.setForecast("snow");
		weather.setHigh(33);
		weather.setLow(32);
		resultList = weather.getAdvisoryList();
		assertThat(resultList, is(expected));
	}
	
	@Test
	public void rain_forecast_and_neutral_temperatures_advises_raingear() {
		List<String> resultList = new ArrayList<String>();
		List<String> expected = Arrays.asList("Pack rain gear and wear waterproof shoes!");
		weather.setForecast("rain");
		weather.setHigh(33);
		weather.setLow(32);
		resultList = weather.getAdvisoryList();
		assertThat(resultList, is(expected));
	}
	
	@Test
	public void thunderstorms_forecast_and_neutral_temperatures_advises_shelter() {
		List<String> resultList = new ArrayList<String>();
		List<String> expected = Arrays.asList("Seek shelter and avoid hiking on exposed ridges! ");
		weather.setForecast("thunderstorms");
		weather.setHigh(33);
		weather.setLow(32);
		resultList = weather.getAdvisoryList();
		assertThat(resultList, is(expected));
	}
	
	@Test
	public void sunny_forecast_and_neutral_temperatures_advises_sunblock() {
		List<String> resultList = new ArrayList<String>();
		List<String> expected = Arrays.asList("Pack sunblock! ");
		weather.setForecast("sunny");
		weather.setHigh(33);
		weather.setLow(32);
		resultList = weather.getAdvisoryList();
		assertThat(resultList, is(expected));
	}

}
