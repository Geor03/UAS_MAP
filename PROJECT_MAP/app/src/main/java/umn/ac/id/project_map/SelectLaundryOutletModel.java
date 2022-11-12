package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

    public class SelectLaundryOutletModel {

        private String laundry_name;
//        private int laundry_rating;
        private int laundry_image;

        // Constructor
        public SelectLaundryOutletModel(String laundry_name, int laundry_image) {
            this.laundry_name = laundry_name;
//            this.course_rating = course_rating;
            this.laundry_image = laundry_image;
        }

        // Getter and Setter
        public String getLaundry_name() {
            return laundry_name;
        }

        public void setLaundry_name(String laundry_name) {
            this.laundry_name = laundry_name;
        }

//        public int getCourse_rating() {
//            return course_rating;
//        }

//        public void setCourse_rating(int course_rating) {
//            this.course_rating = course_rating;
//        }

        public int getLaundry_image() {
            return laundry_image;
        }

        public void setCourse_image(int laundry_image) {
            this.laundry_image = laundry_image;
        }
    }