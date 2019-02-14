package com.nordea.account.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import com.nordea.account.repository.AccountRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger logger = Logger.getLogger(AccountRepositoryImpl.class);

    private static final String PATH= "/data/accounts/accounts.json";


    public Account openAccount(Account account) {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList;
        try {
            logger.debug("Account List Path :"+this.getClass().getResource(PATH).getPath());
            accountList = mapper.readValue(new File(this.getClass().getResource(PATH).getPath()), new TypeReference<List<Account>>(){});
            accountList.add(account);
            mapper.writeValue(new File(this.getClass().getResource(PATH).getPath()), accountList);
        } catch (IOException e) {
            logger.error("Error thrown while fetching Account List", e);
        }
        logger.debug("Account Returned");
        return account;
    }

    public Accountlist getAccounts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList = new ArrayList<>();
        Accountlist accountlist=new Accountlist();
        try {
            logger.debug("Account List Path :"+this.getClass().getResource(PATH).getPath());
            accountList = mapper.readValue(new File(this.getClass().getResource(PATH).getPath()), new TypeReference<List<Account>>(){});
        } catch (IOException e) {
            logger.error("Error thrown while fetching Account List", e);
        }
        accountlist.setAccounts(accountList);
        return accountlist;
    }
}
