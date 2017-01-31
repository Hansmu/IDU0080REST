package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.engine.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public Book test() {
        return bookService.getBook();
    }
}
