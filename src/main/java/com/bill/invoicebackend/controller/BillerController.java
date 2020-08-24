package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping(value ="biller")
public class BillerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public BillerRepository billerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<Biller> getAllBiller(){
        return billerRepository.findAll();
    }

    @GetMapping(value="/billerByQuery")
    public List<Biller> getBillerByQuery(){
        Query query = new Query();
        query.addCriteria(Criteria.where("name.first").is("Nishtha").and("name.last").is("Garg"));
        List<Biller> users = mongoTemplate.find(query,Biller.class);
        return users;
    }


    @GetMapping(value = "/getOneBiller/{id}")
    public Biller getOneBiller(@PathVariable("id") String id){
        return billerRepository.findById(id).get();
    }

    @PostMapping(value = "/register")
    public String registerBiller(@RequestBody Biller biller){
        try{
            biller.setPassword(passwordEncoder.encode(biller.getPassword()));
            billerRepository.insert(biller);
            return "User Successfully Registered!!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping(value = "/deleteBiller")
    public String deleteBiller(@RequestParam(value = "id") String id){
        try {
            billerRepository.deleteById(id);
            return  "Biller Successfully Deleted!!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping(value = "/updateBiller")
    public String updateBiller(@RequestBody Biller biller){
        biller.setPassword(passwordEncoder.encode(biller.getPassword()));
        billerRepository.save(biller);
        return "Biller Successfully Updated!!";
    }

    @GetMapping(value = "/getBillerInfo")
    public Biller getBillerInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return billerRepository.findByEmail(authentication.getName());
    }

}
