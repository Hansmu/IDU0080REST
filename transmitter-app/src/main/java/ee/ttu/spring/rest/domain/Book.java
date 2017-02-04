package ee.ttu.spring.rest.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name="authors", joinColumns=@JoinColumn(name="book_id"))
    private List<String> authorsNames;

    private String bookTitle;

    private String genre;

    private int pageCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAuthorsNames() {
        return authorsNames;
    }

    public void setAuthorsNames(List<String> authorsNames) {
        this.authorsNames = authorsNames;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
