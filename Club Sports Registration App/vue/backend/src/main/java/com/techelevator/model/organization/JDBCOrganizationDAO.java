package com.techelevator.model.organization;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCOrganizationDAO implements OrganizationDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCOrganizationDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Organization> listAllOrganizations() {
		
		ArrayList<Organization> organizations = new ArrayList<>();
		
		String selectAllSql = "SELECT * FROM organization ORDER BY name ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectAllSql);
		while (results.next()) {
			Organization theOrganization = mapRowToOrganization(results);
			organizations.add(theOrganization);
		}
		return organizations;
	}
	
	@Override
	public Organization getOrganizationById(int id) {

		Organization organization = null;
		String selectSql = "SELECT * FROM organization WHERE organization_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id);
		results.next();
		
		organization = mapRowToOrganization(results);
		
		return organization;
	}
	
	@Override
	public void createOrganization(Organization organization) {
		
		String insertSql = "INSERT INTO organization (name) VALUES (?);";
		jdbcTemplate.update(insertSql, organization.getName());
	}

	@Override
	public Organization updateOrganizationById(Organization organization) {
		
		String updateSql = "UPDATE organization SET name = ? WHERE organization_id = ?;";
		jdbcTemplate.update(updateSql, organization.getName(), organization.getOrganizationId());
		return organization;
	}

	@Override
	public void deleteOrganizationById(int id) {
		
		String deleteSql = "DELETE FROM organization WHERE organization_id = ?;";
		jdbcTemplate.update(deleteSql, id);
	}
	
	private Organization mapRowToOrganization(SqlRowSet row) {
		Organization organization = new Organization();
		organization.setOrganizationId(row.getInt("organization_id"));
		organization.setName(row.getString("name"));
		return organization;
	}
}