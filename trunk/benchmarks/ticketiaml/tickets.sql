DROP TABLE Ticket;
CREATE TABLE Ticket(generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, event_id INTEGER);
DELETE FROM Ticket;

INSERT INTO Ticket (user_id, event_id)
  VALUES (10, 1);

INSERT INTO Ticket (user_id, event_id)
  VALUES (10, 2);

INSERT INTO Ticket (user_id, event_id)
  VALUES (10, 2);
