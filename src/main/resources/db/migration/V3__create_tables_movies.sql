CREATE TABLE movie (
    id serial PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE movie_category (
    movie_id INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_movie_category_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE movie_streaming (
    movie_id INTEGER,
    streaming_id INTEGER,
    CONSTRAINT fk_movie_streaming_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY(streaming_id) REFERENCES streaming(id)
);