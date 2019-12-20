package com.techelevator.npgeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.park.Park;
import com.techelevator.npgeek.park.ParkDao;
import com.techelevator.npgeek.weather.Weather;
import com.techelevator.npgeek.weather.WeatherDao;

@Controller
@SessionAttributes ({"tempType", "code"})
public class ParkController {
	
	
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getHomePage(ModelMap map) {

			map.addAttribute("parks", parkDao.getAll());		
			return "homePage";
		
	}
	@RequestMapping(path = "/parkDetail", method = RequestMethod.GET) 
		public String getParkDetail(ModelMap map, @RequestParam String code, @RequestParam(defaultValue = "Fahrenheit")String tempType ) {
			Park park = parkDao.getByCode(code);
			List<Weather> weatherList = weatherDao.getWeatherByCode(code, tempType);
			
			map.addAttribute("park", park);
			map.addAttribute("weather", weatherList);
			map.addAttribute("tempType", tempType);
			return "parkDetail";
		}
	@RequestMapping(path = "/favoritePark", method = RequestMethod.GET)
	public String showResultsPage(ModelMap map) {
		
		map.addAttribute("parks", parkDao.getAllParksWithSurveys());
		return "favoritePark";
	}
	
	}
	

