package manage.book.catalog.repository;

import manage.book.catalog.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
