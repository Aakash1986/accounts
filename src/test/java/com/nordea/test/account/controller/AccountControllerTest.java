package com.nordea.test.account.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordea.account.controller.AccountController;
import com.nordea.account.model.Account;
import com.nordea.account.model.Accountlist;
import com.nordea.account.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AccountControllerTest {

  @Mock
  private AccountService accountService;

  private MockMvc mockMVC;

  @InjectMocks
  AccountController accountController;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    this.mockMVC= MockMvcBuilders.standaloneSetup(accountController).build();
  }

  @Test
  public void testCreateAccount() throws Exception {
    //to test happy path
    when(accountService.createAccounts(ArgumentMatchers.any(Account.class))).thenReturn(getcreateAccount());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mockMVC.perform(MockMvcRequestBuilders.post("/v1/accounts")
      .content(mapper.writeValueAsString(getcreateAccount()))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  public void testListAccounts() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    when(accountService.getAccounts()).thenReturn(getAccountList());
    mockMVC.perform(MockMvcRequestBuilders.get("/v1/accounts"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(getAccountList()), false));
  }

  private Account getcreateAccount() {
    Account account = new Account();
    account.setAccountName("Brukskonto1");
    account.setAccountType("DEPOSIT");
    account.setAccountNickname("Min Brukskonto");
    account.setAccountOwnername("Ola Nordmann");
    account.setCurrency("NOK");
    return account ;
  }


  private Accountlist getAccountList() {
    Accountlist accountDetails = new Accountlist();
    Account acc = new Account();
    List<Account> accList = new ArrayList<Account>();
    accList.add(acc);
    accountDetails.setAccounts(accList);
    return accountDetails;
  }
}
