package umn.ac.id.project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckOut extends AppCompatActivity {

    private Button button_back, button_trackOrder;
    private String orderID;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String Uid = fAuth.getUid();

    private TextView address, total_price, items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Intent intent = getIntent();
        orderID = intent.getStringExtra("OrderID");
        button_back = findViewById(R.id.button_back);
        button_trackOrder = findViewById(R.id.button_trackOrder);
        address = (TextView) findViewById(R.id.address);
        total_price = (TextView) findViewById(R.id.total_price);
        items = (TextView) findViewById(R.id.items);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHomeFromeCheckout = new Intent(CheckOut.this, MainActivity.class);
                startActivity(intentBackToHomeFromeCheckout);
                finish();
            }
        });
        button_trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToTrackOrder = new Intent(CheckOut.this, Track_Order.class);
                intentToTrackOrder.putExtra("order", orderID);
                startActivity(intentToTrackOrder);
                finish();
            }
        });
        DocumentReference docref = fStore.collection("users").document(Uid).collection("orders").document(orderID);
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    items.setText(String.valueOf(documentSnapshot.getString("total_pants")+documentSnapshot.getString("total_shirts"))+" items");
                    total_price.setText("Rp. "+documentSnapshot.getString("total_price"));
                    address.setText(documentSnapshot.getString("address"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Query", "Data ga keluar, CheckOut:70");
            }
        });
    }
}