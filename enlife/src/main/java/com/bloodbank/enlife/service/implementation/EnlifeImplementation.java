package com.bloodbank.enlife.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bloodbank.enlife.model.EnlifeModel;
import com.bloodbank.enlife.repository.EnlifeRepository;


@Service
public class EnlifeImplementation implements EnlifeRepository{

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
