MERGE INTO book (id, book_title, genre, page_count) VALUES (1, 'Greatest Book', 'Mystery', 273);
MERGE INTO book (id, book_title, genre, page_count) VALUES (2, 'Also Good Book', 'Adventure', 353);
MERGE INTO authors (id, authors_names, book_id) VALUES (1, 'Bob Roberts', 1);
MERGE INTO authors (id, authors_names, book_id) VALUES (2, 'Michael J. Flackson', 1);
MERGE INTO authors (id, authors_names, book_id) VALUES (3, 'John J. Johnathan', 2);
