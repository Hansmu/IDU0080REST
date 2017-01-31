package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="book")
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
}
