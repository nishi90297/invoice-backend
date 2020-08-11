package com.bill.invoicebackend.service;

import com.bill.invoicebackend.model.Biller;
import com.bill.invoicebackend.respository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private BillerRepository billerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Biller biller = billerRepository.findByEmail(email);
        System.out.println(billerRepository.findByEmail(email));
        if(biller!=null){
            return new User(biller.getEmail(),biller.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
}
    }
