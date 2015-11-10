DELETE FROM user_roles;
DELETE FROM roles;
DELETE FROM users;
DROP TABLE IF EXISTS persistent_logins

INSERT INTO users (id, email, password, isEnabled) VALUES (1, 'admin@gmail.com', '$2a$10$Euu810RqjaZAR1fkGfe2uOFjqvb397Ar9w9N5shQb1VQDp/wi99AS', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (2, 'student@gmail.com', '$2a$10$KQaLIU4HZcwL6K3KYq/VXOMk2k8YzENro0RZ5y4x15okS/PMIOz8u', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (3, 'teacher@gmail.com', '$2a$10$8okoESgrFW8eykqAOnRzeeoBQelmq4ToFWyF0Ql.eAiI7nUX1mU2u', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (4, 'manager@gmail.com', '$2a$10$.MlLK71yijUbnfpcUFDEr.BRTyy35UbfBsJ.Qfcb1d44HTr99pQ9C', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (5, 'anonymous@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (6, 'sdsadmin2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (7, 'cew4student2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (8, 'nhgstudent1@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (9, 'kliuteacher2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (10, 'nwyamanager2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (11, 'anonymous2@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (12, 'mknfjkeanonymous3@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (13, 'mszstudent3@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (14, 'ekflsstudent4@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (15, 'uamstudent5@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (16, '5jksdstudent6@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (17, 'lasteacher3@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (18, 'idmstudent9@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (19, 'abzxstudent8@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);
INSERT INTO users (id, email, password, isEnabled) VALUES (20, 'zkistudent7@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm', true);

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
INSERT INTO user_roles (user_id, role_id) VALUES (6,1);
INSERT INTO user_roles (user_id, role_id) VALUES (7,2);
INSERT INTO user_roles (user_id, role_id) VALUES (8,2);
INSERT INTO user_roles (user_id, role_id) VALUES (9,3);
INSERT INTO user_roles (user_id, role_id) VALUES (10,4);
INSERT INTO user_roles (user_id, role_id) VALUES (11,5);
INSERT INTO user_roles (user_id, role_id) VALUES (12,5);
INSERT INTO user_roles (user_id, role_id) VALUES (13,2);
INSERT INTO user_roles (user_id, role_id) VALUES (14,2);
INSERT INTO user_roles (user_id, role_id) VALUES (15,2);
INSERT INTO user_roles (user_id, role_id) VALUES (16,2);
INSERT INTO user_roles (user_id, role_id) VALUES (17,3);
INSERT INTO user_roles (user_id, role_id) VALUES (18,2);
INSERT INTO user_roles (user_id, role_id) VALUES (19,2);
INSERT INTO user_roles (user_id, role_id) VALUES (20,2);

CREATE TABLE persistent_logins (username VARCHAR(64) NOT NULL, series VARCHAR(64) NOT NULL, token VARCHAR(64) NOT NULL, last_used TIMESTAMP NOT NULL,  PRIMARY KEY (series));