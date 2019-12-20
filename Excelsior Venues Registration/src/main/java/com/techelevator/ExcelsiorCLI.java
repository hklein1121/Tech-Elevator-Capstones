package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.reservation.JDBCReservationDAO;
import com.techelevator.reservation.Reservation;
import com.techelevator.reservation.ReservationDAO;
import com.techelevator.space.JDBCSpaceDAO;
import com.techelevator.space.Space;
import com.techelevator.space.SpaceDAO;
import com.techelevator.venue.JDBCVenueDAO;
import com.techelevator.venue.Venue;
import com.techelevator.venue.VenueDAO;

public class ExcelsiorCLI {

	private Menu menu;
	private ReservationDAO reservationDAO;
	private SpaceDAO spaceDAO;
	private VenueDAO venueDAO;
	private long userSelectedVenue;
	private LocalDate startDate;
	private int numberOfDaysToReserve;
	private int numberOfAttendees;
	private int spaceToReserve;
	private String reservationName;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();

	}

	public ExcelsiorCLI(DataSource datasource) {
		this.menu = new Menu();

		reservationDAO = new JDBCReservationDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		venueDAO = new JDBCVenueDAO(datasource);
	}

	public void run() {
		runMenu();
	}

	private void runMenu() {
		while (true) {
			String choice = menu.mainMenu();

			if (choice.equals(Menu.MAIN_MENU_DISPLAY_VENUES)) {
				showAllVenuesMenu();
			} else if (choice.toLowerCase().equals("q")) {
				break;
			} else {
				menu.displayUserMessage("Invalid input, please try again\n");
			}
		}
	}

	private void showAllVenuesMenu() {
		while (true) {
			List<Venue> venues = venueDAO.getAllVenues();
			String choice = menu.allVenuesMenu(venues);

			if (choice.toLowerCase().equals("r")) {
				break;
			}
			try {
				Long.parseLong(choice);

			} catch (NumberFormatException e) {
				menu.displayUserMessage("Invalid input, please try again\n");
				continue;
			}

			userSelectedVenue = Long.parseLong(choice);
			if (userSelectedVenue > 0 && userSelectedVenue <= venues.size()) {
				listVenueDetails(userSelectedVenue);
			} else {
				menu.displayUserMessage("Invalid venue id, please try again\n");
			}
		}
	}

	private void listVenueDetails(Venue listedVenue) {
		while (true) {
			Venue venue = venueDAO.selectVenueById(userSelectedVenue);
			String choice = menu.venueDetailsMenu(venue);

			if (choice.equals(Menu.VENUE_DETAILS_MENU_VIEW_SPACES)) {
				listVenueSpaces();
			} else if (choice.toLowerCase().equals("r")) {
				break;
			} else {
				menu.displayUserMessage("Invalid input, please try again\n");
			}
		}
	}

	private void listVenueSpaces() {
		while (true) {
			List<Space> spaces = spaceDAO.getAllSpacesByVenueId(userSelectedVenue);
			String choice = menu.spaceDetailsMenu(spaces);

			if (choice.equals(Menu.RESERVE_A_SPACE_OPTION)) {
				reserveSpace();
			} else if (choice.toLowerCase().equals("r")) {
				break;
			} else {
				menu.displayUserMessage("Invalid input, please try again\n");
			}
		}
	}

	private void reserveSpace() {
		while (true) {
			String startDateChoice = menu.reservationStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			try {
				startDate = LocalDate.parse(startDateChoice, formatter);
			} catch (Exception e) {
				menu.displayUserMessage("Invalid date format, please use MM/dd/yyyy\n");
				continue;
			}
			String numberOfDays = menu.numberOfReservationDays();
			try {
				numberOfDaysToReserve = Integer.parseInt(numberOfDays);
			} catch (NumberFormatException e) {
				menu.displayUserMessage("Invalid input, please try again\n");
				continue;
			}
			if (numberOfDaysToReserve <= 0) {
				menu.displayUserMessage("Invalid number of days, please try again\n");
				continue;
			} else {
				String numberOfAttendeesAsString = menu.numberOfAttendees();
				try {
					numberOfAttendees = Integer.parseInt(numberOfAttendeesAsString);
				} catch (NumberFormatException e) {
					menu.displayUserMessage("Invalid input, please try again\n");
					continue;
				}
				if (numberOfAttendees <= 0) {
					menu.displayUserMessage("Invalid number of attendees, please try again\n");
					continue;
				} else {
					availableSpaces();
				}
			}
		}
	}

	private void availableSpaces() {
		while (true) {
			List<Space> spaces = spaceDAO.getAvailableSpaces(startDate, numberOfDaysToReserve, numberOfAttendees,
					userSelectedVenue);
			String choice = menu.numberOfSpaceBeingReserved(spaces, numberOfDaysToReserve);
			try {
				spaceToReserve = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				menu.displayUserMessage("Invalid input, please try again\n");
				continue;
			}
			if (spaceToReserve == 0) {
				break;
			} else if (checkForValidSpaceNumber(spaces, spaceToReserve)) {
				getReservationName();
			} else {
				menu.displayUserMessage("Invalid input, please try again\n");
				continue;
			}
		}
	}

	private void getReservationName() {
		while (true) {
			reservationName = menu.whoIsThisReservationFor();
			addToReservationTable();
		}
	}

	private void addToReservationTable() {
		Reservation reservation = new Reservation();
		reservation.setSpaceId(spaceToReserve);
		reservation.setNumberOfAttendees(numberOfAttendees);
		reservation.setStartDate(startDate);
		reservation.setEndDate(startDate.plusDays(numberOfDaysToReserve));
		reservation.setReservedFor(reservationName);

		reservation = reservationDAO.createNewReservation(reservation);
		reservation = reservationDAO.getReservationById(reservation.getReservationId());
		menu.displayConfirmationMenu(reservation);
		run();
	}

	private void listVenueDetails(long id) {
		Venue venue = venueDAO.selectVenueById(id);
		listVenueDetails(venue);
	}

	private boolean checkForValidSpaceNumber(List<Space> spaces, int spaceNumber) {
		for (Space space : spaces) {
			if (space.getId() == spaceNumber) {
				return true;
			}
		}
		return false;
	}

}
