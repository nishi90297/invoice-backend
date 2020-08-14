package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
//@RequestMapping(value ="biller")
public class BillerController {

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
            biller.setPassword(new BCryptPasswordEncoder().encode(biller.getPassword()));
            billerRepository.insert(biller);
            return "Register Successfully Added!!";
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
        biller.setPassword(new BCryptPasswordEncoder().encode(biller.getPassword()));
        billerRepository.save(biller);
        return "Biller Successfully Updated!!";
    }

}
