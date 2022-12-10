package umn.ac.id.project_map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private ArrayList<SelectLaundryItemModel> itemArrayList;
    private String order;
    private RecyclerView.LayoutManager mLayoutManager;
    SelectLaundryItemAdapter itemAdapter;

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_laundry_item);
        Intent intent=getIntent();
        order = intent.getParcelableExtra("laundry_outlet");
//        Log.d("Isi dari order", String.valueOf(order.size()));

        btnConfirm = (Button) findViewById(R.id.button_confirm);
        btnBackToOutlet = (Button) findViewById(R.id.button_backSelectOutlet);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToCheckOut = new Intent(SelectLaundryItem.this, OrderPage.class);
                // Disini haru ada extra yang ngirim docId dari outlet
                intentToCheckOut.putExtra("id_outlet",order);
                // dan total input baju.
                intentToCheckOut.putExtra("Jumlah_item",itemArrayList.toArray());
                startActivity(intentToCheckOut);
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