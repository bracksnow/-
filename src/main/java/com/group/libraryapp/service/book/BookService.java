package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.Book.Book;
import com.group.libraryapp.domain.Book.BookRepository;
import com.group.libraryapp.domain.User.LoanHistory.UserLoanHistory;
import com.group.libraryapp.domain.User.LoanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.User.User;
import com.group.libraryapp.domain.User.UserRepository;
import com.group.libraryapp.dto.Book.requesst.BookCreateRequest;
import com.group.libraryapp.dto.Book.requesst.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;


    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));

    }
    @Transactional
    public void loanBook(BookLoanRequest request){
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException();
        }
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName(), false));

    }



}
