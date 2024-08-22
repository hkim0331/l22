-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(sid, login, name, password, uhour, subj, ayear)
VALUES (:sid, :login, :name, :password, :uhour, :subj, :ayear)

-- :name update-password! :! :n
-- :doc updates an existing user's password and updated_at column
UPDATE users
SET password = :password, updated_at = :updated_at
WHERE login = :login

-- :name user :? :1
-- :doc get user by id
SELECT * FROM users
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieves a user record given the login
-- added ayear, 2024-08-22.
SELECT login, password, uhour, ayear FROM users
WHERE login = :login

-- :name profile :? :1
-- :doc retrieves a user record given the login
SELECT * FROM users
WHERE login = :login

-- :name get-user-by-sid :? :1
-- :doc retrieves a user record given the sid
SELECT * FROM users
WHERE sid = :sid

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE login = :login

-- :name list-users :? :*
-- :doc list all users
SELECT login, password FROM users
ORDER BY updated_at DESC, created_at DESC

-- :name list-users-year :? :*
-- :doc list all users
SELECT login, password FROM users
WHERE ayear = :year
ORDER BY updated_at DESC, created_at DESC

-- :name subj :? :*
-- :doc  list users who take the subject `subj`
SELECT login FROM users
WHERE subj = :subj

-- :name user-randomly :? :1
-- :doc select a user randomly who take the class uhour `uhour`.
SELECT * FROM users
WHERE uhour = :uhour and ayear='2024'
ORDER BY random()
-- LIMIT 1
