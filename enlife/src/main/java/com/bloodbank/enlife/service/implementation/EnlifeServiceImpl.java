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

import com.bloodbank.enlife.model.EnlifeCampReg;
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
        Map<String, Object> userDetails = authenticate(email, password);
        logger.info("Login attempt for email: {}", email);
        
        if (userDetails != null) {
            logger.info("Login successful for email: {}", email);
            return ResponseEntity.ok().body(userDetails); // Return the user details as JSON
        } else {
            logger.info("Login failed for email: {}", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"Invalid username or password\"}");
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


  // camp registration  
  
    @Override
    public ResponseEntity<?> campRegistration(EnlifeCampReg enlifeCampReg){
        try {
            // Call the stored procedure to insert the camp registration information

            String sql = "EXEC BloodDonationCampRegistration_19749 @OrganisationName = ?, @Email = ?,  @Phone = ?,  @Location= ?, @Date = ?";
            jdbcTemplate.update(sql,
            enlifeCampReg.getOrganisationName(),
            enlifeCampReg.getEmail(),
            enlifeCampReg.getPhone(),
            enlifeCampReg.getLocation(),
            enlifeCampReg.getDate()
            );
            logger.info("campregistration successful for email: {}", enlifeCampReg.getEmail());
            return ResponseEntity.ok("campregistration  successful");
            
        } catch (Exception e) {
            logger.error("campregistration failed for email: {}", enlifeCampReg.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("campregistration failed: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<EnlifeModel>> search(String bloodGroup, String City) {
        logger.info("Search request received for bloodGroup: {} and city: {}", bloodGroup, City);
        String sql = "EXEC search_19749 @BloodGroup = '" + bloodGroup + "', @Place = '" + City + "'";
        List<EnlifeModel> searchResults = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EnlifeModel.class));
        logger.debug("SQL Query executed: {}", sql);
        logger.info("Search results: {}", searchResults);
        return ResponseEntity.ok(searchResults);
    }


   @Override
    public boolean isEmailRegistered(String email) {
        String sql = "select count(*) from BloodDonors_21 where Email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0 ;
    }

    @Override
    public Map<String, Object> authenticate(String email, String password) {
        
        String sql = "EXEC UserLogin @Email = ?, @Password = ?";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, email, password);
        
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0); // Return the first user details (there should be only one match)
        }
    }

    // getting camp details
    @Override
    public List<Map<String, Object>> getCampReg() {
        String sql = "EXEC getCampresult_19749";  // Adjust the stored procedure name if different
        List<Map<String, Object>> campRegResults = jdbcTemplate.queryForList(sql);
        return campRegResults;
    }

    // to get the count of the camp
    @Override
    public int getCampCount() {
        String sql = "EXEC GetCampRegistrationCount";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }


}
