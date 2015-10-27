DELETE FROM roles;
DELETE FROM users;


INSERT INTO users (id, email, password) VALUES (1, 'admin@gmail.com', 'admin');
INSERT INTO users (id, email, password) VALUES (2, 'student@gmail.com', 'student');
INSERT INTO users (id, email, password) VALUES (3, 'teacher@gmail.com', 'teacher');
INSERT INTO users (id, email, password) VALUES (4, 'manager@gmail.com', 'manager');
INSERT INTO users (id, email, password) VALUES (5, 'anonymous@gmail.com', 'anonymous');
INSERT INTO roles (id, name, user_id) VALUES (1,'ROLE_ADMIN',1);
INSERT INTO roles (id, name, user_id) VALUES (2,'ROLE_STUDENT',2);
INSERT INTO roles (id, name, user_id) VALUES (3,'ROLE_TEACHER',3);
INSERT INTO roles (id, name, user_id) VALUES (4,'ROLE_MANAGER',4);
INSERT INTO roles (id, name, user_id) VALUES (5,'ROLE_ANONYMOUS',5);


