package umn.ac.id.project_map;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectLaundryItemModel implements Serializable {
    private ArrayList<SelectLaundryItemModel> itemModels;
    public String item_name, docId;
    public int item_image;
    public int qty;

    public SelectLaundryItemModel(){}
    // Constructor
    public SelectLaundryItemModel(String laundry_name, int qty, String docId, ArrayList<SelectLaundryItemModel> itemModels) {
        this.item_name = laundry_name;
//        this.item_image = laundry_image;
        this.qty = qty;
        this.docId = docId;
        this.itemModels = itemModels;

    }

    public List<SelectLaundryItemModel> getItemModels() {
        return itemModels;
    }


    protected SelectLaundryItemModel(Parcel in) {
        item_name = in.readString();
        docId = in.readString();
//        item_image = in.readInt();
        qty = in.readInt();
    }

//    public static final Creator<SelectLaundryItemModel> CREATOR = new Creator<SelectLaundryItemModel>() {
//        @Override
//        public SelectLaundryItemModel createFromParcel(Parcel in) {
//            return new SelectLaundryItemModel(in);
//        }
//
//        @Override
//        public SelectLaundryItemModel[] newArray(int size) {
//            return new SelectLaundryItemModel[size];
//        }
//    };

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

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(item_name);
//        parcel.writeInt(qty);
//        parcel.writeString(docId);
//    }
}