package com.techelevator.model.organization;

import java.util.List;

public interface OrganizationDAO {
	
	public List<Organization> listAllOrganizations();
	public Organization getOrganizationById(int id);
	public void createOrganization(Organization organization);
	public Organization updateOrganizationById(Organization organization);
	public void deleteOrganizationById(int id);
}
