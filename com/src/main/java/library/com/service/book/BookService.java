package library.com.service.book;

import jakarta.transaction.Transactional;
import library.com.domain.dtos.library.book.BookCreationDto;
import library.com.domain.dtos.library.book.BookResultDto;
import library.com.domain.dtos.library.book.BookUpdateDto;
import library.com.domain.entities.library.Book;
import library.com.domain.entities.library.Booking;
import library.com.domain.entities.library.Branch;
import library.com.domain.entities.user.User;
import library.com.exception.NotFoundException;
import library.com.repository.library.BookRepository;
import library.com.repository.library.BookingRepository;
import library.com.repository.library.BranchRepository;
import library.com.repository.user.UserRepository;
import library.com.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BookService implements IBookService{

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final BranchRepository branchRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public BookResultDto create(BookCreationDto bookCreationDto) {

        Book book = new Book();
        book.setNameOfBook(bookCreationDto.getNameOfBook());
        book.setTitle(bookCreationDto.getTitle());
        book.setAuthor(bookCreationDto.getAuthor());

        Branch branch = branchRepository.findById(bookCreationDto.getBranchId()).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );

        book.setBranch(branch);
        bookRepository.save(book);

        return modelMapper.map(book, BookResultDto.class);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book not found with given id")
        );
        bookRepository.delete(book);
    }

    @Override
    public BookResultDto update(Long id, BookUpdateDto bookUpdateDto) {

        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with given id not found")
        );

        Branch branch = branchRepository.findById(bookUpdateDto.getBranchId()).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );
        book.setBranch(branch);
        book.setNameOfBook(bookUpdateDto.getNameOfBook());
        book.setTitle(bookUpdateDto.getTitle());
        book.setAuthor(bookUpdateDto.getAuthor());

        return modelMapper.map(book, BookResultDto.class);
    }

    @Override
    public BookResultDto getById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with given id not found")
        );

        return modelMapper.map(book, BookResultDto.class);
    }

    @Override
    public List<BookResultDto> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookResultDto> bookResultDtos = books.stream()
                .map(book -> modelMapper.map(book, BookResultDto.class))
                .collect(Collectors.toList());
        return  bookResultDtos;
    }

    @Override
    public void transferBookToBranch(Long branchId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book with given id not found")
        );
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );

        book.setBranch(branch);
        bookRepository.save(book);
    }

    @Override
    public void transferBooksToBranch(Long branchId, List<Long> booksId) {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );

        List<Book> books = bookRepository.findAllById(booksId);

        books.forEach(book -> book.setBranch(branch));
        bookRepository.saveAll(books);
    }
    @Override
    public boolean isBookBusy(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book with given id not found")
        );
        List<Booking> activeBookings = bookingRepository.findByBookAndEndTimeAfter(book, LocalDateTime.now());
        return !activeBookings.isEmpty();
    }
    @Override
    public List<BookResultDto> getBooksUsedByStudent(Long studentId) {
        User user = userRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Student with given id not found")
        );
        List<Booking> bookings = bookingRepository.findByUser(user);
        List<Book> books = bookings.stream().map(Booking::getBook).collect(Collectors.toList());
        return books.stream()
                .map(book -> modelMapper.map(book, BookResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String orderBook(Long bookId, LocalDateTime startTime, LocalDateTime endTime) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book with given id not found")
        );

        if (isBookBusy(bookId)) {
            throw new NotFoundException("Book is busy. Please choose another book.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotFoundException("User is not authenticated or token is invalid");
        }

        String currentUserId = SecurityUtils.getCurrentUsername();
        System.out.println("Current User ID: " + currentUserId);


//
//        User user = userRepository.getUserByUserId(currentUserId).orElseThrow(
//                () -> new NotFoundException("User is Not valid")
//        );

        Booking booking = new Booking();
        booking.setBook(book);
//        booking.setUser(user);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);

        bookingRepository.save(booking);

        return "Book ordered successfully.";
    }

}