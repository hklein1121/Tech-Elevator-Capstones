package com.techelevator.venue;

import java.util.List;

public interface VenueDAO {

	List<Venue> getAllVenues();

	Venue selectVenueById(long id);

}
