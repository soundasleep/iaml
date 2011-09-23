-- User Heirarchy Root --

DROP TABLE iaml_user_root;
CREATE TABLE iaml_user_root(id INTEGER PRIMARY KEY AUTOINCREMENT);
DELETE FROM iaml_user_root;

INSERT INTO iaml_user_root (id)
  VALUES (10);

-- User --

DROP TABLE User;
CREATE TABLE User(email VARCHAR(255), password VARCHAR(255), generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, root_user_id INTEGER);
DELETE FROM User;

INSERT INTO User (email, password, generated_primary_key, root_user_id)
  VALUES ('manager@openiaml.org', 'manager', 10, 10);
  
-- Named User --

DROP TABLE named_user;
CREATE TABLE named_user(name VARCHAR(255), id INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER);
DELETE FROM named_user;

INSERT INTO named_user (name, id, User_generated_primary_key)
  VALUES ('Manager', 10, 10);

-- Manager --
  
DROP TABLE manager;
CREATE TABLE manager(generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER, named_user_id INTEGER);
DELETE FROM manager;

INSERT INTO manager (generated_primary_key, User_generated_primary_key, named_user_id)
  VALUES (10, 10, 10);
