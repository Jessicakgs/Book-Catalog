package manage.book.catalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import manage.book.catalog.model.Book;
import manage.book.catalog.service.BookService;
import manage.book.catalog.service.IsbnValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class CatalogController {

    @Autowired
    private BookService bookService;

    @Autowired
    private IsbnValidation isbnValidation;

    @PostMapping("/created")
    @Operation(summary = "Create books", description = "Allows you to add a new book with title, author, publication year and ISBN")
    public Book createBook(@RequestBody Book book) {

        if (!isbnValidation.isValid(book.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ISBN: " + book.getIsbn());
        }

        return bookService.createBook(book);
    }

    @GetMapping
    @Operation(summary = "List books", description = "Returns a list in JSON format of all the books.")
    public List<Book> listAllBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "Returns the details of a specific book based on its ID.")
    public Book listBook(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update books", description = "Allows you to update information in an existing book.")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {

        if (!isbnValidation.isValid(bookDetails.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ISBN: " + bookDetails.getIsbn());
        }

        return bookService.updateBooks(id, bookDetails);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete books", description = "Removes a book from the catalog by its ID.")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBooks(id);
    }
}
