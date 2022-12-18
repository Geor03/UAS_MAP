package umn.ac.id.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileToko extends AppCompatActivity {

    private Button btnBackToMain;
    private Button btnEditToProfile;

    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView email;

    protected FirebaseAuth fAuth;
    protected FirebaseFirestore fStore;
    String outletId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_toko);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        outletId = fAuth.getCurrentUser().getUid();

        name = findViewById(R.id.tvNamaToko);
        address = findViewById(R.id.tvAlamatToko);
        phone = findViewById(R.id.tvNomorToko);
        email = findViewById(R.id.tvEmailToko);

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnEditToProfile = findViewById(R.id.btnEditProfile);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnEditToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditProfile = new Intent(ProfileToko.this, EditProfile.class);
                startActivity(intentEditProfile);
                finish();
            }
        });

        DocumentReference documentReference = fStore.collection("outlets").document(outletId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                name.setText(documentSnapshot.getString("outlet_name"));
                address.setText(documentSnapshot.getString("address"));
                phone.setText("+62 "+documentSnapshot.getString("phone"));
                email.setText(documentSnapshot.getString("email"));
                //user = documentSnapshot.toObject(User.class);

            }
        });

    }
}