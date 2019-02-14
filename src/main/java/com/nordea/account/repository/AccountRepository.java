package com.nordea.account.repository;

import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
    Account openAccount(Account account);

    Accountlist getAccounts();
}
