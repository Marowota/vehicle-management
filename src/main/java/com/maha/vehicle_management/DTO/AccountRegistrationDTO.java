package com.maha.vehicle_management.DTO;

import com.maha.vehicle_management.Entities.Account;
import com.maha.vehicle_management.Models.enums.AccountRegistrationResult;

public class AccountRegistrationDTO {
    AccountDTO account;
    AccountRegistrationResult result;

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public AccountRegistrationResult getResult() {
        return result;
    }

    public void setResult(AccountRegistrationResult result) {
        this.result = result;
    }
}
