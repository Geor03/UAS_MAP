package umn.ac.id.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    private Button btnBackToProfile;
    private Button btnSaveProfile;

    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView email;
    private Button save;

    protected FirebaseAuth fAuth;
    protected FirebaseFirestore fStore;
    String outletId;

    DocumentReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        outletId = fAuth.getCurrentUser().getUid();

        reference = FirebaseFirestore.getInstance().collection("outlets").document(outletId);

        name = (EditText) findViewById(R.id.etNamaToko);
        address = (EditText) findViewById(R.id.etAlamatToko);
        phone = (EditText) findViewById(R.id.etNomorToko);
        email = (EditText) findViewById(R.id.etEmailToko);
        save = (Button) findViewById(R.id.btnSaveProfile);

        btnBackToProfile = findViewById(R.id.btnBackToPorfile);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        btnBackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToProfile = new Intent(EditProfile.this, ProfileToko.class);
                startActivity(intentBackToProfile);
                finish();
            }
        });

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSaveProfile = new Intent(EditProfile.this, ProfileToko.class);
                startActivity(intentSaveProfile);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update(view);
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
    public void Update(View view){

        Map<String, Object> outlet = new HashMap<>();
        outlet.put("outlet_name", name.getText().toString());
        outlet.put("address", address.getText().toString());
        outlet.put("email", email.getText().toString());
        outlet.put("phone", phone.getText().toString());

        fStore.collection("outlets").document(outletId).update(outlet).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditProfile.this, "Data telah diupdate", Toast.LENGTH_LONG).show();
                notifyAll();
            }
            public void onFailure(Void unused){
                Toast.makeText(EditProfile.this, "Data gagal",Toast.LENGTH_LONG).show();

            }
        });
    }
}