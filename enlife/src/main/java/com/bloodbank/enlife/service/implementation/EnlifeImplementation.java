package com.bloodbank.enlife.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bloodbank.enlife.model.EnlifeModel;
import com.bloodbank.enlife.repository.EnlifeRepository;
import com.bloodbank.enlife.service.EnlifeService;


@Service
public class EnlifeImplementation implements EnlifeRepository{

    private static final Logger logger = LoggerFactory.getLogger(EnlifeService.class);

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    public boolean authenticate(String email, String password) {

        // Query to check if the provided email and password match in the database
        String sql = "EXEC UserLogin @Email = ?, @Password = ?";

         // Execute the query and get the count
         int count = jdbctemplate.queryForObject(sql, Integer.class, email, password);
         System.out.println(email);
         System.out.println(password);

        // Return true if count is greater than 0 (i.e., there is a matching user), false otherwise
         return count > 0;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        String sql = "select count(*) from BloodDonors_21 where Email = ?";
        int count = jdbctemplate.queryForObject(sql, Integer.class, email);
        return count > 0 ;
    }

    @Override
    public void save(EnlifeModel enlifeModel) {
        String sql = "EXEC InsertBloodDonor @Name = ?, @Email = ?, @Password = ?, @Mobile = ?, @Dob = ?, @Gender = ?, @Address = ?, @BloodGroup = ?, @District = ?, @City = ?, @Pincode = ?, @LastDonatedDate = ?";
        logger.info("Executing SQL: {}", sql); // Log the SQL query
        logger.info("Data to be saved: {}", enlifeModel); // Log the data being saved
        
        jdbctemplate.update(sql,
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
        
                
    }
    
}
