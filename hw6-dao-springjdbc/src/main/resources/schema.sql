CREATE TABLE IF NOT EXISTS books (
  id    SERIAL PRIMARY KEY,
  title VARCHAR(450) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
  id    SERIAL PRIMARY KEY,
  name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS authors (
  id    SERIAL PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE books_genres (
  book_id    int REFERENCES books (id) ON UPDATE CASCADE ON DELETE CASCADE,
  genre_id   int REFERENCES genres (id) ON UPDATE CASCADE,
  CONSTRAINT book_genre_pkey PRIMARY KEY (book_id, genre_id)
);

CREATE TABLE books_authors (
  book_id    int REFERENCES books (id) ON UPDATE CASCADE ON DELETE CASCADE,
  author_id   int REFERENCES authors (id) ON UPDATE CASCADE,
  CONSTRAINT book_author_pkey PRIMARY KEY (book_id, author_id)
);

