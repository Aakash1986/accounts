package com.nordea.account.service.impl;

import com.nordea.account.model.Account;
import com.nordea.account.model.AccountNumber;
import com.nordea.account.model.Accountlist;
import com.nordea.account.repository.AccountRepository;
import com.nordea.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Accountlist getAccounts() {
        return accountRepository.getAccounts();
    }

    public Account createAccounts(Account account) {
        account.setStatus("Open");
        account.setAvailableBalance("0");
        account.setBookedBalance("0");
        try {
            account.setAccountNumber(getAccountNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountRepository.OpenAccount(account);
    }

    private Long getAccountNumber() throws IOException {
        AccountNumber accountNumber = AccountNumber.getInstance();
        return accountNumber.getNextAccount();

    }
}
