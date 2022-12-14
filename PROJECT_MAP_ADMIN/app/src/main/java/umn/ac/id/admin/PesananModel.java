package umn.ac.id.admin;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;

public class PesananModel {
    String address, customerId, status, laundry_type,docID;
    Date date;
    int total_pants, total_shirts;
    int total_price;

    public PesananModel(){}
    public PesananModel(String address, String customerId, Date date, String laundry_type, String status, int total_pants, int total_price, int total_shirts, String docID) {
        this.address = address;
        this.customerId = customerId;
        this.date = date;
        this.laundry_type = laundry_type;
        this.status = status;
        this.total_pants = total_pants;
        this.total_price = total_price;
        this.total_shirts = total_shirts;
        this.docID = docID;
    }

    @DocumentId
    public String getDocID() {
        return docID;
    }

    public Date getDate() {
        return date;
    }

    public int getTotal_pants() {
        return total_pants;
    }

    public int getTotal_price() {
        return total_price;
    }

    public int getTotal_shirts() {
        return total_shirts;
    }

    public String getAddress() {
        return address;
    }

    public String getLaundry_type() {
        return laundry_type;
    }

    public String getcustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLaundry_type(String laundry_type) {
        this.laundry_type = laundry_type;
    }

    public void setcustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal_pants(int total_pants) {
        this.total_pants = total_pants;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setTotal_shirts(int total_shirts) {
        this.total_shirts = total_shirts;
    }

    @DocumentId
    public void setDocID(String docID) {
        this.docID = docID;
    }
}
