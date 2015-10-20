DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles (id, name) VALUES (1,'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2,'ROLE_STUDENT');
INSERT INTO roles (id, name) VALUES (3,'ROLE_TEACHER');
INSERT INTO roles (id, name) VALUES (4,'ROLE_MANAGER');
INSERT INTO roles (id, name) VALUES (5,'ROLE_ANONYMOUS');

INSERT INTO users (id, email, password, role_id) VALUES (1, 'admin@gmail.com', 'admin', 1);
INSERT INTO users (id, email, password, role_id) VALUES (2, 'student@gmail.com', 'student', 2);
INSERT INTO users (id, email, password, role_id) VALUES (3, 'teacher@gmail.com', 'teacher', 3);
INSERT INTO users (id, email, password, role_id) VALUES (4, 'manager@gmail.com', 'manager', 4);
INSERT INTO users (id, email, password, role_id) VALUES (5, 'anonymous@gmail.com', 'anonymous', 5);