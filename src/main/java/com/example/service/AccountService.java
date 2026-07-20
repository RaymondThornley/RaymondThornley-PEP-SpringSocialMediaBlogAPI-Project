package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
  
    public Account registerUser(Account newAccount) {
      if (newAccount.getUsername().length() == 0) {
          return null;
      }
      if (newAccount.getPassword().length() < 4) {
          return null;
      }
      
      Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
      if (existingAccount != null) {
          return null;
      }
      return accountRepository.save(newAccount);
    }

  public Account loginUser(Account loginAccount) {
      Account existingAccount = accountRepository.findByUsername(loginAccount.getUsername());
      if (existingAccount == null) {
          return null;
      }
      if (existingAccount.getPassword().equals(loginAccount.getPassword())) {
          return existingAccount;
      }
      return null;
    }
}
