package com.bill.invoicebackend.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping(value = "login")
    public String billerLogin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){

    }



}
