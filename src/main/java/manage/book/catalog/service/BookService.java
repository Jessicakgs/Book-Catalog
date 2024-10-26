package manage.book.catalog.service;

import manage.book.catalog.model.Book;
import manage.book.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IsbnValidation isbnValidation;

    public Book createBook(Book book) {
        String isbn = book.getIsbn();

        if (!isbnValidation.isValid(isbn)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ISBN format.");
        }

        if (isbn.length() == 10) {
            isbn = isbnValidation.convertIsbn10ToIsbn13(isbn);
        }

        if (bookRepository.existsByIsbn(isbn)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A book with this ISBN already exists.");
        }

        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id " + id));
    }

    public Book updateBooks(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setYearPublication(bookDetails.getYearPublication());

                    String isbn = bookDetails.getIsbn();

                    if (!isbnValidation.isValid(isbn)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ISBN format.");
                    }

                    if (isbn.length() == 10) {
                        isbn = isbnValidation.convertIsbn10ToIsbn13(isbn);
                    }

                    if (!book.getIsbn().equals(isbn) && bookRepository.existsByIsbn(isbn)) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "A book with this ISBN already exists.");
                    }

                    book.setIsbn(isbn);
                    return bookRepository.save(book);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id " + id));
    }

    public void deleteBooks(Long id) {
        bookRepository.deleteById(id);
    }
}
