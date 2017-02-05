package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.Book;
import ee.ttu.spring.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Book> findByParameter(String authorName, String genre, String title) {
        if (isParameterProvided(authorName)) {
            return getBooksByAuthorNameGenreAndTitle(authorName, genre, title);
        } else if (isParameterProvided(genre)) {
            return getBooksByGenreAndTitle(genre, title);
        } else if (isParameterProvided(title)) {
            return bookRepository.findByBookTitle(title);
        }

        return bookRepository.findAll();
    }

    private boolean isParameterProvided(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

    private boolean isSearchParameterInSearchField(String searchValue, String fieldToBeSearched) {
        boolean isTrueToNotFilterOutValuesWhenParameterNull = true;

        if (isParameterProvided(searchValue)) {
            return fieldToBeSearched.toLowerCase().contains(searchValue.toLowerCase());
        }

        return isTrueToNotFilterOutValuesWhenParameterNull;
    }

    private List<Book> getBooksByAuthorNameGenreAndTitle(String authorName, String genre, String title) {
        return bookRepository.findByAuthorsNames(authorName).stream()
                .filter(book -> isSearchParameterInSearchField(genre, book.getGenre()))
                .filter(book -> isSearchParameterInSearchField(title, book.getBookTitle()))
                .collect(Collectors.toList());
    }

    private List<Book> getBooksByGenreAndTitle(String genre, String title) {
        return bookRepository.findByGenre(genre).stream()
                .filter(book -> isSearchParameterInSearchField(title, book.getBookTitle()))
                .collect(Collectors.toList());
    }
}
