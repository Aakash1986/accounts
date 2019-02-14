package com.nordea.account.repository;

import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {
    Account OpenAccount(Account account);

    Accountlist getAccounts();
}
