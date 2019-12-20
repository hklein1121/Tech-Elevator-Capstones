package com.techelevator.reservation;

public interface ReservationDAO {

	Reservation createNewReservation(Reservation reservation);

	Reservation getReservationById(long id);

}
