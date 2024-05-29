package com.bloodbank.enlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bloodbank.enlife.service.EnlifeService;
import com.bloodbank.enlife.model.EnlifeModel;
import com.bloodbank.enlife.model.EnlifeSearchResult;
import com.bloodbank.enlife.repository.EnlifeRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/enlife")
public class EnlifeController {

    private static final Logger logger = LoggerFactory.getLogger(EnlifeService.class);
    @Autowired
    private EnlifeService enlifeservice;
    private EnlifeRepository enlifeRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EnlifeModel enlifeModel) {
        return enlifeservice.login(enlifeModel.getEmail(), enlifeModel.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody EnlifeModel enlifeModel) {
        logger.info("Registration request received with data: {}", enlifeModel);
        return enlifeservice.register(enlifeModel);
        
    }
    @PostMapping("/search")
    public ResponseEntity<List<EnlifeModel>> search(@RequestBody EnlifeModel enlifeModel) {
        return enlifeservice.search( enlifeModel.getBloodGroup(), enlifeModel.getCity());
    }
    

}
