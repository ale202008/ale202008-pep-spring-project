package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
// Adding imports
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import java.util.Optional;

@Service
public class AccountService {
    // Initialize respository for Account bean handling
    private AccountRepository accountRespository;

    // Implementing constructor for service to initalize AccountRepository
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRespository = accountRepository;
    }

    // Method that uses the Optional import for JraRespository to find an Account object by username
    public Boolean getAccountUsernameExists(Account account){
        Optional<Account> acc = accountRespository.findByUsername(account.getUsername());
        return acc.isPresent();
    }

    // Implementing method to register new Account record to AccountRespository
    @Transactional
    public Account registerAccount(Account account){
        return accountRespository.save(account);
    }

    // Implemented a method to check if AccountRepository has an Account object
    public Account getAccount(Account account){
        return accountRespository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).get();
    }
    
}
