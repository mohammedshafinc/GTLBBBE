package com.bloodbank.enlife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloodbank.enlife.model.EnlifeCampReg;
import com.bloodbank.enlife.model.EnlifeModel;
import com.bloodbank.enlife.model.EnlifeSearchResult;
import com.bloodbank.enlife.repository.EnlifeRepository;

@Service
public interface EnlifeService {
    // private static final Logger logger = LoggerFactory.getLogger(EnlifeService.class);

    // @Autowired

    // private EnlifeRepository enlifeRepository;

    // public ResponseEntity<?> login(String email, String password) {
    //     boolean isAuthenticated = enlifeRepository.authenticate(email, password);
    //     // System.out.println(password + email);
    //     System.out.println(isAuthenticated);
    //     logger.info("Login attempt for email: {}", email);

    //     if (isAuthenticated) {
    //         logger.info("Login successful for email: {}", email);
    //         return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Login successful\"}");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
    //     }
    // }

    // public ResponseEntity<?> register(EnlifeModel enlifeModel) {
    //     if (enlifeModel.getEmail() == null || enlifeModel.getEmail().isEmpty() ||
    //             enlifeModel.getPassword() == null || enlifeModel.getPassword().isEmpty()) {
    //         return ResponseEntity.badRequest().body("Email and password are required");
    //     }
    //     // Check if email is already registered
    //     if (enlifeRepository.isEmailRegistered(enlifeModel.getEmail())) {
    //         return ResponseEntity.badRequest().body("Email is already registered");
    //     }
        
    //     try {
    //         logger.info("Registering user: {}", enlifeModel);
    //         enlifeRepository.save(enlifeModel);
    //         logger.info("Registration successful for email: {}", enlifeModel.getEmail());
    //         return ResponseEntity.ok("Registration successful");
    //     } catch (Exception e) {
    //         logger.error("Registration failed for email: {}", enlifeModel.getEmail(), e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Registration failed: " + e.getMessage());
    //     }

    ResponseEntity<?> login(String email, String password);
    ResponseEntity<?> register(EnlifeModel enlifeModel);
    Map<String, Object>  authenticate(String email, String password);
    boolean isEmailRegistered(String email);
    ResponseEntity<List<EnlifeModel>> search(String bloodGroup, String place);
    ResponseEntity<?> campRegistration(EnlifeCampReg enlifeCampReg);
    List<Map<String, Object>> getCampReg();


    }


   

   



 

    


