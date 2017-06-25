--DROP TABLE remainders IF EXISTS;

CREATE TABLE remainders (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(30),
  description  VARCHAR(50),
  dueDate Date,
  status VARCHAR(30)
);
