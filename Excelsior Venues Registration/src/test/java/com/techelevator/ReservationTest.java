package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.reservation.JDBCReservationDAO;
import com.techelevator.reservation.Reservation;
import com.techelevator.reservation.ReservationDAO;


public class ReservationTest {
	
	private static SingleConnectionDataSource dataSource;
	private ReservationDAO dao;
	private JdbcTemplate jdbcTemplate;

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
		dao = new JDBCReservationDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void create_new_reservation() throws SQLException {
		Reservation theReservation = getNewReservation();

		dao.createNewReservation(theReservation);
		Reservation savedReservation = dao.getReservationById(theReservation.getReservationId());

		assertNotEquals(null, theReservation.getReservationId());
		assertReservationsAreEqual(theReservation, savedReservation);
	}
	
	
	private void assertReservationsAreEqual(Reservation expected, Reservation actual) {
		assertEquals(expected.getReservationId(), actual.getReservationId());
		assertEquals(expected.getSpaceId(), actual.getSpaceId());
		assertEquals(expected.getNumberOfAttendees(), actual.getNumberOfAttendees());
		assertEquals(expected.getStartDate(), actual.getStartDate());
		assertEquals(expected.getEndDate(), actual.getEndDate());
		assertEquals(expected.getReservedFor(), actual.getReservedFor());
	}
	
	private Reservation getNewReservation() {
		Reservation reservation = new Reservation();
		reservation.setSpaceId(1);
		reservation.setNumberOfAttendees(12);
		reservation.setStartDate(LocalDate.parse("2019-01-01"));
		reservation.setEndDate(LocalDate.parse("2019-01-02"));
		reservation.setReservedFor("TestName");
		return reservation;
	}

}

