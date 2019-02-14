package com.nordea.test.account;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AccountTest {

  @Mock
  private AccountService accountService;

  private MockMvc mockMVC;

  private Validator validator;

  @InjectMocks
  AccountController accountController;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    this.mockMVC= MockMvcBuilders.standaloneSetup(accountController).build();
  }

  @Test
  public void testCreateAccountSuccess() throws Exception {
    //to test happy path
    when(accountService.createAccounts(ArgumentMatchers.any(Account.class))).thenReturn(getcreateAccount());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mockMVC.perform(MockMvcRequestBuilders.post("/v1/accounts")
      .content(mapper.writeValueAsString(getcreateAccount()))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated());
  }

  @Test
  public void testListAccountsSuccess() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    when(accountService.getAccounts()).thenReturn(getAccountList());
    mockMVC.perform(MockMvcRequestBuilders.get("/v1/accounts"))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(getAccountList()), false));
  }

  @Test
  public void testListAccounts_404() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    when(accountService.getAccounts()).thenReturn(getAccountList());
    mockMVC.perform(MockMvcRequestBuilders.get("/v1/account"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testAccountOK() throws Exception {
    //to test happy path
    Set<ConstraintViolation<Account>> violations = validator.validate(getcreateAccount());
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testAccountNotOK() throws Exception {
    //to test happy path
    Set<ConstraintViolation<Account>> violations = validator.validate(getcreateAccountNotOK());
    assertFalse(violations.isEmpty());
  }

  @Test
  public void testAccount_NotOK() throws Exception {
    //to test happy path
    Set<ConstraintViolation<Account>> violations = validator.validate(getcreateAccountNotOK());
    assertTrue(violations.size()==2);
  }

  private Account getcreateAccount() {
    Account account = new Account();
    account.setAccountName("Brukskonto");
    account.setAccountType("DEPOSIT");
    account.setAccountNickname("Min Brukskonto");
    account.setAccountOwnername("Ola Nordmann");
    account.setCurrency("NOK");
    return account ;
  }

  private Account getcreateAccountNotOK() {
    Account account = new Account();
    account.setAccountName("Brukskonto1");
    account.setAccountType("DEPOSIT1");
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
