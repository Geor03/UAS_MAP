package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentId;

public class SelectLaundryOutletModel implements Parcelable {

        public String outlet_name, docId;
        public int laundry_image;
        public String tagline;

        public SelectLaundryOutletModel(){}
        // Constructor
        public SelectLaundryOutletModel(String outlet_name, String tagline, String docId) {
            this.outlet_name = outlet_name;
            this.tagline = tagline;
            this.docId = docId;
        }

    protected SelectLaundryOutletModel(Parcel in) {
        outlet_name = in.readString();
        docId = in.readString();
//        laundry_image = in.readInt();
        tagline = in.readString();
    }

    public static final Creator<SelectLaundryOutletModel> CREATOR = new Creator<SelectLaundryOutletModel>() {
        @Override
        public SelectLaundryOutletModel createFromParcel(Parcel in) {
            return new SelectLaundryOutletModel(in);
        }

        @Override
        public SelectLaundryOutletModel[] newArray(int size) {
            return new SelectLaundryOutletModel[size];
        }
    };

    // Getter and Setter
        public String getLaundry_name() {
            return outlet_name;
        }

        public String getLaundry_desc() {
            return tagline;
        }

//        public int getLaundry_image() {
//            return laundry_image;
//        }

        public void setLaundry_name(String laundry_name) {
            this.outlet_name = outlet_name;
        }

        public void setLaundry_desc(String tagline) {
            this.tagline = tagline;
        }

//        public void setLaundry_image(int laundry_image) {
//            this.laundry_image = laundry_image;
//        }

    @DocumentId
    public String getDocId() {
        return docId;
    }

    @DocumentId
    public void setDocId(String docId) {
        this.docId = docId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(outlet_name);
            parcel.writeString(docId);
            parcel.writeString(tagline);
    }
}