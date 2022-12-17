package umn.ac.id.project_map;

import java.util.Date;

public class CouponModel {
    String desc, name;
    Date date;

    public CouponModel(){

    }

    public CouponModel(Date date, String desc, String name){
        this.date = date;
        this.desc = desc;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }
}
