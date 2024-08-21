package library.com.api.book;

import library.com.domain.dtos.library.book.*;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.dtos.library.order.OrderRequest;
import library.com.domain.entities.library.Booking;
import library.com.service.book.BookService;
import library.com.service.branch.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        return "books/books_list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        BookResultDto book = bookService.getById(id);
        model.addAttribute("book", book);
        return "books/book_details";
    }

    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("bookDto", new BookCreationDto());
        model.addAttribute("branches", branchService.getAll());
        model.addAttribute("formTitle", "Create Book");
        return "books/book_form";
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
        model.addAttribute("bookDto", updateDto);
        model.addAttribute("branches", branchService.getAll()); // Populate branches dropdown
        model.addAttribute("formTitle", "Update Book");
        return "books/book_form";
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
        return "books/transfer_book";
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
        return "books/transfer_books";
    }

    @PostMapping("/transferMultiple")
    public String transferBooks(@RequestParam Long branchId, @RequestParam List<Long> booksId) {
        bookService.transferBooksToBranch(branchId, booksId);
        return "redirect:/books";
    }



    @GetMapping("/order")
    public String orderBookForm(Model model) {
        List<BookResultDto> books = bookService.getAll();
        model.addAttribute("books", books);
        model.addAttribute("formTitle", "Order Book");
        return "books/order_book";
    }



    @PostMapping("/order")
    public String orderBook(@RequestBody OrderRequest orderRequest, RedirectAttributes redirectAttributes) {
        Booking booking = bookService.orderBook(orderRequest.getBookId(), orderRequest.getStartTime(), orderRequest.getEndTime());

        redirectAttributes.addFlashAttribute("orderId", booking.getId());
        redirectAttributes.addFlashAttribute("orderedBy", booking.getUser().getUsername());
        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");

        return "redirect:/books/order/result";
    }

    @GetMapping("/student")
    public String getBooksUsedByStudent(Model model) {

        return "books/student_books";
    }

    @PostMapping("/student")
    public String postBooksUsedByStudent(@RequestParam Long studentId, Model model) {
        List<BookResultDto> books = bookService.getBooksUsedByStudent(studentId);
        model.addAttribute("books", books);
        model.addAttribute("studentId", studentId);
        return "books/student_books";
    }
    @GetMapping("/order/result")
    public String orderResult(@RequestParam(value = "message", required = false) String message,
                              @RequestParam(value = "orderId", required = false) Long orderId,
                              @RequestParam(value = "orderedBy", required = false) String orderedBy,
                              Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderedBy", orderedBy);
        model.addAttribute("message", message);

        return "books/order_book_result";
    }





}