DROP TABLE Event;

CREATE TABLE Event (
  title VARCHAR(255), 
  description VARCHAR(255), 
  created DATETIME, 
  updated DATETIME, 
  tickets_left INTEGER, 
  venue VARCHAR(255), 
  event_date DATETIME,
  id INTEGER PRIMARY KEY AUTOINCREMENT
);

DELETE FROM Event;

INSERT INTO Event (title, description, venue, created, updated, event_date, tickets_left)
  VALUES ('Event one', 'The first event with twelve tickets.', '123 Main St, Palmerston North, New Zealand', 
  	DATETIME(), DATETIME(), DATETIME(DATETIME(), "+1 hours"), 12);
  
INSERT INTO Event (title, description, venue, created, updated, event_date, tickets_left)
  VALUES ('Event two', 'The second event with no tickets.', '234 Main St, Palmerston North, New Zealand', 
  	DATETIME(), DATETIME(), DATETIME(DATETIME(), "+1 days"), 0);
  
INSERT INTO Event (title, description, venue, created, updated, event_date, tickets_left)
  VALUES ('Event three', 'The third event with one ticket.', '123 Main St, Palmerston North, New Zealand', 
  	DATETIME(), DATETIME(), DATETIME(DATETIME(), "+1 months"), 1);
  
INSERT INTO Event (title, description, venue, created, updated, event_date, tickets_left)
  VALUES ('Event four', 'The expired fourth event with two tickets.', '234 Main St, Palmerston North, New Zealand', 
  	DATETIME(), DATETIME(), DATETIME(DATETIME(), "-1 days"), 2);
