
INSERT INTO authors (id, name) VALUES (1, 'George Orwell');

INSERT INTO categories (id, name) VALUES (1, 'Fiction');

INSERT INTO publishers (id, name) VALUES (1, 'Bloomsbury');

INSERT INTO books (id, title, published_date, isbn, available, author_id, category_id, publisher_id)
VALUES (1, 'Harry Potter and the Philosophers Stone', '1997-06-26', '9780747532699', true, 1, 1, 1);
