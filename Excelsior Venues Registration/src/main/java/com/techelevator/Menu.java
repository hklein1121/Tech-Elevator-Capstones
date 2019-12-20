package com.techelevator;

import java.util.List;
import java.util.Scanner;

import com.techelevator.venue.Venue;
import com.techelevator.reservation.Reservation;
import com.techelevator.space.Space;
import java.text.DateFormatSymbols;
import java.time.temporal.ChronoUnit;

public class Menu {

	public static final String MAIN_MENU_DISPLAY_VENUES = "1";
	public static final String VENUE_DETAILS_MENU_VIEW_SPACES = "1";
	public static final String RESERVE_A_SPACE_OPTION = "1";
	public static final String MENU_QUIT_OPTION = "Q";

	private final Scanner in = new Scanner(System.in);

	public String mainMenu() {
		System.out.println("What would you like to do?");
		System.out.println(MAIN_MENU_DISPLAY_VENUES + ") List Venue");
		System.out.println(MENU_QUIT_OPTION + ") Quit");

		return in.nextLine();
	}

	public String allVenuesMenu(List<Venue> venues) {

		System.out.println("Which venue would you like to view?");
		listVenues(venues);
		System.out.println("R) Return to Previous Screen");

		return in.nextLine();
	}

	public String venueDetailsMenu(Venue venue) {
		System.out.println(venue.getName());
		System.out.println("Location: " + venue.getCity_name() + ", " + venue.getState_abbreviation());
		System.out.println("Categories: " + venue.getCategory_name());
		System.out.println();
		System.out.println(venue.getDescription());
		System.out.println();
		System.out.println("What would you like to do next?");
		System.out.println(VENUE_DETAILS_MENU_VIEW_SPACES + ") View Spaces");
		System.out.println("R) Return to Previous Screen");

		return in.nextLine();
	}

	public String spaceDetailsMenu(List<Space> spaces) {
		System.out.println(spaces.get(0).getVenueName() + "Spaces");
		System.out.println();
		System.out.printf("%-5s %-25s %-10s %-10s %-20s %-10s", "", "Name", "Open", "Close", "Daily Rate",
				"Max. Occupancy");
		System.out.println();
		listSpaces(spaces);
		System.out.println();
		System.out.println("What would you like to do next?");
		System.out.println("     1) Reserve a Space");
		System.out.println("     R) Return to Previous Screen");

		return in.nextLine();
	}

	public String reservationStartDate() {
		System.out.println("When do you need the space?");

		return in.nextLine();
	}

	public String numberOfReservationDays() {
		System.out.println("How many days will you need the space?");

		return in.nextLine();
	}

	public String numberOfAttendees() {
		System.out.println("How many people will be in attendance?");

		return in.nextLine();
	}

	public String numberOfSpaceBeingReserved(List<Space> spaces, int numberOfDays) {
		System.out.println("The following spaces are available based on your needs:");
		System.out.println();
		System.out.println();
		System.out.printf("%-10s %-30s %-15s %-15s %-15s %-10s%n", "Space #", "Name", "Daily Rate", "Max Occup.",
				"Accessible?", "Total Cost");
		listOfSpacesAvailable(spaces, numberOfDays);
		System.out.println();
		System.out.println("Which space would you like to reserve (enter 0 to cancel)?");

		return in.nextLine();
	}

	public String whoIsThisReservationFor() {
		System.out.println("Who is this reservation for?");

		return in.nextLine();
	}

	public void displayConfirmationMenu(Reservation reservation) {
		long daysBetween = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());

		System.out.println("Thanks for submitting your reservation! The details for your event are listed below:");
		System.out.println();

		System.out.println("Confirmation #:  " + reservation.getReservationId());
		System.out.println("Venue:  " + reservation.getVenueName());
		System.out.println("Space:  " + reservation.getSpaceName());
		System.out.println("Reserved For:  " + reservation.getReservedFor());
		System.out.println("Attendees:  " + reservation.getNumberOfAttendees());
		System.out.println("Arrival Date:  " + reservation.getStartDate());
		System.out.println("Depart Date:  " + reservation.getEndDate());
		System.out.println("Total Cost:  $" + (reservation.getDailyRate().intValue() * daysBetween));
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public void displayUserMessage(String message) {
		System.out.println(message);
	}

	private void listVenues(List<Venue> venues) {
		System.out.println();
		if (venues.size() > 0) {
			for (Venue ven : venues) {
				System.out.println(ven.getId() + ") " + ven.getName());
			}
		}
	}

	private void listSpaces(List<Space> spaces) {
		for (Space space : spaces) {
			System.out.printf("%-5s %-25s %-10s %-10s %-20s %-10s%n", "#" + space.getId(), space.getName(),
					getMonth(space.getOpenFrom()), getMonth(space.getOpenTo()), "$" + space.getDailyRate(),
					space.getMaxOccupancy());
		}
	}

	private String getMonth(long month) {
		if (month == 0) {
			return "";
		} else
			return new DateFormatSymbols().getMonths()[(int) (month - 1)];
	}

	private void listOfSpacesAvailable(List<Space> spaces, int numberOfDays) {
		for (Space space : spaces) {
			System.out.printf("%-10s %-30s %-15s %-15s %-15s %-10s%n", space.getId(), space.getName(),
					"$" + space.getDailyRate(), space.getMaxOccupancy(), space.isAccessible(),
					"$" + (space.getDailyRate().intValue() * numberOfDays));
		}
	}

}
