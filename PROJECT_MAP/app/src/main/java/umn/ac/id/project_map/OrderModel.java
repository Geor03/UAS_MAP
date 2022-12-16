package umn.ac.id.project_map;

import java.util.Date;

public class OrderModel {
    String address, outlet, status, laundry_type;
    Date date;
    int total_cardigans, total_dress, total_outwear, total_pants, total_shirt, total_shorts, total_tshirts, total_tie;
    int total_price;

    public OrderModel(){}
    public OrderModel(String address, Date date, String laundry_type, String outlet, String status, int total_pants, int total_price, int total_shirt ){
        this.address = address;
        this.date = date;
        this.outlet = outlet;
        this.laundry_type = laundry_type;
        this.status = status;
        this.total_pants = total_pants;
        this.total_shirt = total_shirt;
        this.total_price = total_price;
    }

    public String getLaundry_type() {
        return laundry_type;
    }

    public String getAddress(){
        return address;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getOutlet() {
        return outlet;
    }

    public int getTotal_dress() {
        return total_dress;
    }

    public int getTotal_outwear() {
        return total_outwear;
    }

    public int getTotal_pants() {
        return total_pants;
    }

    public int getTotal_price() {
        return total_price;
    }

    public int getTotal_shirt() {
        return total_shirt;
    }

    public int getTotal_shorts() {
        return total_shorts;
    }

    public int getTotal_tie() {
        return total_tie;
    }

    public int getTotal_tshirts() {
        return total_tshirts;
    }

    public int getTotal_cardigans() {
        return total_cardigans;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal_cardigans(int total_cardigans) {
        this.total_cardigans = total_cardigans;
    }

    public void setLaundry_type(String laundry_type) {
        this.laundry_type = laundry_type;
    }

    public void setTotal_dress(int total_dress) {
        this.total_dress = total_dress;
    }

    public void setTotal_outwear(int total_outwear) {
        this.total_outwear = total_outwear;
    }

    public void setTotal_pants(int total_pants) {
        this.total_pants = total_pants;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setTotal_shirt(int total_shirt) {
        this.total_shirt = total_shirt;
    }

    public void setTotal_shorts(int total_shorts) {
        this.total_shorts = total_shorts;
    }

    public void setTotal_tie(int total_tie) {
        this.total_tie = total_tie;
    }

    public void setTotal_tshirts(int total_tshirts) {
        this.total_tshirts = total_tshirts;
    }
}
