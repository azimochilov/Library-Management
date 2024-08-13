CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          book_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          start_time TIMESTAMP NOT NULL,
                          end_time TIMESTAMP NOT NULL,
                          CONSTRAINT fk_book
                              FOREIGN KEY(book_id)
                                  REFERENCES book(id),
                          CONSTRAINT fk_user
                              FOREIGN KEY(user_id)
                                  REFERENCES users(id)
);

CREATE INDEX idx_booking_book_id ON bookings(book_id);
CREATE INDEX idx_booking_user_id ON bookings(user_id);