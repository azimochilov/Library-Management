package library.com.service.book;

import library.com.domain.dtos.library.book.BookCreationDto;
import library.com.domain.dtos.library.book.BookResultDto;
import library.com.domain.dtos.library.book.BookUpdateDto;
import library.com.domain.entities.library.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookService {

    BookResultDto create(BookCreationDto bookCreationDto);
    void delete(Long id);
    BookResultDto update(Long id, BookUpdateDto bookUpdateDto);
    BookResultDto getById(Long id);
    List<BookResultDto> getAll();
    void transferBookToBranch(Long branchId, Long bookId);
    void transferBooksToBranch(Long branchId, List<Long> booksId);
    List<BookResultDto> getBooksUsedByStudent(Long studentId);
    Booking orderBook(Long bookId, LocalDateTime startTime, LocalDateTime endTime);
}
