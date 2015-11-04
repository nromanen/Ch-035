DELETE FROM user_roles;
DELETE FROM roles;
DELETE FROM users;
DROP TABLE IF EXISTS persistent_logins

INSERT INTO users (id, email, password, isEnabled) VALUES (1, 'admin@gmail.com', '$2a$10$Euu810RqjaZAR1fkGfe2uOFjqvb397Ar9w9N5shQb1VQDp/wi99AS', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (2, 'student@gmail.com', '$2a$10$KQaLIU4HZcwL6K3KYq/VXOMk2k8YzENro0RZ5y4x15okS/PMIOz8u', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (3, 'teacher@gmail.com', '$2a$10$8okoESgrFW8eykqAOnRzeeoBQelmq4ToFWyF0Ql.eAiI7nUX1mU2u', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (4, 'manager@gmail.com', '$2a$10$.MlLK71yijUbnfpcUFDEr.BRTyy35UbfBsJ.Qfcb1d44HTr99pQ9C', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (5, 'anonymous@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (6, 'user1@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (7, 'user2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (8, 'user3@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (9, 'user4@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (10, 'user5@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (11, 'user6@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (12, 'user7@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (13, 'user8@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (14, 'user9@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (15, 'user10@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (16, 'user11@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);

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
INSERT INTO user_roles (user_id, role_id) VALUES (6,5);
INSERT INTO user_roles (user_id, role_id) VALUES (7,5);
INSERT INTO user_roles (user_id, role_id) VALUES (8,5);
INSERT INTO user_roles (user_id, role_id) VALUES (9,5);
INSERT INTO user_roles (user_id, role_id) VALUES (10,5);
INSERT INTO user_roles (user_id, role_id) VALUES (11,5);
INSERT INTO user_roles (user_id, role_id) VALUES (12,5);
INSERT INTO user_roles (user_id, role_id) VALUES (13,5);
INSERT INTO user_roles (user_id, role_id) VALUES (14,5);
INSERT INTO user_roles (user_id, role_id) VALUES (15,5);
INSERT INTO user_roles (user_id, role_id) VALUES (16,5);

CREATE TABLE persistent_logins (username VARCHAR(64) NOT NULL, series VARCHAR(64) NOT NULL, token VARCHAR(64) NOT NULL, last_used TIMESTAMP NOT NULL,  PRIMARY KEY (series));