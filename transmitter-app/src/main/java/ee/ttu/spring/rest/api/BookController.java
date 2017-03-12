package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.common.Result;
import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="book-ext")
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

    @RequestMapping(value="new", method=RequestMethod.POST)
    public void createNewBook(@RequestBody Book newBook) {
        bookService.saveNewBook(newBook);
    }
}
