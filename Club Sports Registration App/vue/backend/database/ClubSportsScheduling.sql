DROP TABLE IF EXISTS team_users;
DROP TABLE IF EXISTS availability;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS league;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS organization;

CREATE TABLE organization
(
        organization_id serial primary key,
        name varchar(255) not null
        
);

CREATE TABLE users
(
        user_id serial primary key,
        username varchar(255) not null unique,
        email varchar(255) not null,
        password varchar(255) not null,
        salt varchar(255) not null,
        team_id int,
        role varchar(255) NOT NULL default('user')
);
 
CREATE TABLE league
(
        league_id serial primary key,
        name varchar(255) not null,
        organization_id int not null,
        admin_id int,
        
        constraint fk_organization_organization_id foreign key (organization_id) REFERENCES organization(organization_id),
        constraint fk_users_user_id foreign key (admin_id) REFERENCES users(user_id)
              
);

CREATE TABLE team
(
        team_id serial primary key,
        name varchar(255) not null,
        team_email varchar(255) not null,
        primary_venue varchar(255) not null,
        secondary_venue varchar(255),
        league_id int not null,
        
        constraint fk_league_league_id foreign key (league_id) REFERENCES league(league_id)
        
);

CREATE TABLE availability
(
        availability_id serial primary key,
        team_id int not null,
        home boolean not null,
        away boolean not null,
        date varchar(255) not null,
        earliest_start varchar(255) not null,
        latest_start varchar(255) not null,
        
        constraint fk_team_team_id foreign key (team_id) REFERENCES team(team_id)
        
);




CREATE TABLE team_users
(
        team_id int not null,
        user_id int not null,
        
        constraint pk_team_id_user_id primary key ( team_id, user_id ),  
        constraint fk_team_team_id foreign key (team_id) REFERENCES team(team_id),
        constraint fk_user_user_id foreign key (user_id) REFERENCES users(user_id)
);