--Books table contents
INSERT INTO books (id, title) VALUES (1, 'War and peace') ON CONFLICT DO NOTHING;
INSERT INTO books (id, title) VALUES (2, 'Winnie-the-Pooh') ON CONFLICT DO NOTHING;
INSERT INTO books (id, title) VALUES (3, 'The War of the Worlds') ON CONFLICT DO NOTHING;
INSERT INTO books (id, title) VALUES (4, 'Three Men in a Boat (To Say Nothing of the Dog)') ON CONFLICT DO NOTHING;
INSERT INTO books (id, title) VALUES (5, 'Heart of a Dog') ON CONFLICT DO NOTHING;

--Genres table contents
INSERT INTO genres (id, name) VALUES (1, 'Satire') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) VALUES (2, 'Novel') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) VALUES (3, 'Historical novel') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) VALUES (4, 'Science fiction') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) VALUES (5, 'Children''s literature') ON CONFLICT DO NOTHING;
INSERT INTO genres (id, name) VALUES (6, 'Comedy') ON CONFLICT DO NOTHING;


--Authors table contents
INSERT INTO authors (id, name) VALUES (1, 'Leo Tolstoy') ON CONFLICT DO NOTHING;
INSERT INTO authors (id, name) VALUES (2, 'Alan Alexander Milne') ON CONFLICT DO NOTHING;
INSERT INTO authors (id, name) VALUES (3, 'Herbert George Wells') ON CONFLICT DO NOTHING;
INSERT INTO authors (id, name) VALUES (4, 'Jerome K. Jerome') ON CONFLICT DO NOTHING;
INSERT INTO authors (id, name) VALUES (5, 'Mikhail Bulgakov') ON CONFLICT DO NOTHING;

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