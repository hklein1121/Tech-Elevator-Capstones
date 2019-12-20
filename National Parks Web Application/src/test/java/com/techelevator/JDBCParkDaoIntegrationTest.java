package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.park.JDBCParkDao;
import com.techelevator.npgeek.park.Park;
import com.techelevator.npgeek.park.ParkDao;



public class JDBCParkDaoIntegrationTest extends DAOIntegrationTest {

	private ParkDao dao;
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_NEW_PARK ="INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) " + 
			"VALUES ('NP', 'New Park', 'OH', 12345, 83838, 123.3, 7, 'Tropical', 2002, 1234321, 'This is a quote', 'John Doe', 'This is a new Park', 15, 700)";
	private static final String INSERT_NEW_SURVEY = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES ('NP', 'test@test.com', 'OH', 'active')";
	
	@Before
	public void setup() {
		dao = new JDBCParkDao(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void get_park_by_code() {
		String truncateParkSql = "TRUNCATE park CASCADE";
		jdbcTemplate.update(truncateParkSql);
		jdbcTemplate.update(INSERT_NEW_PARK);
		
		Park park = dao.getByCode("NP");
		
		Assert.assertNotNull(park);
		Assert.assertEquals("NP", park.getCode());
		
	}
	
	@Test 
	public void get_all_parks() {
		String truncateParkSql = "TRUNCATE park CASCADE";
		jdbcTemplate.update(truncateParkSql);
		jdbcTemplate.update(INSERT_NEW_PARK);
		
		List<Park> park = dao.getAll();
		
		Assert.assertNotNull(park);
		Assert.assertEquals(1, park.size());
	}
	
	@Test
	public void get_parks_with_surveys() {
		String truncateParkSql = "TRUNCATE park CASCADE";
		jdbcTemplate.update(truncateParkSql);
		jdbcTemplate.update(INSERT_NEW_PARK);
		jdbcTemplate.update(INSERT_NEW_SURVEY);
		
		List<Park> park = dao.getAllParksWithSurveys();
		
		Assert.assertNotNull(park);
		Assert.assertEquals(1, park.size());
		
	}
}
