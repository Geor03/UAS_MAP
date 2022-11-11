package umn.ac.id.project_map;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MainOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);

        RecyclerView laundryRV = findViewById(R.id.idRVLaundry);

        // Here, we have created new array list and added data to it
        ArrayList<LaundryModel> laundryModelArrayList = new ArrayList<LaundryModel>();
        laundryModelArrayList.add(new LaundryModel("Laundry Libro", R.drawable.satu));
        laundryModelArrayList.add(new LaundryModel("Laundry Minahasa", R.drawable.satu));
        laundryModelArrayList.add(new LaundryModel("Laundry Vit",  R.drawable.satu));
        laundryModelArrayList.add(new LaundryModel("Laundry Waterfall",  R.drawable.satu));
        laundryModelArrayList.add(new LaundryModel("Laundry Casey", R.drawable.satu));

        // we are initializing our adapter class and passing our arraylist to it.
        LaundryAdapter laundryAdapter = new LaundryAdapter(this, laundryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(linearLayoutManager);
        laundryRV.setAdapter(laundryAdapter);

    }
}