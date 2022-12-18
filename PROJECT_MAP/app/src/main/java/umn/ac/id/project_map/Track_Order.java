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
import com.google.firestore.admin.v1.Index;

import java.text.SimpleDateFormat;

public class Track_Order extends AppCompatActivity {

    private Button button_back;
    private View Order_Received;
    private View Ongoing;
    private View Finished;
    private TextView date;
    private TextView outlet_name;

    private String orderID, outletid;
    private String status;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String Uid = fAuth.getUid();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        Intent intent = getIntent();
        orderID = intent.getStringExtra("order");

        outlet_name = findViewById(R.id.outlet_name);
        date = findViewById(R.id.date);
        Order_Received = findViewById(R.id.view);
        Ongoing = findViewById(R.id.view2);
        Finished = findViewById(R.id.view3);
        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHomeFromTrackOder = new Intent(Track_Order.this, MainActivity.class);
                startActivity(intentBackToHomeFromTrackOder);
                finish();
            }
        });

        DocumentReference docref = fStore.collection("users").document(Uid).collection("orders").document(orderID);
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    date.setText(format.format(documentSnapshot.getString("date")));
                    status = (documentSnapshot.getString("status"));
                    outletid = (documentSnapshot.getString("outlet_id"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Query", "Data ga keluar, Track Order:75");
            }
        });
        DocumentReference documentReference = fStore.collection("outlets").document(outletid).collection("orders").document(orderID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    outlet_name.setText(documentSnapshot.getString("outlet_name"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Query", "Data ga keluar, Track Order:89");
            }
        });
        setStatus(status);
    }

    private void setStatus(String status) {
        if(status.equals("Ongoing")){
            Order_Received.setBackgroundResource(R.drawable.custom_shape_status_completed);
            Ongoing.setBackgroundResource(R.drawable.custom_shape_status_current);
            Finished.setBackgroundResource(R.drawable.custom_shape_status_remaining);
        }
        else if(status.equals("Finished")){
            Order_Received.setBackgroundResource(R.drawable.custom_shape_status_completed);
            Ongoing.setBackgroundResource(R.drawable.custom_shape_status_completed);
            Finished.setBackgroundResource(R.drawable.custom_shape_status_completed);
        }
    }
}