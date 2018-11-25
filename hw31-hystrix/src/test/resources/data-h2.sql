
--Genres table contents
INSERT INTO genres (id, name) VALUES (default, 'Satire');
INSERT INTO genres (id, name) VALUES (default, 'Novel');
INSERT INTO genres (id, name) VALUES (default, 'Historical novel');
INSERT INTO genres (id, name) VALUES (default, 'Science fiction');
INSERT INTO genres (id, name) VALUES (default, 'Children''s literature');
INSERT INTO genres (id, name) VALUES (default, 'Comedy');

--Authors table contents
INSERT INTO authors (id, name) VALUES (default, 'Leo Tolstoy');
INSERT INTO authors (id, name) VALUES (default, 'Alan Alexander Milne');
INSERT INTO authors (id, name) VALUES (default, 'Herbert George Wells');
INSERT INTO authors (id, name) VALUES (default, 'Jerome K. Jerome');
INSERT INTO authors (id, name) VALUES (default, 'Mikhail Bulgakov');

--Books table contents
INSERT INTO books (title, author_id) VALUES ('War and peace', 1);
INSERT INTO books (title, author_id) VALUES ('Winnie-the-Pooh', 2);
INSERT INTO books (title, author_id) VALUES ('The War of the Worlds', 3);
INSERT INTO books (title, author_id) VALUES ('Three Men in a Boat', 4);
INSERT INTO books (title, author_id) VALUES ('Heart of a Dog', 5);

--Authors vs Genres table contents
INSERT INTO books_genres (book_id, genres_id) VALUES (1, 2);
INSERT INTO books_genres (book_id, genres_id) VALUES (1, 3);
INSERT INTO books_genres (book_id, genres_id) VALUES (2, 5);
INSERT INTO books_genres (book_id, genres_id) VALUES (3, 4);
INSERT INTO books_genres (book_id, genres_id) VALUES (4, 6);
INSERT INTO books_genres (book_id, genres_id) VALUES (5, 1);
INSERT INTO books_genres (book_id, genres_id) VALUES (5, 4);
