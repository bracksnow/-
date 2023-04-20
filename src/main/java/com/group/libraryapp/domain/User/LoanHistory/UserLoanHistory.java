package com.group.libraryapp.domain.User.LoanHistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private String bookName;

    private boolean isReturn;

    public UserLoanHistory() {
    }

    public UserLoanHistory(Long id, String name, boolean isReturn) {
        this.id = id;
        this.bookName = name;
        this.isReturn = isReturn;
    }
}
