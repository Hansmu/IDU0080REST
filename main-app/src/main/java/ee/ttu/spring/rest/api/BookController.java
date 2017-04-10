package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.common.Result;
import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="{bookId}", method=RequestMethod.GET)
    public Result getBookById(@PathVariable(value="bookId")Long bookId) {
        return Result.ok(bookService.getBook(bookId));
    }

    @RequestMapping(value="get-all", method=RequestMethod.GET)
    public Result getAllBooks() {
        return Result.ok(bookService.getAllBooks());
    }

    @RequestMapping(value="get-ext-all", method=RequestMethod.GET)
    public Result getAllBooksFromTransmitterApplication() {
        return Result.ok(bookService.getAllBooksFromTransmitterApplication());
    }

    @RequestMapping(value="new", method=RequestMethod.PUT)
    public void createNewBook(@RequestBody Book newBook) {
        bookService.saveBook(newBook);
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public void editBook(@RequestBody Book bookToEdit) {
        bookService.saveBook(bookToEdit);
    }

    @RequestMapping(value="{bookId}", method=RequestMethod.DELETE)
    public void removeBook(@PathVariable(value="bookId")Long bookId) {
        bookService.removeBook(bookId);
    }

    @RequestMapping(value="findBy", method=RequestMethod.GET)
    public Result findBooksByFieldValue(@RequestParam(value="authorName", required=false) String authorName,
                                            @RequestParam(value="genre", required=false) String genre,
                                            @RequestParam(value="title", required=false) String title) {
        return Result.ok(bookService.findByParameter(authorName, genre, title));
    }

    @RequestMapping(value="order-books", method=RequestMethod.POST)
    public Result orderBooks(@RequestBody Map<Long, Integer> orderInfo) {
        bookService.orderBooks(orderInfo);
        return Result.ok(null);
    }

    @RequestMapping(value="order-cost", method=RequestMethod.GET)
    public Result getOrderCost() {
        return Result.ok(bookService.getOrderCost());
    }
}
