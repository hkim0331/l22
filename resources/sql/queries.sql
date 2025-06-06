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

-- :name list-users-year :? :*
-- :doc list all users
SELECT login, password FROM users
WHERE ayear = :year

-- :name list-users-year-subj-uhour :? :*
-- :doc list users year subj uhour
SELECT login, uhour, subj, ayear, uhour FROM users
WHERE ayear = :year AND subj = :subj AND uhour = :uhour

-- :name subj :? :*
-- :doc  list users who take the subject `subj`
SELECT login FROM users
WHERE subj = :subj

-- :name user-randomly :? :1
-- :doc select a user randomly who take the class uhour `uhour`.
SELECT * FROM users
WHERE uhour = :uhour and ayear='2025' and uhour='wed1'
ORDER BY random()

-- :name login :? :1
-- :doc return `login` whose sid is `sid`.
SELECT login FROM users
WHERE sid = :sid

-- :name sid :? :1
-- :doc return `sid` whose login is `login`.
SELECT sid FROM users
WHERE login = :login
