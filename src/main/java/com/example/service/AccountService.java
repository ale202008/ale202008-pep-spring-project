package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
// Adding imports
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;

@Service
public class AccountService {
    // Initialize respository for Account bean handling
    private AccountRepository AccountRespository;

    // Implementing constructor for service to initalize AccountRepository
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.AccountRespository = accountRepository;
    }

    
}
