package com.bloodbank.enlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.enlife.service.EnlifeService;
import com.bloodbank.enlife.model.EnlifeModel;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/enlife")
public class EnlifeController {

    @Autowired
    private EnlifeService enlifeservice;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EnlifeModel enlifeModel) {
        return enlifeservice.login(enlifeModel.getEmail(), enlifeModel.getPassword());
    }
   
    
}
