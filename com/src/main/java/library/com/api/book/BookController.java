package library.com.api.book;

import library.com.domain.dtos.library.book.BookCreationDto;
import library.com.domain.dtos.library.book.BookResultDto;
import library.com.domain.dtos.library.book.BookUpdateDto;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.entities.library.Book;
import library.com.domain.entities.library.Branch;
import library.com.service.book.BookService;
import library.com.service.branch.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BranchService branchService;

    @GetMapping
    public String listBooks(Model model) {
        List<BookResultDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books_list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        BookResultDto book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book_details";
    }

    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("bookDto", new BookCreationDto());
        model.addAttribute("branches", branchService.getAll()); // Populate branches dropdown
        model.addAttribute("formTitle", "Create Book");
        return "book_form";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookCreationDto bookDto) {
        bookService.create(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        BookResultDto book = bookService.getById(id);
        BookUpdateDto updateDto = new BookUpdateDto();
        updateDto.setId(book.getId());
        updateDto.setNameOfBook(book.getNameOfBook());
        updateDto.setTitle(book.getTitle());
        updateDto.setAuthor(book.getAuthor());
        //updateDto.setBranchId();
        model.addAttribute("bookDto", updateDto);
        model.addAttribute("branches", branchService.getAll()); // Populate branches dropdown
        model.addAttribute("formTitle", "Update Book");
        return "book_form";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookUpdateDto bookDto) {
        bookService.update(bookDto.getId(), bookDto);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/transfer")
    public String transferBookForm(Model model) {
        List<BranchResultDto> branches = branchService.getAll();
        List<BookResultDto> books = bookService.getAll(); // Adjust this as needed to fetch the list of books
        model.addAttribute("branches", branches);
        model.addAttribute("books", books);
        model.addAttribute("formTitle", "Transfer Book to Branch");
        return "transfer_book";
    }

    @PostMapping("/transfer")
    public String transferBook(@RequestParam Long branchId, @RequestParam Long bookId) {
        bookService.transferBookToBranch(branchId, bookId);
        return "redirect:/books";
    }

    @GetMapping("/transferMultiple")
    public String transferBooksForm(Model model) {
        List<BranchResultDto> branches = branchService.getAll();
        List<BookResultDto> books = bookService.getAll();
        model.addAttribute("branches", branches);
        model.addAttribute("books", books);
        model.addAttribute("formTitle", "Transfer Multiple Books to Branch");
        return "transfer_books";
    }

    @PostMapping("/transferMultiple")
    public String transferBooks(@RequestParam Long branchId, @RequestParam List<Long> booksId) {
        bookService.transferBooksToBranch(branchId, booksId);
        return "redirect:/books";
    }

    @GetMapping("/isBusy/{id}")
    public String checkIfBookBusy(@PathVariable Long id, Model model) {
        boolean isBusy = bookService.isBookBusy(id);
        model.addAttribute("bookId", id);
        model.addAttribute("isBusy", isBusy);
        return "book_status";
    }

    @GetMapping("/booksUsedByStudent/{id}")
    public String getBooksUsedByStudent(@PathVariable Long id, Model model) {
        List<BookResultDto> books = bookService.getBooksUsedByStudent(id);
        model.addAttribute("books", books);
        return "books_used_by_student";
    }
    @GetMapping("/order")
    public String orderBookForm(Model model) {
        List<BookResultDto> books = bookService.getAll();
        model.addAttribute("books", books);
        model.addAttribute("formTitle", "Order Book");
        return "order_book";
    }

    @PostMapping("/order")
    public String orderBook(@RequestParam Long bookId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        bookService.orderBook(bookId, startTime, endTime);
        return "redirect:/books";
    }

//
//    @GetMapping("/order/{id}")
//    public String showOrderForm(@PathVariable("id") Long bookId, Model model) {
//        model.addAttribute("bookId", bookId);
//        return "order_book";
//    }
//
//    @PostMapping("/order")
//    public String orderBook(@RequestParam Long bookId,
//                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
//                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
//                            Model model) {
//        try {
//            String message = bookService.orderBook(bookId, startTime, endTime);
//            model.addAttribute("successMessage", message);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//        }
//        return "redirect:/orderBookResult";
//    }


}
