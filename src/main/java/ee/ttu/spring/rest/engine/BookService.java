package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBook() {
        return bookRepository.findOne(1L);
    }
}
