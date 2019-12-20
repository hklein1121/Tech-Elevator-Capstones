package com.techelevator.npgeek.park;

import java.util.List;

public interface ParkDao {

	Park getByCode(String code);
	
	List<Park> getAll();
	
	List<Park> getAllParksWithSurveys();
	
}
