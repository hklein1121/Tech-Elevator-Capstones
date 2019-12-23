package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrganizationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6620225541822687609L;
	
	private int organizationId;
	
	public OrganizationNotFoundException (int organizationId, String message) {
		super(message);
		setOrganizationId(organizationId);
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
}