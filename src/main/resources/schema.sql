CREATE TABLE book
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  book_title VARCHAR2(250),
  genre VARCHAR2(250),
  page_count INTEGER
);

CREATE TABLE authors
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  authors_names VARCHAR2(250),
  book_id BIGINT,
  FOREIGN KEY (book_id) REFERENCES book(id)
);
