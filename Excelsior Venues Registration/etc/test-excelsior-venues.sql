START TRANSACTION;
ROLLBACK;

INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id;

SELECT * FROM VENUE;

SELECT * FROM space
JOIN reservation ON space.id = reservation.space_id
JOIN venue ON space.venue_id = venue.id;

SELECT space.id, space.name, daily_rate, max_occupancy, is_accessible, (daily_rate * ?) AS total_cost
FROM space
WHERE (space.max_occupancy < ?)


SELECT space_id
FROM reservation
WHERE NOT ((start_date <= '2019-10-25') AND (end_date >= '2019-10-20'))
GROUP BY space_id;






--AND (EXTRACT (MONTH FROM DATE (DATEADD(DAY, 7, '2019-11-25')));

--select to_char((current_date + interval '10 days'), 'yyyy-mm-dd')as five_days_from_now;

SELECT DISTINCT ON (max_occupancy, is_accessible) * FROM space
WHERE venue_id = ? AND space_id NOT IN 
(SELECT DISTINCT space_id FROM reservation WHERE (start_date, end_date) OVERLAPS (?, ?);


select date ? + integer '7' as end_date;


SELECT space.id, space.name, daily_rate, max_occupancy, is_accessible, (daily_rate * ?) AS total_cost
FROM space
JOIN venue on space.venue_id = venue.id
WHERE (space.max_occupancy > ?) AND (space.venue_id = ?) AND 
((space.open_from IS NULL AND space.open_to IS NULL) OR 
(space.id NOT IN
(SELECT space_id FROM reservation
WHERE (?, ?) OVERLAPS (start_date, end_date) 
GROUP BY space_id)));
--maybe something?

SELECT *
FROM space
WHERE ((EXTRACT (MONTH FROM DATE ?)) >= open_from) AND ((EXTRACT (MONTH FROM DATE ?)) <= open_to);




select (? as user_start_date) + (user_start_date + ?) as user_end_date;
group by user_end_date;

select date '2001-08-28' + integer '7' as end_date;

SELECT space.id, space.name, daily_rate, max_occupancy, is_accessible, (daily_rate * ?) AS total_cost 
FROM space
JOIN venue on space.venue_id = venue.id
WHERE (space.max_occupancy > ?) AND (space.venue_id = ?) AND 
(((EXTRACT (MONTH FROM DATE ?) >= open_from) AND (EXTRACT (MONTH FROM DATE ?) <= open_to)) AND
((EXTRACT (MONTH FROM DATE ?) >= open_from) AND (EXTRACT (MONTH FROM DATE ?) <= open_to)))
OR (space.open_from IS NULL AND space.open_to IS NULL)) OR 
(space.id NOT IN
(SELECT space_id FROM reservation
WHERE (?, ?) OVERLAPS (start_date, end_date) 
GROUP BY space_id)));

SELECT * FROM SPACE;

SELECT id FROM venue;

SELECT id, name, city_id, description FROM venue WHERE id = 1000;

SELECT venue.id, venue.name AS venue_name, city.name AS city_name, city.state_abbreviation, category.name AS category_name, venue.description
FROM venue
JOIN city ON venue.city_id = city.id
JOIN category_venue ON venue.id = category_venue.venue_id
JOIN category ON category_venue.category_id = category.id
WHERE venue.id = ?;

SELECT venue.id AS venue_id, venue.name AS venue_name, venue.city_id AS city_id, venue.description AS venue_description, city.name AS city_name, city.state_abbreviation AS state_abbreviation, STRING_AGG(category.name, ',' ) AS category_name FROM venue
JOIN city ON venue.city_id = city.id
LEFT JOIN category_venue ON venue.id = category_venue.venue_id
LEFT JOIN category ON category_venue.category_id = category.id
GROUP BY venue.id, city.name, city.state_abbreviation;




SELECT space.id, space.name, daily_rate, max_occupancy, is_accessible, (daily_rate * ?) AS total_cost FROM space
						JOIN venue on space.venue_id = venue.id
					WHERE (space.max_occupancy > ?) AND (space.venue_id = ?) AND
					((space.open_from IS NULL AND space.open_to IS NULL) OR
					(space.id NOT IN
				(SELECT space_id FROM reservation
				WHERE (? > start_date AND ? < end_date) OR (? > start_date AND ? < end_date)
				GROUP BY space_id)));
				
				input_start_date > state_date
				
SELECT space.id AS space_id, space.venue_id AS venue_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate, space.max_occupancy AS max_occupancy, venue.name AS venue_name FROM space
				JOIN venue ON venue_id = venue.id
				WHERE venue.id = ?;
								SELECT space.id AS space_id, space.venue_id AS venue_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate, space.max_occupancy AS max_occupancy, venue.name AS venue_name FROM space;
			
SELECT reservation.reservation_id AS reservation_id, reservation.space_id AS space_id, reservation.number_of_attendees AS number_of_attendees, reservation.start_date AS start_date, reservation.end_date AS end_date, reservation.reserved_for AS reserved_for, venue.name AS venue_name, space.name AS space_name, CAST (space.daily_rate AS numeric) AS daily_rate
FROM reservation
JOIN space ON reservation.space_id = space.id
JOIN venue ON space.venue_id = venue.id
WHERE reservation_id = ?;		
			
