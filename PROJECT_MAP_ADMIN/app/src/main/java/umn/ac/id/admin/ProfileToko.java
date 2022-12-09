package umn.ac.id.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileToko extends AppCompatActivity {

    private Button btnBackToMain;
    private Button btnEditToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_toko);

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnEditToProfile = findViewById(R.id.btnEditProfile);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHome = new Intent(ProfileToko.this, MainActivity.class);
                startActivity(intentBackToHome);
                finish();
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
    }
}