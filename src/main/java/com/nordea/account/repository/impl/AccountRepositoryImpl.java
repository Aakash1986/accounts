package com.nordea.account.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import com.nordea.account.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private static final String PATH= "/data/accounts/accounts.json";


    public Account OpenAccount(Account account) {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList = null;
        try {
            System.out.println(this.getClass().getResource(PATH).getPath());
            accountList = mapper.readValue(new File(this.getClass().getResource(PATH).getPath()), new TypeReference<List<Account>>(){});
            accountList.add(account);
            mapper.writeValue(new File(this.getClass().getResource(PATH).getPath()), accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Account Returned");
        return account;
    }

    public Accountlist getAccounts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList = null;
        Accountlist accountlist=new Accountlist();
        try {
            System.out.println(this.getClass().getResource(PATH).getPath());
            accountList = mapper.readValue(new File(this.getClass().getResource(PATH).getPath()), new TypeReference<List<Account>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        accountlist.setAccounts(accountList);
        return accountlist;
    }
}
