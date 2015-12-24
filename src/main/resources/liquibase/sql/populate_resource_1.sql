TRUNCATE module_resource CASCADE;
TRUNCATE resource CASCADE;
    
INSERT INTO resource(id, name, type, path, storage_type) VALUES 
	(1, 'Модуль 1. Відео.', 1, 'https://www.youtube.com/embed/LICSA6iJd6w', 0),
	(2, 'Модуль 1. Конспект. Частина 1.', 0, '0B02lHXExpq3XbThVU1ZWS1pFT1U', 2),
	(3, 'Модуль 1. Конспект. Частина 2.', 0, '0B02lHXExpq3XbThVU1ZWS1pFT1U', 2),
	(4, 'Модуль 2. Відео.', 1, 'https://www.youtube.com/embed/Y_dsckWhclE', 0),
	(5, 'Модуль 3. Конспект.', 0, '0B02lHXExpq3XbThVU1ZWS1pFT1U', 2),
	(6, 'Модуль 4. Конспект.', 0, '0B02lHXExpq3XbThVU1ZWS1pFT1U', 2),
	
	(7, 'Уроки танцев для программистов. Часть 1. Полиморфизм.', 1, 'https://www.youtube.com/embed/fVGCLEAqUtA', 0),
	(8, 'Уроки танцев для программистов. Часть 2. Икапсуляция.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(9, 'Уроки танцев для программистов. Часть 3. Наследование.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(10, 'Уроки танцев для программистов. Часть 4. Абстрация.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(11, 'Уроки танцев для программистов. Часть 5. БД.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(12, 'Уроки танцев для программистов. Часть 6. Стек оверфлов.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(13, 'Уроки танцев для программистов. Часть 7. Клиент-Сервер.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(14, 'Уроки танцев для программистов. Часть 8. IoC & DI.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(15, 'Уроки танцев для программистов. Часть 9. Баг фиксинг.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(16, 'Уроки танцев для программистов. Часть 10. Солюшн.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	
	(17, 'Етика для программістів. Частина 1.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(18, 'Етика для программістів. Частина 2.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(19, 'Етика для программістів. Частина 3.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(20, 'Етика для программістів. Частина 4.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(21, 'Етика для программістів. Частина 5.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(22, 'Етика для программістів. Частина 6.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(23, 'Етика для программістів. Частина 7.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(24, 'Етика для программістів. Частина 8.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(25, 'Етика для программістів. Частина 9.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2),
	(26, 'Етика для программістів. Частина 10.', 0, '0B02lHXExpq3XUGhwQXBydXNrZkU', 2);

SELECT setval('resource_id_seq', (SELECT MAX(id) FROM resource));

INSERT INTO module_resource(modules_id, resources_id) VALUES 
	(1, 1), (1, 2), (1, 3),
	(2, 4),
	(3, 5),
	(4, 6),
	(2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15), (2, 16),
	(7, 7), (7, 8), (7, 9), (7, 10), (7, 11), (7, 12), (7, 13), (7, 14), (7, 15), (7, 16),
 	(9, 17), (9, 18), (9, 19), (9, 20), (9, 21), (9, 22), (9, 23), (9, 24), (9, 25), (9, 26);
