package com.group.libraryapp.Controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
