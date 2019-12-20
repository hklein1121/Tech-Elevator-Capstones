package com.techelevator.npgeek.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JDBCParkDao implements ParkDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDao(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public Park getByCode(String code) {
		String selectSql = "SELECT park.parkcode AS code, park.parkname AS parkname, park.state AS state, park.acreage AS acreage, park.elevationinfeet AS elevationinfeet, park.milesoftrail AS milesoftrail, park.numberofcampsites AS numberofcampsites, park.climate AS climate, park.yearfounded AS yearfounded, park.annualvisitorcount AS annualvisitorcount, park.inspirationalquote AS inspirationalquote, park.inspirationalquotesource AS inspirationalquotesource, park.parkdescription AS description, park.entryfee AS entryfee, park.numberofanimalspecies AS numberofanimalspecies " +
				"FROM park " +
				"WHERE park.parkcode = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, code);
		
		Park park = null;
		while(rows.next()) {
			park = mapRowToParkDetails(rows);
		}
		
		return park;
	}

	@Override
	public List<Park> getAll() {
		List<Park> parks = new ArrayList<Park>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT parkcode, parkname, parkdescription FROM park ORDER BY parkname ASC");
		while(rows.next()) {
			Park park = mapRowToPark(rows);
			parks.add(park);
		}
		
		return parks;
	}
	
	@Override
	public List<Park> getAllParksWithSurveys() {
		List<Park> parks = new ArrayList<Park>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT parkname, COUNT(park.parkcode) AS surveycount, park.parkcode FROM park " +
				"JOIN survey_result ON park.parkcode = survey_result.parkcode " +
				"GROUP BY parkname, park.parkcode " +
				"ORDER BY surveycount DESC");
		
		while (rows.next()) {
			Park park = mapRowToParkWithSurveys(rows);
			parks.add(park);
		}
		
		
		return parks;
	}

	
	
	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		
		park.setCode(row.getString("parkcode"));
		park.setName(row.getString("parkname"));
		park.setDescription(row.getString("parkdescription"));
		
		return park;
	}
	
	private Park mapRowToParkDetails(SqlRowSet row) {
		Park park = new Park();
		
		park.setCode(row.getString("code"));
		park.setName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitors(row.getInt("annualvisitorcount"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfSpecies(row.getInt("numberofanimalspecies"));
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		park.setDescription(row.getString("description"));
		
		return park;
	}
	
	private Park mapRowToParkWithSurveys(SqlRowSet row) {
		Park park = new Park();
		
		park.setName(row.getString("parkname"));
		park.setCode(row.getString("parkcode"));
		park.setSurveyCount(row.getInt("surveycount"));
		
		return park;
		
	}
	
	
}
