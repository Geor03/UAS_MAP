package umn.ac.id.project_map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class SelectLaundryOutlet extends AppCompatActivity{

    private Button btnBackToSelectLaundry;
    private RecyclerView laundryRV;
    private ArrayList<SelectLaundryOutletModel> laundryArrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    SelectLaundryOutletAdapter laundryOutletAdapter;

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();


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

//        laundryArrayList.add(new SelectLaundryOutletModel("Laundry Libro", R.drawable.libro_outlet));
//        laundryArrayList.add(new SelectLaundryOutletModel("Laundry Minahasa", R.drawable.minahasa_outlet));
//        laundryArrayList.add(new SelectLaundryOutletModel("Laundry Vit",  R.drawable.vit_outlet));
//        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Waterfall",  R.drawable.waterfall_outlet));
//        laundryModelArrayList.add(new SelectLaundryOutletModel("Laundry Casey", R.drawable.casey_outlet));

        laundryRV = findViewById(R.id.idRVLaundry);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(mLayoutManager);

        // Here, we have created new array list and added data to it
        laundryArrayList = new ArrayList<SelectLaundryOutletModel>();

        // we are initializing our adapter class and passing our arraylist to it.
        laundryOutletAdapter = new SelectLaundryOutletAdapter(this, laundryArrayList);

        laundryRV.setAdapter(laundryOutletAdapter);

        fetchlaundry();
    }
    private void fetchlaundry(){
        fStore.collection("outlets").orderBy("outlet_name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        laundryArrayList.add(dc.getDocument().toObject(SelectLaundryOutletModel.class));
                    }
                    laundryOutletAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}