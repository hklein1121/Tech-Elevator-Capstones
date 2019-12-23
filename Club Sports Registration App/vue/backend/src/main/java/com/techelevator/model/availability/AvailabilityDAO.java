package com.techelevator.model.availability;

import java.util.List;

public interface AvailabilityDAO {

	public List<Availability> listAvailabilityByTeamId(int id);
	public Availability getAvailabilityById(int id);
	public void createAvailability(Availability availability);
	public Availability updateAvailability(Availability availability);
	public void deleteAvailability(int id);
}
