package library.com.repository.library;

import library.com.domain.entities.library.Book;
import library.com.domain.entities.library.Booking;
import library.com.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookAndEndTimeAfter(Book book, LocalDateTime now);
    List<Booking> findByUser(User user);
}
