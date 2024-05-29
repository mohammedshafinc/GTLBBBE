package com.bloodbank.enlife.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bloodbank.enlife.model.EnlifeModel;
import com.bloodbank.enlife.model.EnlifeSearchResult;
import com.bloodbank.enlife.repository.EnlifeRepository;
import com.bloodbank.enlife.service.EnlifeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnlifeServiceImpl implements EnlifeService {

    private static final Logger logger = LoggerFactory.getLogger(EnlifeServiceImpl.class);


   

    

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<?> login(String email, String password) {
        boolean isAuthenticated = authenticate(email, password);
        logger.info("Login attempt for email: {}", email);
        if (isAuthenticated) {
            logger.info("Login successful for email: {}", email);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Login successful\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username or password");
        }
    }

    @Override
    public ResponseEntity<?> register(EnlifeModel enlifeModel) {
        if (enlifeModel.getEmail() == null || enlifeModel.getEmail().isEmpty() ||
            enlifeModel.getPassword() == null || enlifeModel.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        if (isEmailRegistered(enlifeModel.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }
        try {
            logger.info("Registering user: {}", enlifeModel);
            
            // Call the stored procedure to insert the blood donor information
            String sql = "EXEC InsertBloodDonor @Name = ?, @Email = ?, @Password = ?, @Mobile = ?, @Dob = ?, @Gender = ?, @Address = ?, @BloodGroup = ?, @District = ?, @City = ?, @Pincode = ?, @LastDonatedDate = ?";   
           jdbcTemplate.update(sql, 
                enlifeModel.getName(), 
                enlifeModel.getEmail(), 
                enlifeModel.getPassword(), 
                enlifeModel.getMobile(), 
                enlifeModel.getDob(), 
                enlifeModel.getGender(), 
                enlifeModel.getAddress(), 
                enlifeModel.getBloodGroup(), 
                enlifeModel.getDistrict(), 
                enlifeModel.getCity(), 
                enlifeModel.getPincode(), 
                enlifeModel.getLastDonatedDate());
            
            logger.info("Registration successful for email: {}", enlifeModel.getEmail());
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            logger.error("Registration failed for email: {}", enlifeModel.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<EnlifeModel>> search(String bloodGroup, String City) {
        System.out.println(City);
        System.out.println(bloodGroup);
        String sql = "EXEC search_19749 @BloodGroup = '" + bloodGroup + "', @Place = '" + City + "'";
        List<EnlifeModel> searchResults = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EnlifeModel.class));
       System.out.println(searchResults);
        return ResponseEntity.ok(searchResults);
    }


   @Override
    public boolean isEmailRegistered(String email) {
        String sql = "select count(*) from BloodDonors_21 where Email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0 ;
    }

    @Override
    public boolean authenticate(String email, String password) {

        // Query to check if the provided email and password match in the database
        String sql = "EXEC UserLogin @Email = ?, @Password = ?";

         // Execute the query and get the count
         int count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
         System.out.println(email);
         System.out.println(password);

        // Return true if count is greater than 0 (i.e., there is a matching user), false otherwise
         return count > 0;
    }


}
