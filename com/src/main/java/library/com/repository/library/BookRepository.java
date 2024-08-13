package library.com.repository.library;

import library.com.domain.entities.library.Book;
import library.com.domain.entities.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long> {
}
