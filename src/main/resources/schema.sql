CREATE TABLE book
(
  id NUMBER(10, 0) PRIMARY KEY,
  book_title VARCHAR2(250),
  genre VARCHAR2(250),
  page_count NUMBER(4, 0)
);

CREATE TABLE authors
(
  id NUMBER(10, 0) PRIMARY KEY,
  names VARCHAR2(250),
  book_id NUMBER(10, 0),
  FOREIGN KEY (book_id) REFERENCES book(id)
);
