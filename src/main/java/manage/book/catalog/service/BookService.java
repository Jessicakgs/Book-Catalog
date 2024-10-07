package manage.book.catalog.service;


import manage.book.catalog.model.BookModel;
import manage.book.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookModel createBook (BookModel book) {
        return bookRepository.save(book);

    }
}
