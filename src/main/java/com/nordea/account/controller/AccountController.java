package com.nordea.account.controller;


import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import com.nordea.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("v1/accounts")
public class AccountController {

   @Autowired
    private AccountService accountService;

    @Autowired
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Accountlist getAccounts(){
        return accountService.getAccounts();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Account creatAccounts(@RequestBody @Valid Account account){
        return accountService.createAccounts(account);
    }

}
