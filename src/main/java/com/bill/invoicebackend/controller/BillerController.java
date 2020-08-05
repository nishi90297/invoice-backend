package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value ="biller")
public class BillerController {

    @Autowired
    public BillerRepository billerRepository;

    @GetMapping()
    public List<Biller> getAllBiller(){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("first").is("Nishtha").and())

//        List<Biller> users = mongoTemplate.find(query,Biller.class);
        return billerRepository.findAll();
    }

    @PostMapping(value = "/register")
    public String registerBiller(@RequestBody Biller newBiller){
        try{
            billerRepository.insert(newBiller);
            return "Register Successfully Added";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

}
