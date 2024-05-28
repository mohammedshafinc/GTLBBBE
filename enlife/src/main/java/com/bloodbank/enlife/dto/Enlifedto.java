package com.bloodbank.enlife.dto;

import java.util.Date;

public class Enlifedto {

    private String name;
    private String mobile;
    private Date lastDonatedDate;
    private String address;
    private String bloodGroup;


    public Enlifedto(String name, String mobile, Date lastDonatedDate, String address, String bloodGroup) {
        this.name = name;
        this.mobile = mobile;
        this.lastDonatedDate = lastDonatedDate;
        this.address = address;
        this.bloodGroup = bloodGroup;


    }
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getMobile() {
            return mobile;
        }
    
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    
        public Date getLastDonatedDate() {
            return lastDonatedDate;
        }
    
        public void setLastDonatedDate(Date lastDonatedDate) {
            this.lastDonatedDate = lastDonatedDate;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getBloodGroup() {
            return bloodGroup;
        }
    
        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }
    
}
