CREATE TABLE branch (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        library_id BIGINT NOT NULL,
                        CONSTRAINT fk_branch_library FOREIGN KEY (library_id) REFERENCES library (id)
);