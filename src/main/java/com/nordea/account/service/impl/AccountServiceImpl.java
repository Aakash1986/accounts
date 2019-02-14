package com.nordea.account.service.impl;

import com.nordea.account.model.Account;
import com.nordea.account.model.AccountNumber;
import com.nordea.account.model.Accountlist;
import com.nordea.account.repository.AccountRepository;
import com.nordea.account.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    public Accountlist getAccounts() {
        return accountRepository.getAccounts();
    }

    public Account createAccounts(Account account) {
        account.setStatus("Open");
        if(account.getAccountNickname().isEmpty())
            account.setAccountNickname(account.getAccountName());
        account.setAvailableBalance("0");
        account.setBookedBalance("0");
        account.setAccountNumber(getAccountNumber());
        return accountRepository.openAccount(account);
    }

    private Long getAccountNumber(){
        AccountNumber accountNumber = AccountNumber.getInstance();
        logger.debug("Account Number is "+ accountNumber.getNextAccount());
        return accountNumber.getNextAccount();

    }
}
