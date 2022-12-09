package umn.ac.id.project_map;

import com.google.firebase.firestore.DocumentId;

public class SelectLaundryItemModel {

    public String item_name, docId;
    public int item_image;
    public int qty;

    public SelectLaundryItemModel(){}
    // Constructor
    public SelectLaundryItemModel(String laundry_name, int qty, String docId) {
        this.item_name = laundry_name;
//        this.item_image = laundry_image;
        this.qty = qty;
        this.docId = docId;

    }

    // Getter and Setter
    public String getLaundry_name() {
        return item_name;
    }

    public void setLaundry_name(String laundry_name) {
        this.item_name = laundry_name;
    }


    public int getLaundry_image() {
        return item_image;
    }

    public void setCourse_image(int laundry_image) {
        this.item_image = laundry_image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;

    }

    public void addToQuantity(){
        this.qty += 1;
    }

    public void removeFromQuantity(){
        if(this.qty >= 1){
            this.qty -= 1;
        }
    }

    @DocumentId
    public String getDocId() {
        return docId;
    }

    @DocumentId
    public void setDocId(String docId) {
        this.docId = docId;
    }

}