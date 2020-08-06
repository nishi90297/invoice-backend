package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private BillerRepository billerRepository;

    @GetMapping(value = "login")
    public String billerLogin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){

        Biller result = billerRepository.findByEmailAndPassword(email,password);
        if(result==null){
            return "Wrong Credentials !";
        }
        return "Successfully login !";
    }



}
