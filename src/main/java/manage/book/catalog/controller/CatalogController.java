package manage.book.catalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import manage.book.catalog.model.BookModel;
import manage.book.catalog.repository.BookRepository;
import manage.book.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class CatalogController {

    @Autowired
    private BookService bookService;

    @PostMapping("/created")
    @Operation(summary = "Creating books " , description =  "Allows you to add a new book with title, author, publication year and ISBN")
    public BookModel createBook(@RequestBody BookModel book) {
        return bookService.createBook(book);
    }



}
