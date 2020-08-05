package com.bill.invoicebackend.respository;

import com.bill.invoicebackend.model.Biller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerRepository extends MongoRepository<Biller, String> {
}
