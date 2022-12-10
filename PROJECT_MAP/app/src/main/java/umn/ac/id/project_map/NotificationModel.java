package umn.ac.id.project_map;

public class NotificationModel {

    private String notification_title;
//        private int laundry_rating;
    private String notification_message;

    // Constructor
    public NotificationModel(String notification_title, String notification_message) {
        this.notification_title = notification_title;
//            this.course_rating = course_rating;
        this.notification_message = notification_message;
    }

    // Getter and Setter
    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String laundry_name) {
        this.notification_title = notification_title;
    }

//        public int getCourse_rating() {
//            return course_rating;
//        }

//        public void setCourse_rating(int course_rating) {
//            this.course_rating = course_rating;
//        }

    public String getNotification_message() {
        return notification_message;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }
}