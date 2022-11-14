package umn.ac.id.project_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class SelectLaundry extends AppCompatActivity{

    private Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry);
        btnOrder = (Button) findViewById(R.id.button);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOrderButton = new Intent(SelectLaundry.this, SelectLaundryOutlet.class);
                startActivity(intentOrderButton);
            }
        });


        RecyclerView laundryRV = findViewById(R.id.idRVSelectLaundry);

        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryModel> laundryModelArrayList = new ArrayList<SelectLaundryModel>();
        laundryModelArrayList.add(new SelectLaundryModel("Laundry", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Ironing", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Folding",  R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Dry Cleaning",  R.drawable.satu));

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