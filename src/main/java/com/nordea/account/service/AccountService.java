package com.nordea.account.service;

import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Accountlist getAccounts();

    Account createAccounts(Account account);
}
