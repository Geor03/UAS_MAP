package umn.ac.id.project_map;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

public class Profile extends AppCompatActivity {
    //    TextView fName, email, phone;

    EditText fName, email, phone;

    public String firstName, lastName, dbEmail, dbPhone;

    private Button btnBackToMain;

    protected FirebaseAuth fAuth;
    protected FirebaseFirestore fStore;
    String userID;

    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();


        reference = FirebaseFirestore.getInstance().collection("users").document(userID);

        phone = (EditText) findViewById(R.id.phone);
        fName = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

//        showAllUserData();


        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                dbPhone = documentSnapshot.getString("phoneNum");
                firstName = documentSnapshot.getString("fName");
                lastName = documentSnapshot.getString("lName");
                dbEmail = documentSnapshot.getString("email");
                phone.setText(dbPhone);
                fName.setText(firstName+" "+lastName);
                email.setText(dbEmail);
                //user = documentSnapshot.toObject(User.class);

            }
        });


        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToMain = new Intent(Profile.this, MainActivity.class);
                startActivity(intentBackToMain);
                finish();
            }
        });
    }

//    private void showAllUserData(){
//        Intent intent = getIntent();
//        _FIRSTNAME = intent.getStringExtra("firstName");
//        _EMAIL = intent.getStringExtra("Email");
//        _PHONENO = intent.getStringExtra("phoneNo");
//
//        email.setText(_EMAIL);
//        phone.setText(_PHONENO);
//        fName.setText(_FIRSTNAME);
//    }

    public void update(View view){

        if(isNameChanged()){
            Toast.makeText(this,"Data telah diupdate", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data gagal",Toast.LENGTH_LONG).show();
    }



    public boolean isNameChanged() {

//        if(!_FIRSTNAME.equals(fName.getEditableText().toString())){
        if(!firstName.equals(fName.getText().toString())){
            reference.update("fName",fName.getText().toString());//iini itu buat se ke DB nya
            return true;
        }
        else{
            return false;
        }
    }
}