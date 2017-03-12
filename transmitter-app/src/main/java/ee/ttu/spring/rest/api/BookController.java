package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="book-ext")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="{bookId}", method=RequestMethod.GET)
    public Book getBookById(@PathVariable(value="bookId")Long bookId) {
        return bookService.getBook(bookId);
    }

    @RequestMapping(value="get-all", method=RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value="new", method=RequestMethod.POST)
    public void createNewBook(@RequestBody Book newBook) {
        bookService.saveNewBook(newBook);
    }
}
