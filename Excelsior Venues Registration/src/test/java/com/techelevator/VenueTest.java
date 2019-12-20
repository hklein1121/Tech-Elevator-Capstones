package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.venue.JDBCVenueDAO;
import com.techelevator.venue.Venue;
import com.techelevator.venue.VenueDAO;



public class VenueTest {

	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private VenueDAO dao;
	

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void destroyDataSource() {
		dataSource.destroy();
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Before
	public void setup() {
		dao = new JDBCVenueDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void get_all_venues() {
		String sql = "SELECT COUNT(*) AS numberOfRows FROM venue";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		rows.next();
		int originalRowCount = rows.getInt("numberOfRows");

		Venue venueOne = getNewVenue();
		Venue venueTwo = getNewVenue();
		
		save(venueOne);
		save(venueTwo);
		
		List<Venue> venuesReturned = dao.getAllVenues();
		
		Assert.assertNotNull(venuesReturned);
		Assert.assertEquals(originalRowCount + 2, venuesReturned.size());
	}
	
	
	@Test
	public void search_venue_by_id() {
		Venue theVenue = getNewVenue();
		
		save(theVenue);
		
		Venue results = dao.selectVenueById(theVenue.getId());
		
		assertNotNull(results);
		assertVenuesAreEqual(theVenue, results);
	}
	
	
	
	
	
	
	
	
	private Venue getNewVenue() {
		Venue venue = new Venue();
		venue.setName("TestName");
		venue.setCity_id(1L);
		venue.setDescription("Test description");
		
		return venue;
	}
	
	private void save(Venue venue) {
		String insertSql = "INSERT INTO venue (id, name, city_id, description) VALUES (DEFAULT, ?, ?, ?) RETURNING id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, venue.getName(), venue.getCity_id(), venue.getDescription() );
		rows.next();
		long id = rows.getLong("id");
		venue.setId(id);
	}

	
	private void assertVenuesAreEqual(Venue expected, Venue actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getCity_id(), actual.getCity_id());
		assertEquals(expected.getDescription(), actual.getDescription());
	
	}
	
	
}
