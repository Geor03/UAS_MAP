package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Receipt extends AppCompatActivity {
    private Button btnBackHome;
    private Button btnBackOrderList;

    String orderID;
    private TextView total_price, items, laundry, outlet_name, date;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        Intent intent = getIntent();
        orderID = intent.getStringExtra("order");
        total_price = findViewById(R.id.total_price);
        items = findViewById(R.id.items);
        laundry = findViewById(R.id.laundry);
        outlet_name = findViewById(R.id.outlet_name);
        date = findViewById(R.id.date);
        btnBackHome= findViewById(R.id.button_backHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Receipt.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnBackOrderList = findViewById(R.id.button_backToOrderList);
        btnBackOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToOrderList = new Intent(Receipt.this, Order_list.class);
                startActivity(intentBackToOrderList);
                finish();
            }
        });

        DocumentReference docref = fStore.collection("users").document(userID).collection("orders").document(orderID);
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                total_price.setText("Rp. "+String.valueOf(documentSnapshot.get("total_price")));
                items.setText(String.valueOf(documentSnapshot.getDouble("total_pants")+documentSnapshot.getDouble("total_shirts")));
                date.setText(format.format(documentSnapshot.getDate("date")));
                getOutlet(documentSnapshot.getString("outlet_id"));
            }
        });
    }

    private void getOutlet(String outlet_id) {
        DocumentReference documentReference = fStore.collection("outlets").document(outlet_id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                laundry.setText(documentSnapshot.getString("outlet_name"));
                outlet_name.setText(documentSnapshot.getString("outlet_name"));
            }
        });
    }
}