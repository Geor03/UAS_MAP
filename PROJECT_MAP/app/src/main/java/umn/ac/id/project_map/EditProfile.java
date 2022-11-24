package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfile extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText profileFirstName, profileLastName, profileEmail, profilePhone;
    ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String firstName = data.getStringExtra("firstName");
        String lastName = data.getStringExtra("lastName");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        profileFirstName = findViewById(R.id.profileFirstName);
        profileLastName = findViewById(R.id.profileLastName);
        profileEmail = findViewById(R.id.email);
        profilePhone = findViewById(R.id.profilePhoneNo);


        profileEmail.setText(email);
        profilePhone.setText(phone);
        profileLastName.setText(lastName);
        profileFirstName.setText(firstName);



        Log.d(TAG, "onCreate: " + firstName + " " + email + " "+ phone);

    }
}