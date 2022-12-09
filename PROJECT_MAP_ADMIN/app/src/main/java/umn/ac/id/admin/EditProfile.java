package umn.ac.id.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfile extends AppCompatActivity {

    private Button btnBackToProfile;
    private Button btnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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
    }
}