package com.techelevator.reservation;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Reservation createNewReservation(Reservation reservation) {
		String insertSql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, reservation.getSpaceId(),
				reservation.getNumberOfAttendees(), reservation.getStartDate(), reservation.getEndDate(),
				reservation.getReservedFor());
		rows.next();
		long reservationId = rows.getLong("reservation_id");

		reservation.setReservationId(reservationId);
		return reservation;
	}

	@Override
	public Reservation getReservationById(long id) {
		String selectSql = "SELECT reservation.reservation_id AS reservation_id, reservation.space_id AS space_id, reservation.number_of_attendees AS number_of_attendees, reservation.start_date AS start_date, reservation.end_date AS end_date, reservation.reserved_for AS reserved_for, venue.name AS venue_name, space.name AS space_name, CAST (space.daily_rate AS numeric) AS daily_rate"
				+ " FROM reservation" + " JOIN space ON reservation.space_id = space.id"
				+ " JOIN venue ON space.venue_id = venue.id" + " WHERE reservation_id = ?";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, id);

		Reservation reservation = null;
		if (rows.next()) {
			reservation = mapRowToReservation(rows);
		}

		return reservation;
	}

	private Reservation mapRowToReservation(SqlRowSet row) {
		Reservation reservation = new Reservation();

		reservation.setReservationId(row.getLong("reservation_id"));
		reservation.setSpaceId(row.getLong("space_id"));
		reservation.setNumberOfAttendees(row.getLong("number_of_attendees"));
		if (row.getDate("start_date") != null) {
			reservation.setStartDate(row.getDate("start_date").toLocalDate());
		}
		if (row.getDate("end_date") != null) {
			reservation.setEndDate(row.getDate("end_date").toLocalDate());
		}
		reservation.setReservedFor(row.getString("reserved_for"));
		reservation.setVenueName(row.getString("venue_name"));
		reservation.setSpaceName(row.getString("space_name"));
		reservation.setDailyRate(row.getBigDecimal("daily_rate"));
		return reservation;
	}

}
