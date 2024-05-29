package com.bloodbank.enlife.model;

import java.util.Date;

public class EnlifeSearchResult {
    private String name;
    private String address;
    private Date lastDonatedDate;
    private String mobile;
    private String city;
    private String bloodGroup;

    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for lastDonatedDate
    public Date getLastDonatedDate() {
        return lastDonatedDate;
    }

    // Setter for lastDonatedDate
    public void setLastDonatedDate(Date lastDonatedDate) {
        this.lastDonatedDate = lastDonatedDate;
    }

    // Getter for mobile
    public String getMobile() {
        return mobile;
    }

    // Setter for mobile
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Setter for city
    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

}
