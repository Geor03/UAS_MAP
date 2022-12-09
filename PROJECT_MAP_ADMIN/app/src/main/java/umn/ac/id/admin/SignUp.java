package umn.ac.id.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    private Button btnRegisAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegisAcc = findViewById(R.id.btnRegisterAccount);

        btnRegisAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegisAccount = new Intent(SignUp.this, Login.class);
                startActivity(intentRegisAccount);
                finish();
            }
        });
    }
}