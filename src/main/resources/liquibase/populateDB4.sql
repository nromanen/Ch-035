-- Виберіть правильне визначення інкапсуляції.
INSERT INTO answer(id, correct, text, disable) VALUES (1, true,'Інкапсуляція - один з трьох основних механізмів об''єктно-орієнтованого програмування.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (2, false,'Інкапсуляція - засіб міжпотокових комунікацій.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (3, false,'Інкапсуляція - автодоповнення (autocomplete) в середовищі програмування.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (4, false,'Інкапсуляція - ситуація, коли два потоки намагаються використати одну синхронізовану секцію.', false);

-- Виберіть правильне визначення поліморфізму.
INSERT INTO answer(id, correct, text, disable) VALUES (5, false,'Поліморфізм - область видимості змінної.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (6, false,'Поліморфізм - використання багатьма потоками багатьох синхронізованих секцій.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (7, true,'Поліморфізм - один з трьох основних механізмів об''єктно-орієнтованого програмування.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (8, false,'Поліморфізм - метод запису файлів в файлову систему.', false);

-- Виберіть правильне визначення наслідування.
INSERT INTO answer(id, correct, text, disable) VALUES (9, false,'Наслідування - копіювання частини коду з одного метода в інший.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (10, true,'Наслідування - один з трьох основних механізмів об''єктно-орієнтованого програмування.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (11, false,'Наслідування - принцип за яким розташовуються елементи в масиві.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (12, false,'Наслідування - використання одного коду декілька раз.', false);

-- Виберіть правильне визначення абстракції.
INSERT INTO answer(id, correct, text, disable) VALUES (13, false,'Абстракція - використання сторонніх бібліотек для вирішення задачі.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (14, false,'Абстракція - перевизначення методів класу предка в класі нащадку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (15, false,'Абстракція - специфікація певного API.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (16, true,'Абстракція - метод відокремлення деталей з метою отримання можливості зосередитись на найважливіших особливостях об''єкта.', false);

-- Виберіть правильне визначення класу.
INSERT INTO answer(id, correct, text, disable) VALUES (17, true,'Клас - ключове поняття ООП. Описує зміст і поведінку об''єкта.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (18, false,'Клас - функція для визначення типу даних.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (19, false,'Клас - методика збереження різнотипних даних в персистентному сховищі.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (20, false,'Клас - результат виконання хеш-функції.', false);

-- Виберіть коректне оголошення класу.
INSERT INTO answer(id, correct, text, disable) VALUES (21, true,'public class Demo {}', false);
INSERT INTO answer(id, correct, text, disable) VALUES (22, false,'override class Demo {}', false);
INSERT INTO answer(id, correct, text, disable) VALUES (23, true,'class Demo {}', false);
INSERT INTO answer(id, correct, text, disable) VALUES (24, false,'serialized class Demo {}', false);

-- Яким буде результат виконання коду System.out.println("4/2=" + 4/2 == "4/2=2"); ?
INSERT INTO answer(id, correct, text, disable) VALUES (25, false,'false', false);
INSERT INTO answer(id, correct, text, disable) VALUES (26, false,'4/2=2 == true', false);
INSERT INTO answer(id, correct, text, disable) VALUES (27, true,'true', false);
INSERT INTO answer(id, correct, text, disable) VALUES (28, false,'4/2=2 == false', false);

-- Який з даних методів є методом класу Object?
INSERT INTO answer(id, correct, text, disable) VALUES (29, true,'finalize()', false);
INSERT INTO answer(id, correct, text, disable) VALUES (30, false,'getOutputStream()', false);
INSERT INTO answer(id, correct, text, disable) VALUES (31, false,'join()', false);
INSERT INTO answer(id, correct, text, disable) VALUES (32, false,'run()', false);

-- Чи буде відкомпільовано наступний код System.out.println(0.0/0.0 + 10); ?
INSERT INTO answer(id, correct, text, disable) VALUES (33, true,'Так.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (34, false,'Ні. Компілятор видасть помилку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (35, false,'Так, якщо версія JVM 1.7 і вище.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (36, false,'Так, якщо виконувати даний код всередині методу.', false);

-- Чи може метод main бути оголошеним як final?
INSERT INTO answer(id, correct, text, disable) VALUES (37, false,'Так.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (38, true,'Ні.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (39, false,'Може, якщо версія JVM 1.7 і вище.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (40, false,'Може, якщо установити методу модифікатор доступу "private".', false);

-- Яким буде результат виконання коду System.out.println(Math.sqrt(-2.0)); ?
INSERT INTO answer(id, correct, text, disable) VALUES (41, false,'Infinity.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (42, false,'Помилка компіляції.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (43, true,'Nan.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (44, false,'Помилка виконання.', false);

-- Який Exception буде отримано в результаті виконання коду System.out.println(a/0); ?
INSERT INTO answer(id, correct, text, disable) VALUES (45, false,'DivisionException.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (46, false,'IOException.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (47, true,'ArithmeticException.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (48, false,'IllegalArgumentException.', false);

-- Скільки ітерацій циклу відбудеться for (int i = 2; i < 10; ++i) ?
INSERT INTO answer(id, correct, text, disable) VALUES (49, true,'8', false);
INSERT INTO answer(id, correct, text, disable) VALUES (50, false,'7', false);
INSERT INTO answer(id, correct, text, disable) VALUES (51, false,'9', false);
INSERT INTO answer(id, correct, text, disable) VALUES (52, false,'10', false);

-- Чи буде відкомпільовано наступний код public static void test(String str1, String... str2) {}?
INSERT INTO answer(id, correct, text, disable) VALUES (53, false,'Ні, якщо версія JVM 1.7 і нижче.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (54, false,'Ні. Компілятор видасть помилку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (55, true,'Так.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (56, false,'Жоден з варіантів не є вірним.', false);

-- Для чого використовується метод flush()?
INSERT INTO answer(id, correct, text, disable) VALUES (57, false,'Для закриття потоку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (58, true,'Для звільнення всіх вихідних буферів.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (59, false,'Для відкриття потоку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (60, true,'Для перехоплення виключень вводу-виводу.', false);

-- Скільки директорій створиться даним кодом File file = new File("C:/Temp").mkdir();?
INSERT INTO answer(id, correct, text, disable) VALUES (61, false,'Жодної.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (62, false,'Помилка компіляції.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (63, true,'Одна.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (64, false,'Помилка виконання.', false);

-- Для фільтрації файлів за їх розширенням використовують:
INSERT INTO answer(id, correct, text, disable) VALUES (65, false,'Метод fileNameExtensionFilter().', false);
INSERT INTO answer(id, correct, text, disable) VALUES (66, false,'Інтерфейс FileNameExtensionFilter.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (67, true,'Клас FileNameExtensionFilter.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (68, false,'Жоден з варіантів не є вірним.', false);

-- Чи буде відкомпільовано наступний код: public static final int AC = ~220; ?
INSERT INTO answer(id, correct, text, disable) VALUES (69, false,'Ні, якщо версія JVM 1.7 і нижче.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (70, false,'Ні. Компілятор видасть помилку.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (71, true,'Так.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (72, false,'Жоден з варіантів не є вірним.', false);

-- Скільки ітерацій циклу відбудеться for (int i = 2; i < 10; ++i) ?
INSERT INTO answer(id, correct, text, disable) VALUES (73, true,'Поточна дата в unix-like форматі.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (74, false,'Поточна дата в форматі DD/MM/YYYY.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (75, false,'Поточна дата в форматі DD/MM/YYYY HH:MM:SS.', false);
INSERT INTO answer(id, correct, text, disable) VALUES (76, false,'Поточна дата в форматі DYYYY.', false);

-- Виберіть вірний фрагмент коду можливий після строки Formatter formatter = new Formatter(Locale.ROOT);
INSERT INTO answer(id, correct, text, disable) VALUES (77, false,'formatter.format("!.2x", 100.0/3.0);', false);
INSERT INTO answer(id, correct, text, disable) VALUES (78, true,'formatter.format("%.2E\n", 100.0/3.0);', false);
INSERT INTO answer(id, correct, text, disable) VALUES (79, false,'formatter.format("%%.[%]", 100.0/3.0);', false);
INSERT INTO answer(id, correct, text, disable) VALUES (80, true,'formatter.format("%.2f", 100.0/3.0);', false);

INSERT INTO question_answer(question_id, answers_id) VALUES (1, 1);
INSERT INTO question_answer(question_id, answers_id) VALUES (1, 2);
INSERT INTO question_answer(question_id, answers_id) VALUES (1, 3);
INSERT INTO question_answer(question_id, answers_id) VALUES (1, 4);

INSERT INTO question_answer(question_id, answers_id) VALUES (2, 5);
INSERT INTO question_answer(question_id, answers_id) VALUES (2, 6);
INSERT INTO question_answer(question_id, answers_id) VALUES (2, 7);
INSERT INTO question_answer(question_id, answers_id) VALUES (2, 8);

INSERT INTO question_answer(question_id, answers_id) VALUES (3, 9);
INSERT INTO question_answer(question_id, answers_id) VALUES (3, 10);
INSERT INTO question_answer(question_id, answers_id) VALUES (3, 11);
INSERT INTO question_answer(question_id, answers_id) VALUES (3, 12);

INSERT INTO question_answer(question_id, answers_id) VALUES (4, 13);
INSERT INTO question_answer(question_id, answers_id) VALUES (4, 14);
INSERT INTO question_answer(question_id, answers_id) VALUES (4, 15);
INSERT INTO question_answer(question_id, answers_id) VALUES (4, 16);

INSERT INTO question_answer(question_id, answers_id) VALUES (5, 17);
INSERT INTO question_answer(question_id, answers_id) VALUES (5, 18);
INSERT INTO question_answer(question_id, answers_id) VALUES (5, 19);
INSERT INTO question_answer(question_id, answers_id) VALUES (5, 20);

INSERT INTO question_answer(question_id, answers_id) VALUES (6, 21);
INSERT INTO question_answer(question_id, answers_id) VALUES (6, 22);
INSERT INTO question_answer(question_id, answers_id) VALUES (6, 23);
INSERT INTO question_answer(question_id, answers_id) VALUES (6, 24);

INSERT INTO question_answer(question_id, answers_id) VALUES (7, 25);
INSERT INTO question_answer(question_id, answers_id) VALUES (7, 26);
INSERT INTO question_answer(question_id, answers_id) VALUES (7, 27);
INSERT INTO question_answer(question_id, answers_id) VALUES (7, 28);

INSERT INTO question_answer(question_id, answers_id) VALUES (8, 29);
INSERT INTO question_answer(question_id, answers_id) VALUES (8, 30);
INSERT INTO question_answer(question_id, answers_id) VALUES (8, 31);
INSERT INTO question_answer(question_id, answers_id) VALUES (8, 32);

INSERT INTO question_answer(question_id, answers_id) VALUES (9, 33);
INSERT INTO question_answer(question_id, answers_id) VALUES (9, 34);
INSERT INTO question_answer(question_id, answers_id) VALUES (9, 35);
INSERT INTO question_answer(question_id, answers_id) VALUES (9, 36);

INSERT INTO question_answer(question_id, answers_id) VALUES (10, 37);
INSERT INTO question_answer(question_id, answers_id) VALUES (10, 38);
INSERT INTO question_answer(question_id, answers_id) VALUES (10, 39);
INSERT INTO question_answer(question_id, answers_id) VALUES (10, 40);

INSERT INTO question_answer(question_id, answers_id) VALUES (11, 41);
INSERT INTO question_answer(question_id, answers_id) VALUES (11, 42);
INSERT INTO question_answer(question_id, answers_id) VALUES (11, 43);
INSERT INTO question_answer(question_id, answers_id) VALUES (11, 44);

INSERT INTO question_answer(question_id, answers_id) VALUES (12, 45);
INSERT INTO question_answer(question_id, answers_id) VALUES (12, 46);
INSERT INTO question_answer(question_id, answers_id) VALUES (12, 47);
INSERT INTO question_answer(question_id, answers_id) VALUES (12, 48);

INSERT INTO question_answer(question_id, answers_id) VALUES (13, 49);
INSERT INTO question_answer(question_id, answers_id) VALUES (13, 50);
INSERT INTO question_answer(question_id, answers_id) VALUES (13, 51);
INSERT INTO question_answer(question_id, answers_id) VALUES (13, 52);

INSERT INTO question_answer(question_id, answers_id) VALUES (14, 53);
INSERT INTO question_answer(question_id, answers_id) VALUES (14, 54);
INSERT INTO question_answer(question_id, answers_id) VALUES (14, 55);
INSERT INTO question_answer(question_id, answers_id) VALUES (14, 56);

INSERT INTO question_answer(question_id, answers_id) VALUES (15, 57);
INSERT INTO question_answer(question_id, answers_id) VALUES (15, 58);
INSERT INTO question_answer(question_id, answers_id) VALUES (15, 59);
INSERT INTO question_answer(question_id, answers_id) VALUES (15, 60);

INSERT INTO question_answer(question_id, answers_id) VALUES (16, 61);
INSERT INTO question_answer(question_id, answers_id) VALUES (16, 62);
INSERT INTO question_answer(question_id, answers_id) VALUES (16, 63);
INSERT INTO question_answer(question_id, answers_id) VALUES (16, 64);

INSERT INTO question_answer(question_id, answers_id) VALUES (17, 65);
INSERT INTO question_answer(question_id, answers_id) VALUES (17, 66);
INSERT INTO question_answer(question_id, answers_id) VALUES (17, 67);
INSERT INTO question_answer(question_id, answers_id) VALUES (17, 68);

INSERT INTO question_answer(question_id, answers_id) VALUES (18, 69);
INSERT INTO question_answer(question_id, answers_id) VALUES (18, 70);
INSERT INTO question_answer(question_id, answers_id) VALUES (18, 71);
INSERT INTO question_answer(question_id, answers_id) VALUES (18, 72);

INSERT INTO question_answer(question_id, answers_id) VALUES (19, 73);
INSERT INTO question_answer(question_id, answers_id) VALUES (19, 74);
INSERT INTO question_answer(question_id, answers_id) VALUES (19, 75);
INSERT INTO question_answer(question_id, answers_id) VALUES (19, 76);

INSERT INTO question_answer(question_id, answers_id) VALUES (20, 77);
INSERT INTO question_answer(question_id, answers_id) VALUES (20, 78);
INSERT INTO question_answer(question_id, answers_id) VALUES (20, 79);
INSERT INTO question_answer(question_id, answers_id) VALUES (20, 80);
