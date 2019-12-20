package com.techelevator.npgeek.survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDao implements SurveyDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDao(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public Survey createSurvey(Survey survey) {
		String insertSql = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (DEFAULT, ?,?,?,?) RETURNING surveyid";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		rows.next();
		long surveyId = rows.getLong("surveyid");
		
		survey.setSurveyID(surveyId);
		return survey;
	}
}
