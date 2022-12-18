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

public class Coupon_list extends AppCompatActivity {

    private Button btnBackToMain;
    private RecyclerView rvHorizontal;
    private ArrayList<CouponModel> couponArrayList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    CouponAdapter couponAdapter;

    String userID, orderID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        fStore = FirebaseFirestore.getInstance();
        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHomeFromCouponList = new Intent(Coupon_list.this, MainActivity.class);
                startActivity(intentBackToHomeFromCouponList);
                finish();
            }
        });

        rvHorizontal = (RecyclerView) findViewById(R.id.rvCouponList);
        mLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHorizontal.setLayoutManager(mLayoutManagerHorizontal);
        couponArrayList = new ArrayList<CouponModel>();
        couponAdapter = new CouponAdapter(this, couponArrayList);
        rvHorizontal.setAdapter(couponAdapter);
        EventChangeListener();
    }
    private void EventChangeListener(){
        fStore.collection("vouchers").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        couponArrayList.add(dc.getDocument().toObject(CouponModel.class));
                    }

                    couponAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}