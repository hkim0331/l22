-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(sid, login, name, password)
VALUES (:sid, :login, :name, :password)

-- :name update-password! :! :n
-- :doc updates an existing user's password
UPDATE users
SET password = :password
WHERE login = :login

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT * FROM users
WHERE login = :login

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE login = :login

-- :name list-users :? :*
-- :doc list all users
SELECT * FROM users
