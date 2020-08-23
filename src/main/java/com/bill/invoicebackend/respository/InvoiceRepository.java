package com.bill.invoicebackend.respository;

import com.bill.invoicebackend.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
