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
   

    ResponseEntity<?> login(String email, String password);
    ResponseEntity<?> register(EnlifeModel enlifeModel);
    Map<String, Object>  authenticate(String email, String password);
    boolean isEmailRegistered(String email);
    ResponseEntity<List<EnlifeModel>> search(String bloodGroup, String place);
    ResponseEntity<?> campRegistration(EnlifeCampReg enlifeCampReg);
    List<Map<String, Object>> getCampReg();
    int getCampCount();


    }


   

   



 

    


