DELETE FROM user_roles;
DELETE FROM roles;
DELETE FROM users;


INSERT INTO users (id, email, password) VALUES (1, 'admin@gmail.com', 'admin');
INSERT INTO users (id, email, password) VALUES (2, 'student@gmail.com', 'student');
INSERT INTO users (id, email, password) VALUES (3, 'teacher@gmail.com', 'teacher');
INSERT INTO users (id, email, password) VALUES (4, 'manager@gmail.com', 'manager');
INSERT INTO users (id, email, password) VALUES (5, 'anonymous@gmail.com', 'anonymous');

INSERT INTO roles (id, name) VALUES (1,'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2,'ROLE_STUDENT');
INSERT INTO roles (id, name) VALUES (3,'ROLE_TEACHER');
INSERT INTO roles (id, name) VALUES (4,'ROLE_MANAGER');
INSERT INTO roles (id, name) VALUES (5,'ROLE_ANONYMOUS');


INSERT INTO user_roles (user_id, role_id) VALUES (1,1);
INSERT INTO user_roles (user_id, role_id) VALUES (2,2);
INSERT INTO user_roles (user_id, role_id) VALUES (3,3);
INSERT INTO user_roles (user_id, role_id) VALUES (4,4);
INSERT INTO user_roles (user_id, role_id) VALUES (5,5);