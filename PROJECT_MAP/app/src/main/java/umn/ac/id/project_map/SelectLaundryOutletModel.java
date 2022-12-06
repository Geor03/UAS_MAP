package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

    public class SelectLaundryOutletModel {

        public String outlet_name;
        public int laundry_image;
        public String tagline;

        public SelectLaundryOutletModel(){}
        // Constructor
        public SelectLaundryOutletModel(String outlet_name, String tagline) {
            this.outlet_name = outlet_name;
            this.tagline = tagline;
        }

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
    }