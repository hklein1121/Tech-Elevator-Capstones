UPDATE users SET role = 'admin' WHERE user_id = 1;

SELECT * FROM organization;
INSERT INTO organization (name) VALUES ('TestOrganization1');
INSERT INTO organization (name) VALUES ('TestOrganization2');
INSERT INTO organization (name) VALUES ('TestOrganization3');
INSERT INTO organization (name) VALUES ('TestOrganization4');

SELECT * FROM users;
INSERT INTO users (user_id, username, email, password, salt) VALUES (DEFAULT, 'testUsername1', 'testEmail', 'testPassword', 'testSalt');
INSERT INTO users (user_id, username, email, password, salt) VALUES (DEFAULT, 'testUsername2', 'testEmail', 'testPassword', 'testSalt');
INSERT INTO users (user_id, username, email, password, salt) VALUES (DEFAULT, 'testUsername3', 'testEmail', 'testPassword', 'testSalt');
INSERT INTO users (user_id, username, email, password, salt) VALUES (DEFAULT, 'testUsername4', 'testEmail', 'testPassword', 'testSalt');


SELECT * FROM league;
INSERT INTO league (name, organization_id, admin_id) VALUES ('testLeague1', 1, 1);
INSERT INTO league (name, organization_id, admin_id) VALUES ('testLeague2', 2, 2);
INSERT INTO league (name, organization_id, admin_id) VALUES ('testLeague3', 3, 3);
INSERT INTO league (name, organization_id, admin_id) VALUES ('testLeague4', 4, 4);


SELECT * FROM team;
INSERT INTO team (team_id, name, team_email, primary_venue, secondary_venue, league_id) VALUES (DEFAULT, 'testTeam1', 'testEmail', 'testVenue1', 'testVenue2', 1);
INSERT INTO team (team_id, name, team_email, primary_venue, secondary_venue, league_id) VALUES (DEFAULT, 'testTeam2', 'testEmail', 'testVenue1', 'testVenue2', 1);
INSERT INTO team (team_id, name, team_email, primary_venue, secondary_venue, league_id) VALUES (DEFAULT, 'testTeam3', 'testEmail', 'testVenue1', 'testVenue2', 1);
INSERT INTO team (team_id, name, team_email, primary_venue, secondary_venue, league_id) VALUES (DEFAULT, 'testTeam4', 'testEmail', 'testVenue1', 'testVenue2', 1);
INSERT INTO team (team_id, name, team_email, primary_venue, secondary_venue, league_id) VALUES (DEFAULT, 'testTeam5', 'testEmail', 'testVenue1', 'testVenue2', 1);

INSERT INTO team (name, team_email, primary_venue, secondary_venue, league_id) VALUES (?, ?, ?, ?, 1);


SELECT * FROM availability;
INSERT INTO availability (availability_id, team_id, home, away, date, earliest_start, latest_start) VALUES (DEFAULT, 6, TRUE, TRUE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO availability (availability_id, team_id, home, away, date, earliest_start, latest_start) VALUES (DEFAULT, 6, TRUE, FALSE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO availability (availability_id, team_id, home, away, date, earliest_start, latest_start) VALUES (DEFAULT, 6, FALSE, TRUE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


SELECT * 
FROM league
JOIN users ON league.admin_id=users.user_id;

SELECT * 
FROM team
JOIN users ON team.team_id=users.user_id;

SELECT * FROM team_users;
INSERT INTO team_users (team_id, user_id) VALUES (5, 2);
INSERT INTO team_users (team_id, user_id) VALUES (3, 4);
INSERT INTO team_users (team_id, user_id) VALUES (2, 4);
INSERT INTO team_users (team_id, user_id) VALUES (4, 4);
INSERT INTO team_users (team_id, user_id) VALUES (2, 3);
INSERT INTO team_users (team_id, user_id) VALUES (1, 3);

SELECT team.name FROM team LEFT JOIN team_users ON team.team_id = team_users.team_id WHERE team_users.team_id IS NULL;