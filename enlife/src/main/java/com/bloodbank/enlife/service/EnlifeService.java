package com.bloodbank.enlife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bloodbank.enlife.repository.EnlifeRepository;


@Service
public class EnlifeService {

    @Autowired

    private EnlifeRepository enlifeRepository;

    public ResponseEntity<?> login(String email, String password) {
        boolean isAuthenticated  = enlifeRepository.authenticate(email, password);
        // System.out.println(password + email);
        System.out.println(isAuthenticated);
    

    if(isAuthenticated) {
        return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Login successful\"}");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
    }
}
    
}
