package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private Button btnSingup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnSingup = (Button) findViewById(R.id.button_singup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoginToHome = new Intent(Login.this, MainActivity.class);
                startActivity(intentLoginToHome);
            }
        });

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSingUp = new Intent(Login.this, SignUp.class);
                startActivity(intentSingUp);
            }
        });
    }
}