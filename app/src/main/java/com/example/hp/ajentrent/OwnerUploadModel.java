package com.example.hp.ajentrent;

import java.util.Date;

public class OwnerUploadModel {

    private String Customer_id;
    private String date;
    private int amount;
    private String Square_Area;
    private String House_type;
    private String Location;
    private String Ac_nonAc;
    private String Furnished;
    private String Pets_Allowed;
    private String Washing_machine;
    private String Attched_Bathroom;
    private String mImageUrl;

    private String PhoneNum;


    public OwnerUploadModel() {
        //empty constructor needed
    }



    public OwnerUploadModel(String customer_id,String date, int amount, String house_type, String location, String square_Area, String ac_nonAc, String furnished, String pets_Allowed, String washing_machine, String attched_Bathroom, String mImageUrl, String phone) {
        this.Customer_id = customer_id;
        this.date = date;
        this.amount = amount;
        this.House_type = house_type;
        this.Location = location;
        this.Square_Area = square_Area;
        this.Ac_nonAc = ac_nonAc;
        this.Furnished = furnished;
        this.Pets_Allowed = pets_Allowed;
        this.Washing_machine = washing_machine;
        this.Attched_Bathroom = attched_Bathroom;
        this.mImageUrl = mImageUrl;
        this.PhoneNum = phone;
    }

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String customer_id) {
        Customer_id = customer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getHouse_type() {
        return House_type;
    }

    public void setHouse_type(String house_type) {
        House_type = house_type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSquare_Area() {
        return Square_Area;
    }

    public void setSquare_Area(String square_Area) {
        Square_Area = square_Area;
    }

    public String getAc_nonAc() {
        return Ac_nonAc;
    }

    public void setAc_nonAc(String ac_nonAc) {
        Ac_nonAc = ac_nonAc;
    }

    public String getFurnished() {
        return Furnished;
    }

    public void setFurnished(String furnished) {
        Furnished = furnished;
    }

    public String getPets_Allowed() {
        return Pets_Allowed;
    }

    public void setPets_Allowed(String pets_Allowed) {
        Pets_Allowed = pets_Allowed;
    }

    public String getWashing_machine() {
        return Washing_machine;
    }

    public void setWashing_machine(String washing_machine) {
        Washing_machine = washing_machine;
    }

    public String getAttched_Bathroom() {
        return Attched_Bathroom;
    }

    public void setAttched_Bathroom(String attched_Bathroom) {
        Attched_Bathroom = attched_Bathroom;
    }
    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
