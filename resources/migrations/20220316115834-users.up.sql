CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  sid VARCHAR(20),
  name VARCHAR(30),
  login VARCHAR(20),
  password TEXT,
  is_admin BOOLEAN DEFAULT FALSE,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp DEFAULT CURRENT_TIMESTAMP)