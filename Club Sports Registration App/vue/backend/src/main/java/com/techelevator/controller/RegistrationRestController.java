package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.exception.LeagueNotFoundException;
import com.techelevator.exception.OrganizationNotFoundException;
import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.exception.UserNotFoundException;
import com.techelevator.model.availability.Availability;
import com.techelevator.model.availability.AvailabilityDAO;
import com.techelevator.model.league.League;
import com.techelevator.model.league.LeagueDAO;
import com.techelevator.model.organization.Organization;
import com.techelevator.model.organization.OrganizationDAO;
import com.techelevator.model.team.Team;
import com.techelevator.model.team.TeamDAO;
import com.techelevator.model.teamuser.TeamUser;
import com.techelevator.model.teamuser.TeamUserDAO;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDao;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/clubsports")
@CrossOrigin
public class RegistrationRestController {
	
	@Autowired
    private OrganizationDAO organizationDAO;
	@Autowired
	private LeagueDAO leagueDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private UserDao userDao;
	@Autowired 
	private AvailabilityDAO availabilityDAO;
	@Autowired 
	private TeamUserDAO teamUserDAO;
	
	@GetMapping("/user")
	public List<User> listAllUsers() {
		return userDao.getAllUsers();
	}
	

	@GetMapping("/user/assign")
	public List<Team> listAllTeams() {
		return teamDAO.listAllTeams();
	}
	
	@GetMapping("/user/{id}")
	public List<TeamUser> listAllTeamsByUserId(@PathVariable int id) {
		return teamUserDAO.listAllTeamsByUserId(id);
	}

	
	@PostMapping("/user/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignUserToTeam(@RequestBody TeamUser user) {
        teamUserDAO.updateUserByUserId(user);
    }
	
	
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userDao.saveUser(user);
    }
	
	@GetMapping("/organization")
	public List<Organization> listAllOrganizations() {
		return organizationDAO.listAllOrganizations();
	}
	
//	@PutMapping("user/{id}")
//	public User updateUsersTeamId(@RequestBody User user, @PathVariable int id) throws UserNotFoundException {
//		User requestedUser = userDao.getUserByUserId(id);
//		if (requestedUser != null) {
//			return userDao.updateUserByUserId(user);
//		} else {
//			throw new UserNotFoundException(id, "User Not Found");
//		}
//	}
	
	@GetMapping("/organization/{id}")
	public Organization getOrganizationById(@PathVariable int id) throws OrganizationNotFoundException {
		Organization organization = organizationDAO.getOrganizationById(id);
		if (organization != null) {
			return organization;
		} else {
			throw new OrganizationNotFoundException(id, "Organization Not Found");
		}
	}
    
    @PostMapping("/organization")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrganization(@RequestBody Organization organization) {
        organizationDAO.createOrganization(organization);
    }
    
    @PutMapping("/organization/{id}")
    public Organization updateOrganization(@RequestBody Organization organization, @PathVariable int id) throws OrganizationNotFoundException {
    	Organization requestedOrganization = organizationDAO.getOrganizationById(id);
    	if (requestedOrganization != null) {
    		return organizationDAO.updateOrganizationById(organization);
    	} else {
    		throw new OrganizationNotFoundException(id, "Organization Not Found");
    	}
    }
    
    @DeleteMapping("/organization/{id}")
    public void deleteOrganization(@PathVariable int id) throws OrganizationNotFoundException {
    	if (organizationDAO.getOrganizationById(id) != null) {
    		organizationDAO.deleteOrganizationById(id);
    	} else {
    		throw new OrganizationNotFoundException(id, "Organization Not Found");
    	}
    }
    
	@GetMapping("/league/{id}")
	public List<League> listAllLeaguesByOrganizationId(@PathVariable int id) {
		return leagueDAO.listAllLeaguesByOrganizationId(id);
	}
	
	@GetMapping("/league/{orgId}/{id}")
	public League getLeagueById(@PathVariable int id, @PathVariable int orgId) throws OrganizationNotFoundException {
		League league = leagueDAO.getLeagueById(id);
		if (league != null) {
			return league;
		} else {
			throw new OrganizationNotFoundException(id, "Organization Not Found");
		}
	}
	
	
    @PostMapping("/league")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLeague(@RequestBody League league) {
    	leagueDAO.createLeague(league);
    }
    
    @PutMapping("/league/{id}")
    public League updateLeague(@RequestBody League league, @PathVariable int id) throws LeagueNotFoundException {
    	League requestedLeague = leagueDAO.getLeagueById(id);
    	if (requestedLeague != null) {
    		return leagueDAO.updateLeagueById(league);
    	} else {
    		throw new LeagueNotFoundException(id, "League Not Found");
    	}
    }
    
    @DeleteMapping("/league/{id}")
    public void deleteLeague(@PathVariable int id) throws LeagueNotFoundException {
    	if (leagueDAO.getLeagueById(id) != null) {
    		leagueDAO.deleteLeagueById(id);
    	} else {
    		throw new LeagueNotFoundException(id, "League Not Found");
    	}
    }
    
	@GetMapping("/team/{id}")
	public List<Team> listAllTeamsByLeagueId(@PathVariable int id) {
		return teamDAO.listAllTeamsByLeagueId(id);
	}
	
	@GetMapping("/team/{leagueId}/{id}")
	public Team getTeamById(@PathVariable int id, @PathVariable int leagueId) throws OrganizationNotFoundException {
		Team team = teamDAO.getTeamById(id);
		if (team != null) {
			return team;
		} else {
			throw new LeagueNotFoundException(id, "League Not Found");
		}
	}
    
    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody Team team) {
        teamDAO.createTeam(team);
    }
    
    @PutMapping("/team/{id}")
    public Team updateTeam(@RequestBody Team team, @PathVariable int id) throws TeamNotFoundException {
    	Team requestedTeam = teamDAO.getTeamById(id);
    	if (requestedTeam != null) {
    		return teamDAO.updateTeam(team);
    	} else {
    		throw new TeamNotFoundException(id, "Team Not Found");
    	}
    }
    
    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable int id) throws TeamNotFoundException {
    	if (teamDAO.getTeamById(id) != null) {
    		teamDAO.deleteTeam(id);
    	} else {
    		throw new TeamNotFoundException(id, "Team Not Found");
    	}
    }
    
    @GetMapping("/availability/{id}")
	public List<Availability> listAvailabilityByTeamId(@PathVariable int id) {
		return availabilityDAO.listAvailabilityByTeamId(id);
	}
    
    @GetMapping("/availability/{teamId}/{id}")
	public Availability getAvailabilityById(@PathVariable int id, @PathVariable int teamId) throws TeamNotFoundException {
		Availability availability = availabilityDAO.getAvailabilityById(id);
		if (availability != null) {
			return availability;
		} else {
			throw new LeagueNotFoundException(id, "Availability Not Found");
		}
	}
    
    @PostMapping("/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAvailability(@RequestBody Availability availability) {
        availabilityDAO.createAvailability(availability);
    }
    
    @PutMapping("/availability/{id}")
    public Availability updateAvailability(@RequestBody Availability availability, @PathVariable int id) throws TeamNotFoundException {
    	Availability requestedAvailability = availabilityDAO.getAvailabilityById(id);
    	if (requestedAvailability != null) {
    		return availabilityDAO.updateAvailability(availability);
    	} else {
    		throw new TeamNotFoundException(id, "Availability Not Found");
    	}
    }
    
    @DeleteMapping("/availability/{id}")
    public void deleteAvailability(@PathVariable int id) throws TeamNotFoundException {
    	if (availabilityDAO.getAvailabilityById(id) != null) {
    		availabilityDAO.deleteAvailability(id);
    	} else {
    		throw new TeamNotFoundException(id, "Availability Not Found");
    	}
    }
}