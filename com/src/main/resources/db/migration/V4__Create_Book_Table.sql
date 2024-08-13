

CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      book_id UUID NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      isbn VARCHAR(13) NOT NULL,
                      branch_id BIGINT NOT NULL,
                      CONSTRAINT unique_book_id UNIQUE (book_id),
                      CONSTRAINT fk_book_branch FOREIGN KEY (branch_id) REFERENCES branch (id)
);