package com.techelevator.npgeek;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.survey.Survey;
import com.techelevator.npgeek.survey.SurveyDao;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyDao surveyDao;

	
	
	@RequestMapping(path = "/survey", method = RequestMethod.GET)
		public String getSurveyPage(ModelMap map) {
		if(!map.containsAttribute("survey")) {
			map.put("survey", new Survey());
		}
		
			return "survey";
		
	}
	
	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") Survey newSurvey, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors() ) {
			return "survey";
		}
		surveyDao.createSurvey(newSurvey);
		
		attr.addFlashAttribute("survey", newSurvey);
		return "redirect:/favoritePark";
	}
	
}
	


