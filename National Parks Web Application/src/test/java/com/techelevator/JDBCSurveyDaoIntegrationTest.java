package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.survey.JDBCSurveyDao;
import com.techelevator.npgeek.survey.Survey;
import com.techelevator.npgeek.survey.SurveyDao;



public class JDBCSurveyDaoIntegrationTest extends DAOIntegrationTest {

	private SurveyDao dao;
	private JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void setup() {
		dao = new JDBCSurveyDao(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void create_survey() {
		String truncateSurveySql = "TRUNCATE survey_result CASCADE";
		jdbcTemplate.update(truncateSurveySql);
		
		Survey survey = new Survey();
		survey.setActivityLevel("inactive");
		survey.setEmailAddress("test@test.com");
		survey.setParkCode("ENP");
		survey.setState("OH");
		
		Survey result = dao.createSurvey(survey);

		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT activitylevel, emailaddress, parkcode, state FROM survey_result WHERE surveyid = ?", result.getSurveyID());
		Assert.assertNotNull(results);
		Assert.assertTrue(results.next());
		Assert.assertEquals("ENP", results.getString("parkcode"));
		Assert.assertEquals("test@test.com", results.getString("emailaddress"));
		Assert.assertEquals("OH", results.getString("state"));
		Assert.assertEquals("inactive", results.getString("activitylevel"));
	}
}
