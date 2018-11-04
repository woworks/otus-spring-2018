
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

-- ACL data

INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, 1, 'manager'),
  (2, 1, 'user'),
  (3, 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
  (1, 'ru.otus.hw21springacl.domain.Author');

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 3, 0),
  (2, 1, 2, NULL, 3, 0),
  (3, 1, 3, NULL, 3, 0),
  (4, 1, 4, NULL, 3, 0),
  (5, 1, 5, NULL, 3, 0);

INSERT INTO acl_entry
  (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 1, 1,    1, 1, 1),
  (2, 1, 2, 1, 2,    1, 1, 1),
  (3, 1, 3, 3, 1,    1, 1, 1),

  (4, 2, 1, 2, 1,    1, 1, 1),
  (5, 2, 2, 3, 1,    1, 1, 1),

  (6, 3, 1, 3, 1,    1, 1, 1),
  (7, 3, 2, 3, 2,    1, 1, 1),

  (8, 4, 1, 1, 1,    1, 1, 1),
  (9, 4, 2, 1, 2,    1, 1, 1),

  (10, 5, 1, 1, 1,   1, 1, 1),
  (11, 5, 2, 1, 2,   1, 1, 1);