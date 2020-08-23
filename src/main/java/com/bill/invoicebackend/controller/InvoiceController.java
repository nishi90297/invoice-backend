package com.bill.invoicebackend.controller;

import com.bill.invoicebackend.model.Invoice;
import com.bill.invoicebackend.respository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    public InvoiceRepository invoiceRepository;

    @PostMapping(value = "addInvoiceForm")
    public String addInvoiceForm(@RequestBody Invoice invoice){
        invoiceRepository.save(invoice);
        return "Successfully Added";
    }


    @GetMapping(value = "getAllInvoices")
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }


}
