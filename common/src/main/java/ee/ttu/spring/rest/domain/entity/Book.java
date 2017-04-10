package ee.ttu.spring.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String authorsNames;

    private String bookTitle;

    private String genre;

    private Integer pageCount;

    @JsonIgnore
    public List<String> getAuthorsNamesAsList() {
        return Arrays.asList(authorsNames.split(","));
    }
}
