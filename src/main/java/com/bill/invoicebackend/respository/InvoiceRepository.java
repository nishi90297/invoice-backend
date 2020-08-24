package com.bill.invoicebackend.respository;

import com.bill.invoicebackend.model.Invoice.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    List<Invoice> findByCreatedBy(String createdBy);
}
