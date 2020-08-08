package com.bill.invoicebackend.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if("admin".equals(userName)){
            return new User("admin","$2y$12$lF/kxUT9IpmqL7sTMTKdHuvx6z.rOvhOwZT5bOF06nVp5PcSJ4cJy",new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
