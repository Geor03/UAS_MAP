package umn.ac.id.project_map;

public class NotificationModel {

    private String laundry_title;
//        private int laundry_rating;
    private String laundry_message;

    // Constructor
    public NotificationModel(String laundry_title, String laundry_message) {
        this.laundry_title = laundry_title;
//            this.course_rating = course_rating;
        this.laundry_message = laundry_message;
    }

    // Getter and Setter
    public String getLaundry_name() {
        return laundry_title;
    }

    public void setLaundry_name(String laundry_name) {
        this.laundry_title = laundry_title;
    }

//        public int getCourse_rating() {
//            return course_rating;
//        }

//        public void setCourse_rating(int course_rating) {
//            this.course_rating = course_rating;
//        }

    public String getLaundry_message() {
        return laundry_message;
    }

    public void setLaundry_message(String laundry_message) {
        this.laundry_message = laundry_message;
    }
}