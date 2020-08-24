package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Biller.Biller;
import com.bill.invoicebackend.model.Invoice.Invoice;
import com.bill.invoicebackend.model.Invoice.InvoiceStatus;
import com.bill.invoicebackend.respository.BillerRepository;
import com.bill.invoicebackend.respository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    public BillerRepository billerRepository;

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Biller getBillerInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return billerRepository.findByEmail(authentication.getName());
    }

    @PostMapping(value = "addInvoiceForm")
    public String addInvoiceForm(@RequestBody Invoice invoice){
        invoice.setCreatedBy(getBillerInfo().getId());
        invoice.setCreatedAt();
        invoice.setStatus(InvoiceStatus.DATA_SAVED);
        System.out.println(invoice.toString());
        invoiceRepository.save(invoice);
        return invoiceRepository.save(invoice).getId();
    }

    @GetMapping(value = "getAllInvoices")
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findByCreatedBy(getBillerInfo().getId());
    }

    @GetMapping(value = "getInvoiceById/{id}")
    public Invoice getInvoiceById(@PathVariable("id") String id){
        return invoiceRepository.findById(id).get();
    }

    @GetMapping(value = "getInvoiceNumber")
    public Integer getInvoiceNumber(){
        List<Invoice> invoiceList = invoiceRepository.findByCreatedBy(getBillerInfo().getId());
        return invoiceList.size()+1;
    }


}
