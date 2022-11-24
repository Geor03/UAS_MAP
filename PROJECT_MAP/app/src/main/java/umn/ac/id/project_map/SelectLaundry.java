package umn.ac.id.project_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class SelectLaundry extends AppCompatActivity{

    private Button btnToSelectOutlet,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry);
        btnBack = (Button) findViewById(R.id.button_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        RecyclerView laundryRV = findViewById(R.id.idRVSelectLaundry);
        TextView laundryTV = findViewById(R.id.idTVLaundryName);
        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryModel> laundryModelArrayList = new ArrayList<SelectLaundryModel>();
        laundryModelArrayList.add(new SelectLaundryModel("Laundry", R.drawable.laundry_image));
        laundryModelArrayList.add(new SelectLaundryModel("Ironing", R.drawable.ironing_image));
        laundryModelArrayList.add(new SelectLaundryModel("Folding",  R.drawable.folding_image));
        laundryModelArrayList.add(new SelectLaundryModel("Dry Cleaning",  R.drawable.drycleaning_image));

        // we are initializing our adapter class and passing our arraylist to it.
        SelectLaundryAdapter laundryAdapter = new SelectLaundryAdapter(this, laundryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(linearLayoutManager);
        laundryRV.setAdapter(laundryAdapter);

    }
}