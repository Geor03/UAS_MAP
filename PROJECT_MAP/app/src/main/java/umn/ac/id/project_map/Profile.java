package umn.ac.id.project_map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {
    //    TextView fName, email, phone;
    TextView email, phone;
    EditText fName;


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID,_FIRSTNAME, _EMAIL, _PHONENO;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");


        phone = (TextView) findViewById(R.id.phone);
        fName = (EditText) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);

//        showAllUserData();


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                phone.setText(documentSnapshot.getString("phoneNum"));
                fName.setText(documentSnapshot.getString("fName")+" "+documentSnapshot.getString("lName"));
                email.setText(documentSnapshot.getString("email"));
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
        if(!_FIRSTNAME.equals(fName.getText().toString())){
            reference.child(userID).child("name").setValue(fName.getEditableText().toString());
            _FIRSTNAME = fName.getEditableText().toString();
            return true;
        }
        else{
            return false;
        }
    }
}