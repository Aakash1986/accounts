package com.nordea.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nordea.account.validation.ValidateStringValue;

import javax.validation.constraints.NotNull;

public class Account {
    public Account(){
    }

    public Account(String accountName, String accountNickname, String accountOwnername, String accountType, String currency){
        this.accountName = accountName;
        this.accountNickname = accountNickname;
        this.accountOwnername = accountOwnername;
        this.accountType = accountType;
        this.currency = currency;
    }


    @JsonProperty("account_number")
    private long accountNumber;

    @JsonProperty("account_name")
    @NotNull
    @ValidateStringValue(acceptedValues = {"Min Brukskonto","Min Sparekonto","Valutakonto", "Brukskonto", "Sparekonto"}, message = "Account Name is Invalid")
    private String accountName;

    @JsonProperty("account_nickname")
    private String accountNickname;

    @JsonProperty("account_owner_name")
    @NotNull
    private String accountOwnername;

    @JsonProperty("account_type")
    @NotNull
    @ValidateStringValue(acceptedValues = {"SAVING","CURRENT","DEPOSIT"}, message = "Account Type is Invalid")
    private String accountType;

    @JsonProperty("currency")
    @NotNull
    @ValidateStringValue(acceptedValues = {"NOK","INR","EUR","USD","DNK"}, message = "Currency is Invalid")
    private String currency;

    @JsonProperty("available_balance")
    private String availableBalance;

    @JsonProperty("booked_balance")
    private String bookedBalance;

    @JsonProperty("status")
    private String status;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNickname() {
        return accountNickname;
    }

    public void setAccountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
    }

    public String getAccountOwnername() {
        return accountOwnername;
    }

    public void setAccountOwnername(String accountOwnername) {
        this.accountOwnername = accountOwnername;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getBookedBalance() {
        return bookedBalance;
    }

    public void setBookedBalance(String bookedBalance) {
        this.bookedBalance = bookedBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
