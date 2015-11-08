DELETE FROM user_roles;
DELETE FROM roles;
DELETE FROM users;


INSERT INTO users (id, email, password) VALUES (1, 'admin@gmail.com', '$2a$10$Euu810RqjaZAR1fkGfe2uOFjqvb397Ar9w9N5shQb1VQDp/wi99AS');
INSERT INTO users (id, email, password) VALUES (2, 'student@gmail.com', '$2a$10$KQaLIU4HZcwL6K3KYq/VXOMk2k8YzENro0RZ5y4x15okS/PMIOz8u');
INSERT INTO users (id, email, password) VALUES (3, 'teacher@gmail.com', '$2a$10$8okoESgrFW8eykqAOnRzeeoBQelmq4ToFWyF0Ql.eAiI7nUX1mU2u');
INSERT INTO users (id, email, password) VALUES (4, 'manager@gmail.com', '$2a$10$.MlLK71yijUbnfpcUFDEr.BRTyy35UbfBsJ.Qfcb1d44HTr99pQ9C');
INSERT INTO users (id, email, password) VALUES (5, 'anonymous@gmail.com', '$2a$10$y3Gd5K/negydiEHd7BCVXuMLExs158HbyV77FP7Py1n2IKUyESzQm');

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