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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Order_list extends AppCompatActivity {
    private RecyclerView rvOrderList;
    private ArrayList<OrderModel> orderArrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    OrderAdapter orderAdapter;


    private Button btnBackToMain;

    String userID, orderID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rvOrderList = (RecyclerView) findViewById(R.id.rvOrderList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvOrderList.setLayoutManager(mLayoutManager);
        orderArrayList = new ArrayList<OrderModel>();
        orderAdapter = new OrderAdapter(orderArrayList, this);
        rvOrderList.setAdapter(orderAdapter);
        fetchData();
    }
    private void fetchData(){
        //_______add dummy status,order,date,price
        fStore.collection("users").document(userID).collection("orders").orderBy("address", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        orderArrayList.add(dc.getDocument().toObject(OrderModel.class));
                    }
                    else{
                        Log.e("Empty Data", "Order is empty");
                        return;
                    }
                }
                orderAdapter.notifyDataSetChanged();

            }
        });

    }
}