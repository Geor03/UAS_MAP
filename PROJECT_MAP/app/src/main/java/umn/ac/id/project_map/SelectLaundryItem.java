package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SelectLaundryItem extends AppCompatActivity {
    private Button btnConfirm;
    private Button btnBackToOutlet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry_item);
        btnConfirm = (Button) findViewById(R.id.button_confirm);
        btnBackToOutlet = (Button) findViewById(R.id.button_backSelectOutlet);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnBackToOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        RecyclerView laundryRV = findViewById(R.id.idRVSelectLaundryItem);

        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryItemModel> laundryModelArrayList = new ArrayList<SelectLaundryItemModel>();
        laundryModelArrayList.add(new SelectLaundryItemModel("T-Shirts", R.drawable.tshirt_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Pants", R.drawable.pants_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Cardigans",  R.drawable.cardigan_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Dresses",  R.drawable.dress_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Short pants", R.drawable.shorts_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Outwear", R.drawable.outwear_item));
        laundryModelArrayList.add(new SelectLaundryItemModel("Others", R.drawable.others_item));


        // we are initializing our adapter class and passing our arraylist to it.
        SelectLaundryItemAdapter laundryAdapter = new SelectLaundryItemAdapter(this, laundryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(linearLayoutManager);
        laundryRV.setAdapter(laundryAdapter);
    }
}