package manage.book.catalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import manage.book.catalog.model.Book;
import manage.book.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class CatalogController {

    @Autowired
    private BookService bookService;

    @PostMapping("/created")
    @Operation(summary = "Creating books ", description = "Allows you to add a new book with title, author, publication year and ISBN")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    @Operation(summary = "Listing books", description = "A list in GameS format of all the books.")
    public List<Book> listAllBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search books by id", description = "Returns the details of a specific book based on its ID.")
    public Book listBook(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PutMapping("/updated/{id}")
    @Operation(summary = "Update books", description = "Allows you to update information in an existing book.")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.updateBooks(id, bookDetails);
    }

}
