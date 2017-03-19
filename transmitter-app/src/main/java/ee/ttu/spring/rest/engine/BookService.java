package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.repository.BookRepository;
import ee.ttu.spring.rest.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBook(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveNewBook(Book newBook) {
        Utils.validateBook(newBook);
        bookRepository.save(newBook);
    }
}
