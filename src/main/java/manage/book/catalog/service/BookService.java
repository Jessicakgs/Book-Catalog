package manage.book.catalog.service;


import manage.book.catalog.model.Book;
import manage.book.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook (Book book) {
        return bookRepository.save(book);

    }

    public List<Book> listBooks () {
        return bookRepository.findAll();
    }
}
