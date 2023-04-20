package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.Book.Book;
import com.group.libraryapp.domain.Book.BookRepository;
import com.group.libraryapp.dto.Book.requesst.BookCreateRequest;
import com.group.libraryapp.repository.book.BookRepositoryV2;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));

    }

}
