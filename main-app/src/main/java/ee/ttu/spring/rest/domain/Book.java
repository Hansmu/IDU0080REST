package ee.ttu.spring.rest.domain;

import javax.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
public @Data class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name="authors", joinColumns=@JoinColumn(name="book_id"))
    private List<String> authorsNames;

    private String bookTitle;

    private String genre;

    private int pageCount;
}
