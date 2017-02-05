package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="get-ext-all", method=RequestMethod.GET)
    public List<Book> getAllBooksFromTransmitterApplication() {
        return bookService.getAllBooksFromTransmitterApplication();
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
    public void removeBook(@RequestBody Book bookToRemove) {
        bookService.removeBook(bookToRemove);
    }

    @RequestMapping(value="findBy/{searchParameter}/{parameterValue}", method=RequestMethod.GET)
    public List<Book> findBooksByFieldValue(@PathVariable(value="searchParameter") String searchParameter,
                                            @PathVariable(value="parameterValue") String parameterValue) {
        return bookService.findByParameter(searchParameter, parameterValue);
    }
}
