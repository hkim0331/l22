-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(sid, login, name, password, uhour)
VALUES (:sid, :login, :name, :password, :uhour)

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
-- SELECT * FROM users
SELECT login, password, uhour FROM users
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
-- SELECT * FROM users
SELECT login, password FROM users
ORDER BY updated_at DESC, created_at DESC
