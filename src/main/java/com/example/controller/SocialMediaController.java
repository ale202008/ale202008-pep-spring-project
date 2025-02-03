package com.example.controller;
// Adding imports for necessary annotations
import org.springframework.web.bind.annotation.*;
import com.example.service.*;
import com.example.entity.*;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
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

    /* REQUEST MAPPINGS AND HANDLERS FOR ACCOUNT START */


    // Setting up handler for #1: processing new User registration POST
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

        // Return a 500 status code indicating an error on the server-side
        return ResponseEntity.status(500).body(null);
    }


    // Implementing handler for /login POST
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        try {
            Account loginAccount = accountService.getAccountByUsernameAndLogin(account);
            if (loginAccount != null){
                return ResponseEntity.status(200).body(loginAccount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // Return status 401 if no account could be found given the information
        return ResponseEntity.status(401).body(null);
    }


    /* REQUEST MAPPINGS AND HANDLERS FOR ACCOUNT END */
    /* REQUEST MAPPINGS AND HANDLERS FOR MESSAGES START */


    // Implemented handler for /messages POST
    @PostMapping("/messages")
    public ResponseEntity<Message> message(@RequestBody Message message){
        if ((message.getMessageText().isBlank()) || (message.getMessageText().length()) > 255 || (accountService.getAccountById(message.getPostedBy()) == null)){
            return ResponseEntity.status(400).body(null);
        }

        // try-block to attempt in creating a new message after if conditionals
        try{
            Message newMessage = messageService.createMessage(message);
            if (newMessage != null){
                return ResponseEntity.status(200).body(newMessage);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(400).body(null);
    }


    // Implemented handler for /messages GET
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }


    // Implemented handler for /messages/{messageId} GET
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
        return ResponseEntity.status(200).body(messageService.getMessageByid(messageId));
    }


    // Implemented handler for /messages/{messageId} DELETE
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        if (messageService.messageExistsById(messageId)){
            return ResponseEntity.status(200).body(messageService.deleteMessageByid(messageId));
        }
        else {
            return ResponseEntity.status(200).body(null);
        }

    }


    // Implemented handler for /messages/{messageId} PATCH
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@RequestBody Message message, @PathVariable int messageId){
        if (messageService.messageExistsById(messageId) && !message.getMessageText().trim().isBlank() && message.getMessageText().length() <= 255){
            return ResponseEntity.status(200).body(messageService.updateMessageById(messageId, message.getMessageText()));
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }


    /* REQUEST MAPPINGS AND HANDLERS FOR MESSAGES END */
    /* REQUEST MAPPINGS AND HANDLERS FOR BOTH ACCOUT AND MESSAGES */


    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId){
        return ResponseEntity.status(200).body(messageService.getAllMessagesByAccountId(accountId));
    }


}
