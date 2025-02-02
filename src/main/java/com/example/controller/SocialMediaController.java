package com.example.controller;
import org.springframework.http.ResponseEntity;
// Adding imports for necessary annotations
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.service.*;
import com.example.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;


    // Constructor method for Controller to initialize the Account and Message Service
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // Setting up handler for #1: processing new User registration
    
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        System.out.print("BEGINNING OF REGISTER METHOD");
        // If conditional that check if the Username is blank, or if the password is less that 4 characters, the minimum.
        // Returns status code 400, headers, and account in the body
        if (account.getUsername().isBlank() || account.getPassword().length() < 4){
            return ResponseEntity.status(400).body(null);
        }
        // If conditional that checks through AccountService if account username already exists, if yes
        // return status code 409, headers, and account in the body
        if (accountService.getAccountUsernameExists(account)){
            return ResponseEntity.status(409).body(null);
        }
        // try-catch block to try to register a new account given the information.
        // if a newAccount is not null i.e. a non-null value was returned, send newAccoutn info in ResponseEntity body with status 200
        try{
            System.out.print("WE GOT HERE IN TRY BLOCK");
            Account newAccount = accountService.registerAccount(account);
            if (newAccount != null){
                return ResponseEntity.status(200).body(newAccount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(500).body(null);
    }


}
