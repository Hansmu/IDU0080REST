package ee.ttu.spring.rest.repository;

import ee.ttu.spring.rest.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b " +
            "FROM Book b " +
            "WHERE lower(b.bookTitle) LIKE concat('%', lower(:title), '%') " +
            "OR lower(b.bookTitle) LIKE concat('%', lower(:title)) " +
            "OR lower(b.bookTitle) LIKE concat(lower(:title), '%')")
    List<Book> findByBookTitle(@Param(value="title")String bookTitle);

    @Query("SELECT b " +
            "FROM Book b " +
            "WHERE lower(b.genre) LIKE concat('%', lower(:genre), '%') " +
            "OR lower(b.genre) LIKE concat('%', lower(:genre)) " +
            "OR lower(b.genre) LIKE concat(lower(:genre), '%')")
    List<Book> findByGenre(@Param(value="genre")String genre);

    @Query("SELECT b " +
            "FROM Book b " +
            "WHERE lower(b.authorsNames) LIKE concat('%', lower(:authorsNames), '%') " +
            "OR lower(b.authorsNames) LIKE concat('%', lower(:authorsNames)) " +
            "OR lower(b.authorsNames) LIKE concat(lower(:authorsNames), '%')")
    List<Book> findByAuthorsNames(@Param(value="authorsNames")String authorsNames);

}
