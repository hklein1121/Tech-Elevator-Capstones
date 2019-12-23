package com.techelevator.model.teamuser;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.team.Team;



@Component
public class JDBCTeamUserDAO implements TeamUserDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCTeamUserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
    public void updateUserByUserId(TeamUser teamUser) {
    	
		String updateSql = "INSERT INTO team_users (team_id, user_id) VALUES (?, ?);";
		jdbcTemplate.update(updateSql, teamUser.getTeamId(), teamUser.getUserId());
    }
	
	@Override
	public List<TeamUser> listAllTeamsByUserId(int id) {
		
		ArrayList<TeamUser> teams = new ArrayList<>();
		
		String SelectAllSql = "SELECT team_users.team_id AS team_id, team_users.user_id AS user_id, team.name FROM team LEFT JOIN team_users ON team.team_id = team_users.team_id WHERE team_users.user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(SelectAllSql, id);
		while (results.next()) {
			TeamUser theTeam = mapRowToTeamList(results);
			teams.add(theTeam);
		}
		return teams;
	}
	
	private TeamUser mapRowToTeamList(SqlRowSet row) {
		TeamUser teamUser = new TeamUser();
		teamUser.setTeamId(row.getInt("team_id"));
		teamUser.setUserId(row.getInt("user_id"));
		teamUser.setName(row.getString("team.name"));
		return teamUser;
	}

}
