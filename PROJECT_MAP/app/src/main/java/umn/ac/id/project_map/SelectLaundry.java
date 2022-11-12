package umn.ac.id.project_map;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class SelectLaundry extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry);

        RecyclerView laundryRV = findViewById(R.id.idRVSelectLaundry);

        // Here, we have created new array list and added data to it
        ArrayList<SelectLaundryModel> laundryModelArrayList = new ArrayList<SelectLaundryModel>();
        laundryModelArrayList.add(new SelectLaundryModel("Laundry Libro", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Laundry Minahasa", R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Laundry Vit",  R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Laundry Waterfall",  R.drawable.satu));
        laundryModelArrayList.add(new SelectLaundryModel("Laundry Casey", R.drawable.satu));

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