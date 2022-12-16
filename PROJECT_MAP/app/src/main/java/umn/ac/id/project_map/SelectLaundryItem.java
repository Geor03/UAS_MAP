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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SelectLaundryItem extends AppCompatActivity {
    private Button btnConfirm;
    private Button btnBackToOutlet;
    private RecyclerView laundryRV;
    private static ArrayList<SelectLaundryItemModel> itemArrayList;
    String order;
    String type;
    private RecyclerView.LayoutManager mLayoutManager;
    SelectLaundryItemAdapter itemAdapter;

    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry_item);
        Intent intent = getIntent();
        order = intent.getStringExtra("laundry_outlet");
        type = intent.getStringExtra("laundry_type");
        btnConfirm = (Button) findViewById(R.id.button_confirm);
        btnBackToOutlet = (Button) findViewById(R.id.button_backSelectOutlet);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToCheckOut = new Intent(view.getContext(), OrderPage.class);
                Bundle bundle = new Bundle();

                // dan total input baju.
                bundle.putSerializable("items", itemArrayList);
                bundle.putString("Outlet", order );
                bundle.putString("Type", type );

                Log.d("Isi dari pakaian", String.valueOf(itemArrayList.getClass()));
                Log.d("Tipe dari order", String.valueOf(order.getClass()));
                Log.d("Isi dari order", String.valueOf(order));

                intentToCheckOut.putExtras(bundle);
                view.getContext().startActivity(intentToCheckOut);
            }
        });

        btnBackToOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        laundryRV = findViewById(R.id.idRVSelectLaundryItem);

        // Here, we have created new array list and added data to it
        itemArrayList = new ArrayList<SelectLaundryItemModel>();

        // we are initializing our adapter class and passing our arraylist to it.
        itemAdapter = new SelectLaundryItemAdapter(this, itemArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        laundryRV.setLayoutManager(mLayoutManager);
        laundryRV.setAdapter(itemAdapter);


        fetchData();
    }
    public void fetchData(){
        fStore.collection("items").orderBy("item_name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){

                        itemArrayList.add(dc.getDocument().toObject(SelectLaundryItemModel.class));
                    }

                    itemAdapter.notifyDataSetChanged();
                }
            }
        });
    }


}