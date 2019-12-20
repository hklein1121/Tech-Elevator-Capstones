package com.techelevator.space;

import java.time.LocalDate;
import java.util.List;

public interface SpaceDAO {

	List<Space> getAllSpacesByVenueId(long id);

	List<Space> getAvailableSpaces(LocalDate userStartDate, int numberOfDays, int numberOfAttendees, long venueId);

}
