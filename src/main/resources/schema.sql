CREATE TABLE book
(
  id BIGINT PRIMARY KEY,
  book_title VARCHAR2(250),
  genre VARCHAR2(250),
  page_count INTEGER
);

CREATE TABLE authors
(
  id BIGINT PRIMARY KEY,
  authors_names VARCHAR2(250),
  book_id BIGINT,
  FOREIGN KEY (book_id) REFERENCES book(id)
);
