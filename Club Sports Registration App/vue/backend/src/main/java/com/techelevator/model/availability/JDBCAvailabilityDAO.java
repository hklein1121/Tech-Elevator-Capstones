package com.techelevator.model.availability;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;



@Component
public class JDBCAvailabilityDAO implements AvailabilityDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

	
	@Autowired
	public JDBCAvailabilityDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Availability> listAvailabilityByTeamId(int id) {
		ArrayList<Availability> availabilities = new ArrayList<>();
		
		String SelectAllSql = "SELECT * FROM availability WHERE team_id = ?  ORDER BY date, earliest_start, latest_start ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(SelectAllSql, id);
		while (results.next()) {
			Availability theAvailability = mapRowToAvailability(results);
			availabilities.add(theAvailability);
		}
		return availabilities;
	}

	@Override
	public Availability getAvailabilityById(int id) {
		
		Availability availability = null;
		String selectSql = "SELECT * FROM availability WHERE availability_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id);
		results.next();
		
		availability = mapRowToAvailability(results);
		
		return availability;
	}

	@Override
	public void createAvailability(Availability availability) {
		
		String insertSql = "INSERT INTO availability (team_id, home, away, date, earliest_start, latest_start) VALUES (?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(insertSql, availability.getTeamId(), availability.isHome(), availability.isAway(), availability.getDate(), availability.getEarliestStart(), availability.getLatestStart());
	}

	@Override
	public Availability updateAvailability(Availability availability) {
		
		String updateSql = "UPDATE availability SET home = ?, away = ?, date = ?, earliest_start = ?, latest_start = ? WHERE availability_id = ?;";
		jdbcTemplate.update(updateSql, availability.isHome(), availability.isAway(), availability.getDate(), availability.getEarliestStart(), availability.getLatestStart(), availability.getAvailabilityId());
		return availability;
	}

	@Override
	public void deleteAvailability(int id) {
		
		String deleteSql = "DELETE FROM availability WHERE availability_id = ?;";
		jdbcTemplate.update(deleteSql, id);
	}
	
	private Availability mapRowToAvailability(SqlRowSet row) {
		Availability availability = new Availability();
		availability.setAvailabilityId(row.getInt("availability_id"));
		availability.setTeamId(row.getInt("team_id"));
		availability.setHome(row.getBoolean("home"));
		availability.setAway(row.getBoolean("away"));
		availability.setDate(row.getString("date"));
		availability.setEarliestStart(row.getString("earliest_start"));
		availability.setLatestStart(row.getString("latest_start"));
		return availability;
	}
}