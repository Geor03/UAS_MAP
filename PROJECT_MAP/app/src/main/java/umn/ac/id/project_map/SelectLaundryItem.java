package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SelectLaundryItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry_item);

        RecyclerView laundryRV = findViewById(R.id.idRVSelectLaundryItem);

        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryItemModel> laundryModelArrayList = new ArrayList<SelectLaundryItemModel>();
        laundryModelArrayList.add(new SelectLaundryItemModel("T-Shirts", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Pants", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Cardigans",  R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Dresses",  R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Short pants", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Outwear", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryItemModel("Others", R.drawable.satu));


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