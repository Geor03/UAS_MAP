package umn.ac.id.project_map;

public class CouponModel {
    String date, desc, name;

    public CouponModel(){

    }

    public CouponModel(String date, String desc, String name){
        this.date = date;
        this.desc = desc;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }
}
