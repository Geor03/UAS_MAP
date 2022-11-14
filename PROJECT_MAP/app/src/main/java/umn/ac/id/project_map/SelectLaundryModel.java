package umn.ac.id.project_map;

public class SelectLaundryModel {

    private String laundry_name;
//        private int laundry_rating;
    private int laundry_image;

    // Constructor
    public SelectLaundryModel(String laundry_name, int laundry_image) {
        this.laundry_name = laundry_name;
//            this.course_rating = course_rating;
        this.laundry_image = laundry_image;
    }

    // Getter and Setter
    public String getLaundry_name() {
        return this.laundry_name;
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
        return this.laundry_image;
    }

    public void setLaundry_image(int laundry_image) {
        this.laundry_image = laundry_image;
    }
}