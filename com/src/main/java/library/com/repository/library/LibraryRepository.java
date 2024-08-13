package library.com.repository.library;

import library.com.domain.entities.library.Library;
import library.com.domain.entities.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository  extends JpaRepository<Library,Long> {
}
