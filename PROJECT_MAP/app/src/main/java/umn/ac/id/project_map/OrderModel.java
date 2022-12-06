package umn.ac.id.project_map;

import java.util.Date;

public class OrderModel {
    String address, outlet, status, laundry_type;
    Date date;
    String total_cardigans, total_dress, total_outwear, total_pants, total_shirt, total_shorts, total_tshirts, total_tie;
    String total_price;

    public OrderModel(){}
    public OrderModel(String address, Date date,String outlet,String status, String total_cardigans,String total_dress,String total_outwear,String total_pants,String total_shirt,String total_shorts,String total_tshirts,String total_tie, String total_price, String laundry_type){
        this.address = address;
        this.date = date;
        this.outlet = outlet;
        this.laundry_type = laundry_type;
        this.status = status;
        this.total_cardigans = total_cardigans;
        this.total_dress = total_dress;
        this.total_outwear = total_outwear;
        this.total_pants = total_pants;
        this.total_shirt = total_shirt;
        this.total_shorts = total_shorts;
        this.total_tshirts = total_tshirts;
        this.total_tie = total_tie;
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

    public String getTotal_dress() {
        return total_dress;
    }

    public String getTotal_outwear() {
        return total_outwear;
    }

    public String getTotal_pants() {
        return total_pants;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getTotal_shirt() {
        return total_shirt;
    }

    public String getTotal_shorts() {
        return total_shorts;
    }

    public String getTotal_tie() {
        return total_tie;
    }

    public String getTotal_tshirts() {
        return total_tshirts;
    }

    public String getTotal_cardigans() {
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

    public void setTotal_cardigans(String total_cardigans) {
        this.total_cardigans = total_cardigans;
    }

    public void setLaundry_type(String laundry_type) {
        this.laundry_type = laundry_type;
    }

    public void setTotal_dress(String total_dress) {
        this.total_dress = total_dress;
    }

    public void setTotal_outwear(String total_outwear) {
        this.total_outwear = total_outwear;
    }

    public void setTotal_pants(String total_pants) {
        this.total_pants = total_pants;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public void setTotal_shirt(String total_shirt) {
        this.total_shirt = total_shirt;
    }

    public void setTotal_shorts(String total_shorts) {
        this.total_shorts = total_shorts;
    }

    public void setTotal_tie(String total_tie) {
        this.total_tie = total_tie;
    }

    public void setTotal_tshirts(String total_tshirts) {
        this.total_tshirts = total_tshirts;
    }
}
