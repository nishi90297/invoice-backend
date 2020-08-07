package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping(value = "getOneBiller/{id}")
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

    @PostMapping(value = "deleteBiller")
    public String deleteBiller(@RequestParam(value = "id") String id){
        try {
            billerRepository.deleteById(id);
            return  "Biller Successfully Deleted!!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping(value = "updateBiller")
    public String updateBiller(@RequestBody Biller biller){
        biller.setPassword(new BCryptPasswordEncoder().encode(biller.getPassword()));
        billerRepository.save(biller);
        return "Biller Successfully Updated!!";
    }

}
