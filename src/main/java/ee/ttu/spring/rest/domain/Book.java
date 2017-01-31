package ee.ttu.spring.rest.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private List<String> authorsNames;

    @Column
    private String bookTitle;

    @Column
    private String genre;

    @Column
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
