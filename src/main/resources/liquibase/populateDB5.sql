TRUNCATE groups_users CASCADE;
TRUNCATE groups CASCADE;

INSERT INTO groups(id, name, course_id, startdate) VALUES (1, 'Ch-001.UI', 1, '2015-12-12 00:00:00');
INSERT INTO groups(id, name, course_id, startdate) VALUES (2, 'Ch-002.CSS', 2, '2015-12-12 00:00:00');
INSERT INTO groups(id, name, course_id, startdate) VALUES (3, 'Ch-003.HTML', 3, '2015-12-12 00:00:00');
INSERT INTO groups(id, name, course_id, startdate) VALUES (4, 'Ch-004.Java', 4, '2015-12-12 00:00:00');
INSERT INTO groups(id, name, course_id, startdate) VALUES (5, 'Ch-005.NET', 5, '2015-12-12 00:00:00');
INSERT INTO groups(id, name, course_id, startdate) VALUES (6, 'Ch-006.UX', 6, '2015-12-12 00:00:00');


INSERT INTO groups_users(groups_id, users_id) VALUES (4, 2);

SELECT setval('group_id_seq', (SELECT MAX(id) FROM groups));