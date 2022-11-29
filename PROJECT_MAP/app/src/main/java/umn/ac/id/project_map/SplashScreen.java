package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intentSplashScreen = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intentSplashScreen);
                    finish();
                }
                else{
                    Intent intentSplashScreen = new Intent(SplashScreen.this, Login.class);
                    startActivity(intentSplashScreen);
                    finish();
                }
            }
        }, 3000);


    }
}