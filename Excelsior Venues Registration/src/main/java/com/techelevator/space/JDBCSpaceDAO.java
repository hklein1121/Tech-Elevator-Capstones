package com.techelevator.space;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCSpaceDAO implements SpaceDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSpaceDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Space> getAllSpacesByVenueId(long venueId) {
		List<Space> spaces = new ArrayList<Space>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet(
				"SELECT space.id AS space_id, space.venue_id AS venue_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, CAST (space.daily_rate AS numeric) AS daily_rate, space.max_occupancy AS max_occupancy, venue.name AS venue_name FROM space JOIN venue ON space.venue_id = venue.id WHERE venue.id = ?",
				venueId);

		while (rows.next()) {
			Space space = mapRowToSpace(rows);
			spaces.add(space);
		}
		return spaces;
	}

	@Override
	public List<Space> getAvailableSpaces(LocalDate userStartDate, int numberOfDays, int numberOfAttendees,
			long venueId) {
		LocalDate userEndDate = userStartDate.plusDays(numberOfDays);
		List<Space> spaces = new ArrayList<Space>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet(
				"SELECT space.id AS space_id, space.venue_id AS venue_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, CAST (space.daily_rate AS numeric) AS daily_rate, space.max_occupancy AS max_occupancy, venue.name AS venue_name FROM space"
						+ " JOIN venue on space.venue_id = venue.id"
						+ " WHERE (space.max_occupancy > ?) AND (space.venue_id = ?) AND"
						+ " ((space.open_from IS NULL AND space.open_to IS NULL) OR" + " (space.id NOT IN"
						+ " (SELECT space_id FROM reservation"
						+ " WHERE (? > start_date AND ? < end_date) OR (? > start_date AND ? < end_date)"
						+ " GROUP BY space_id)))",
				numberOfAttendees, venueId, userStartDate, userStartDate, userEndDate, userEndDate);

		while (rows.next()) {
			Space space = mapRowToSpace(rows);
			spaces.add(space);
		}
		return spaces;
	}

	private Space mapRowToSpace(SqlRowSet row) {
		Space space = new Space();

		space.setId(row.getLong("space_id"));
		space.setVenueId(row.getLong("venue_id"));
		space.setName(row.getString("space_name"));
		space.setAccessible(row.getBoolean("is_accessible"));
		space.setOpenFrom(row.getLong("open_from"));
		space.setOpenTo(row.getLong("open_to"));
		space.setDailyRate(row.getBigDecimal("daily_rate"));
		space.setMaxOccupancy(row.getLong("max_occupancy"));
		space.setVenueName(row.getString("venue_name"));

		return space;
	}

}
