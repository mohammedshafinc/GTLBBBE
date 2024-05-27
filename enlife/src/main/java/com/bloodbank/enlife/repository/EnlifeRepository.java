package com.bloodbank.enlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.enlife.model.EnlifeModel;

public interface EnlifeRepository  {

    boolean authenticate(String email, String password);
} 
