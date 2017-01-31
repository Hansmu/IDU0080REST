package ee.ttu.spring.rest.repository;

import ee.ttu.spring.rest.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Long>{

}
