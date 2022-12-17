package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnBack = (Button) findViewById(R.id.button_backFromNotif);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView notificationRV = findViewById(R.id.idRVNotification);

        // Here, we have created new array list and added data to it
        ArrayList<NotificationModel> laundryModelArrayList = new ArrayList<NotificationModel>();
        laundryModelArrayList.add(new NotificationModel("Welcome to our app", "Thank you for downloading our app"));
        laundryModelArrayList.add(new NotificationModel("Notification 1", "isi Notification 1"));


        // we are initializing our adapter class and passing our arraylist to it.
       NotificationAdapter laundryAdapter = new NotificationAdapter(this, laundryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        notificationRV.setLayoutManager(linearLayoutManager);
        notificationRV.setAdapter(laundryAdapter);
    }
}