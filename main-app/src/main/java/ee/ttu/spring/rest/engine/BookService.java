package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooksFromTransmitterApplication() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity("http://localhost:9500/book-ext/get-all", Book[].class);
        List<Book> externalBooks = Arrays.asList(responseEntity.getBody());

        return externalBooks;
    }

    public void removeBook(Book bookToRemove) {
        bookRepository.delete(bookToRemove);
    }

    public List<Book> findByParameter(String searchParameter, String parameterValue) {
        if (searchParameter.equals("genre")) {
            return bookRepository.findByGenre(parameterValue);
        } else if (searchParameter.equals("authorsNames")){
            return bookRepository.findByAuthorsNames(parameterValue);
        } else {
            return bookRepository.findByBookTitle(parameterValue);
        }
}
}
