package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
    private Button btnRegisterAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnRegisterAccount = (Button) findViewById(R.id.button_registerAccount);

        btnRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenRegisterAccount = new Intent(SignUp.this, Login.class);
                startActivity(intenRegisterAccount);
            }
        });
    }
}