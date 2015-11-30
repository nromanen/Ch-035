DELETE FROM question_answer;
DELETE FROM test_question;
DELETE FROM module_test;
DELETE FROM answer;
DELETE FROM question;
DELETE FROM test;

INSERT INTO test(id, available, name) VALUES (1, true, 'Основні поняття об''єктно-орієнтованого програмування.');
INSERT INTO test(id, available, name) VALUES (2, true, 'Синтаксис. Об''єкти і класи.');
INSERT INTO test(id, available, name) VALUES (3, true, 'Хід виконання програми.');
INSERT INTO test(id, available, name) VALUES (4, true, 'Потоки вводу-виводу. Робота з файловою системою.');

INSERT INTO module_test(module_id, tests_id) VALUES (1, 1);
INSERT INTO module_test(module_id, tests_id) VALUES (1, 2);
INSERT INTO module_test(module_id, tests_id) VALUES (1, 3);
INSERT INTO module_test(module_id, tests_id) VALUES (1, 4);

INSERT INTO question(id, text) VALUES (1, 'Виберіть правильне визначення інкапсуляції.');
INSERT INTO question(id, text) VALUES (2, 'Виберіть правильне визначення поліморфізму.');
INSERT INTO question(id, text) VALUES (3, 'Виберіть правильне визначення наслідування.');
INSERT INTO question(id, text) VALUES (4, 'Виберіть правильне визначення абстракції.');
INSERT INTO question(id, text) VALUES (5, 'Виберіть правильне визначення класу.');

INSERT INTO question(id, text) VALUES (6, 'Виберіть коректне оголошення класу.');
INSERT INTO question(id, text) VALUES (7, 'Яким буде результат виконання коду?');
INSERT INTO question(id, text) VALUES (8, 'Який з даних методів є методом класу Object?');
INSERT INTO question(id, text) VALUES (9, 'Чи буде відкомпільовано наступний код?');
INSERT INTO question(id, text) VALUES (10,'Чи може метод main бути оголошеним як final?');

INSERT INTO question(id, text) VALUES (11, 'Яким буде результат виконання коду?');
INSERT INTO question(id, text) VALUES (12, 'Який Exception буде отримано в результаті виконання програми?');
INSERT INTO question(id, text) VALUES (13, 'Скільки ітерацій циклу відбудеться?');
INSERT INTO question(id, text) VALUES (14, 'Чи буде відкомпільовано наступний код?');
INSERT INTO question(id, text) VALUES (15, 'Вставте вірний фрагмент коду в строку 3.');

INSERT INTO question(id, text) VALUES (16, 'Скільки директорій створиться даним кодом?');
INSERT INTO question(id, text) VALUES (17, 'Для фільтрації файлів за їх розширенням використовують:');
INSERT INTO question(id, text) VALUES (18, 'Чи буде відкомпільовано наступний код?');
INSERT INTO question(id, text) VALUES (19, 'Яким буде результат виконання коду?');
INSERT INTO question(id, text) VALUES (20, 'Для чого використовується метод flush()?');

INSERT INTO test_question(test_id, questions_id) VALUES (1, 1);
INSERT INTO test_question(test_id, questions_id) VALUES (1, 2);
INSERT INTO test_question(test_id, questions_id) VALUES (1, 3);
INSERT INTO test_question(test_id, questions_id) VALUES (1, 4);
INSERT INTO test_question(test_id, questions_id) VALUES (1, 5);

INSERT INTO test_question(test_id, questions_id) VALUES (2, 6);
INSERT INTO test_question(test_id, questions_id) VALUES (2, 7);
INSERT INTO test_question(test_id, questions_id) VALUES (2, 8);
INSERT INTO test_question(test_id, questions_id) VALUES (2, 9);
INSERT INTO test_question(test_id, questions_id) VALUES (2, 10);

INSERT INTO test_question(test_id, questions_id) VALUES (3, 11);
INSERT INTO test_question(test_id, questions_id) VALUES (3, 12);
INSERT INTO test_question(test_id, questions_id) VALUES (3, 13);
INSERT INTO test_question(test_id, questions_id) VALUES (3, 14);
INSERT INTO test_question(test_id, questions_id) VALUES (3, 15);

INSERT INTO test_question(test_id, questions_id) VALUES (4, 16);
INSERT INTO test_question(test_id, questions_id) VALUES (4, 17);
INSERT INTO test_question(test_id, questions_id) VALUES (4, 18);
INSERT INTO test_question(test_id, questions_id) VALUES (4, 19);
INSERT INTO test_question(test_id, questions_id) VALUES (4, 20);