package umn.ac.id.project_map;

import android.widget.TextView;

public class Order_listMOdel {

    private String tvOrderListStatus;
    private String tvOrderListOrderID;
    private String tvOrderListDate;
    private String tvOrderListPrice;

    public void setTvOrderListStatus(String tvOrderListStatus) {
        this.tvOrderListStatus = tvOrderListStatus;
    }

    public void setTvOrderListOrderID(String tvOrderListOrderID) {
        this.tvOrderListOrderID = tvOrderListOrderID;
    }

    public void setTvOrderListDate(String tvOrderListDate) {
        this.tvOrderListDate = tvOrderListDate;
    }

    public void setTvOrderListPrice(String tvOrderListPrice) {
        this.tvOrderListPrice = tvOrderListPrice;
    }

    public String getTvOrderListStatus() {
        return tvOrderListStatus;
    }

    public String getTvOrderListOrderID() {
        return tvOrderListOrderID;
    }

    public String getTvOrderListDate() {
        return tvOrderListDate;
    }

    public String getTvOrderListPrice() {
        return tvOrderListPrice;
    }

    public Order_listMOdel(String tvOrderListStatus, String tvOrderListOrderID, String tvOrderListDate, String tvOrderListPrice) {
        this.tvOrderListStatus = tvOrderListStatus;
        this.tvOrderListOrderID = tvOrderListOrderID;
        this.tvOrderListDate = tvOrderListDate;
        this.tvOrderListPrice = tvOrderListPrice;
    }
}
