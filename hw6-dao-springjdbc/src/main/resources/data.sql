--Books table contents
INSERT INTO books (title) VALUES ('War and peace') ON CONFLICT DO NOTHING;
INSERT INTO books (title) VALUES ('Winnie-the-Pooh') ON CONFLICT DO NOTHING;
INSERT INTO books (title) VALUES ('The War of the Worlds') ON CONFLICT DO NOTHING;
INSERT INTO books (title) VALUES ('Three Men in a Boat (To Say Nothing of the Dog)') ON CONFLICT DO NOTHING;
INSERT INTO books (title) VALUES ('Heart of a Dog') ON CONFLICT DO NOTHING;

--Genres table contents
INSERT INTO genres (name) VALUES ('Satire') ON CONFLICT DO NOTHING;
INSERT INTO genres (name) VALUES ('Novel') ON CONFLICT DO NOTHING;
INSERT INTO genres (name) VALUES ('Historical novel') ON CONFLICT DO NOTHING;
INSERT INTO genres (name) VALUES ('Science fiction') ON CONFLICT DO NOTHING;
INSERT INTO genres (name) VALUES ('Children''s literature') ON CONFLICT DO NOTHING;
INSERT INTO genres (name) VALUES ('Comedy') ON CONFLICT DO NOTHING;


--Authors table contents
INSERT INTO authors (name) VALUES ('Leo Tolstoy') ON CONFLICT DO NOTHING;
INSERT INTO authors (name) VALUES ('Alan Alexander Milne') ON CONFLICT DO NOTHING;
INSERT INTO authors (name) VALUES ('Herbert George Wells') ON CONFLICT DO NOTHING;
INSERT INTO authors (name) VALUES ('Jerome K. Jerome') ON CONFLICT DO NOTHING;
INSERT INTO authors (name) VALUES ('Mikhail Bulgakov') ON CONFLICT DO NOTHING;

--Authors vs Genres table contents
INSERT INTO books_genres (book_id, genre_id) VALUES (1, 2) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (2, 5) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (3, 4) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (4, 6) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (5, 1) ON CONFLICT DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) VALUES (5, 4) ON CONFLICT DO NOTHING;

--Authors vs Authors table contents
INSERT INTO books_authors (book_id, author_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO books_authors (book_id, author_id) VALUES (2, 2) ON CONFLICT DO NOTHING;
INSERT INTO books_authors (book_id, author_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO books_authors (book_id, author_id) VALUES (4, 4) ON CONFLICT DO NOTHING;
INSERT INTO books_authors (book_id, author_id) VALUES (5, 5) ON CONFLICT DO NOTHING;