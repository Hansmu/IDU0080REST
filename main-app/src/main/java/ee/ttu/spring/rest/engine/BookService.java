package ee.ttu.spring.rest.engine;

import ee.ttu.spring.rest.domain.common.OrderInfo;
import ee.ttu.spring.rest.domain.common.Result;
import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.exception.exceptions.InvalidDataException;
import ee.ttu.spring.rest.repository.BookRepository;
import ee.ttu.spring.rest.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public Book getBook(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        Utils.validateBook(book);
        bookRepository.save(book);
    }

    public List<Book> getAllBooksFromTransmitterApplication() {
        RestTemplate restTemplate = new RestTemplate();
        Result<List<Book>> responseEntity = restTemplate.exchange("http://localhost:9500/book-ext/get-all", HttpMethod.GET, null, new ParameterizedTypeReference<Result<List<Book>>>() {}).getBody();
        List<Book> externalBooks = responseEntity.getData();

        return externalBooks;
    }

    public void removeBook(long bookId) {
        if (bookId < 0) {
            throw new InvalidDataException("Book ID cannot be less than 0.");
        }
        bookRepository.delete(bookId);
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

    public void orderBooks(List<Book> booksToOrder) {
        jmsTemplate.convertAndSend(OrderHandler.ORDER_CHECKER, booksToOrder);
    }

    public OrderInfo getOrderCost() {
        return (OrderInfo)jmsTemplate.receiveAndConvert(OrderHandler.ORDER_RECEIVER);
    }
}
