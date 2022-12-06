package umn.ac.id.project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private Button btnSingup;
    FirebaseAuth firebaseAuth;
    EditText mEmail, mPassword;
    CheckBox mSave;
    Boolean agree;
    SharedPreferences signupPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnSingup = (Button) findViewById(R.id.button_singup);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mSave = (CheckBox) findViewById(R.id.savelogin);
        firebaseAuth = FirebaseAuth.getInstance();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setEnabled(false);
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    btnLogin.setEnabled(true);
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    btnLogin.setEnabled(true);
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be >=  6 characters");
                    btnLogin.setEnabled(true);
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged In Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }else{
                            Toast.makeText(Login.this, "Error!!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            btnLogin.setEnabled(true);
                        }
                    }
                });
            }
        });

        btnSingup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
    }
}