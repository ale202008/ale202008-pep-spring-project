package com.example.controller;
import org.springframework.http.ResponseEntity;
// Adding imports for necessary annotations
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.service.*;
import com.example.entity.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    private AccountService accountService;


    // Setting up handler for #1: processing new User registration
    @GetMapping("/register")
    public @ResponseBody ResponseEntity<String> register(@RequestBody Account account){

        return null;
    }
}
