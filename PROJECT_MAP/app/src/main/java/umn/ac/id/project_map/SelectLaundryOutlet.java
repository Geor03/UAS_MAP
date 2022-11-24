package umn.ac.id.project_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class SelectLaundryOutlet extends AppCompatActivity{

    private Button btnBackToSelectLaundry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry_outlet);
        btnBackToSelectLaundry = (Button) findViewById(R.id.button_backSelectLaundry);
        btnBackToSelectLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView laundryRV = findViewById(R.id.idRVLaundry);

        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryOutletModel> laundryModelArrayList = new ArrayList<SelectLaundryOutletModel>();
        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Libro", R.drawable.libro_outlet));
        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Minahasa", R.drawable.minahasa_outlet));
        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Vit",  R.drawable.vit_outlet));
        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Waterfall",  R.drawable.waterfall_outlet));
        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Casey", R.drawable.casey_outlet));

        // we are initializing our adapter class and passing our arraylist to it.
        SelectLaundryOutletAdapter laundryAdapter = new SelectLaundryOutletAdapter(this, laundryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(linearLayoutManager);
        laundryRV.setAdapter(laundryAdapter);

    }
}