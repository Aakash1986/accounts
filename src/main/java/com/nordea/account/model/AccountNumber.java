package com.nordea.account.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Scope(value = "Singleton")
public class AccountNumber {

    final static Logger logger = Logger.getLogger(AccountNumber.class);

    private static AccountNumber accountNumber;

    private static final String PATH= "/data/accounts/accounts.json";

    private static AtomicLong at = new AtomicLong(0);

    private AccountNumber(){}


    static{
        try{
            accountNumber = new AccountNumber();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static AccountNumber getInstance(){
        return accountNumber;
    }

    public Long getNextAccount(){
        Long temp;

        if(at.intValue()==0) {
            at.set(getMaxAccountNumber());
            temp= at.incrementAndGet();
            return temp;
        }
        else {
            logger.debug("Already have assigned value");
            temp =at.incrementAndGet();
            return temp;
        }
    }
    private Long getMaxAccountNumber(){
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accountList = null;
        try {
            System.out.println(this.getClass().getResource(PATH).getPath());
            accountList = mapper.readValue(new File(this.getClass().getResource(PATH).getPath()), new TypeReference<List<Account>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account acc = accountList.stream()
                .max(Comparator.comparing(Account::getAccountNumber))
                .orElseThrow(NoSuchElementException::new);
        logger.info("Account Number Returned is "+ acc.getAccountNumber());
        return acc.getAccountNumber();
    }

}

