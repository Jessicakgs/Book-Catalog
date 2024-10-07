package manage.book.catalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import manage.book.catalog.model.Book;
import manage.book.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class CatalogController {

    @Autowired
    private BookService bookService;

    @PostMapping("/created")
    @Operation(summary = "Creating books " , description =  "Allows you to add a new book with title, author, publication year and ISBN")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    @Operation(summary = "Listing books" , description = "A list in GameS format of all the books.")
    public List<Book> listBook(@RequestBody Book book) {
        return bookService.listBooks();
    }


}
