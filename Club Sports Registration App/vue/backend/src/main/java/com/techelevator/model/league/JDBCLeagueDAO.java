package com.techelevator.model.league;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCLeagueDAO implements LeagueDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCLeagueDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<League> listAllLeaguesByOrganizationId(int id) {
		
		ArrayList<League> leagues = new ArrayList<>();
		
		String selectAllSql = "SELECT * FROM league WHERE organization_id = ? ORDER BY name ASC ;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectAllSql, id);
		while (results.next()) {
			League theLeague = mapRowToLeague(results);
			leagues.add(theLeague);
		}
		return leagues;
	}

	@Override
	public League getLeagueById(int id) {
		
		League league = null;
		String selectSql = "SELECT * FROM league WHERE league_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, id);
		if (results.next()) {
			league = mapRowToLeague(results);			
		}
		return league;
	}

	@Override
	public void createLeague(League league) {
		
		String insertSql = "INSERT INTO league (name, organization_id, admin_id) VALUES (?, ?, 1);";
		jdbcTemplate.update(insertSql, league.getName(), league.getOrganizationId());
	}

	@Override
	public League updateLeagueById(League league) {
		
		String updateSql = "UPDATE league SET name = ? WHERE league_id = ?;";
		jdbcTemplate.update(updateSql, league.getName(), league.getLeagueId());
		return league;
	}

	@Override
	public void deleteLeagueById(int id) {
		
		String deleteSql = "DELETE FROM league WHERE league_id = ?;";
		jdbcTemplate.update(deleteSql, id);
	}

	private League mapRowToLeague(SqlRowSet row) {
		League league = new League();
		league.setLeagueId(row.getInt("league_id"));
		league.setName(row.getString("name"));
		league.setOrganizationId(row.getInt("organization_id"));
		league.setAdminId(row.getInt("admin_id"));
		return league;
	}
}