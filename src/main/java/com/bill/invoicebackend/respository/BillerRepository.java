package com.bill.invoicebackend.respository;

import com.bill.invoicebackend.model.Biller.Biller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerRepository extends MongoRepository<Biller, String> {

    public Biller findByEmail(String email);
    public Biller findByEmailAndPassword(String email, String password);
}