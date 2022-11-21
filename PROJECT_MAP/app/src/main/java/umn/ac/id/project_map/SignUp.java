package umn.ac.id.project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private Button btnRegisterAccount, mLoginBtn;
    EditText mFirstName, mLastName, mEmail, mPassword, mUsername, mConfirm;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnRegisterAccount = (Button) findViewById(R.id.button_registerAccount);
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mEmail = (EditText) findViewById(R.id.email);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mConfirm = (EditText) findViewById(R.id.confirm_password);
        mLoginBtn = findViewById(R.id.button_singup);

        fAuth = FirebaseAuth.getInstance();



        btnRegisterAccount.setOnClickListener(new View.OnClickListener() {
            String password;
            @Override
            public void onClick(View view) {
                    password = mPassword.getText().toString().trim();

                String firstname = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String username = mUsername.getText().toString().trim();

                if(TextUtils.isEmpty(firstname)){
                    mFirstName.setError("Name is required");
                    return;

                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;

                }
                if(TextUtils.isEmpty(username)){
                    mFirstName.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be >=  6 characters");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, Login.class));
                        }
                        else{
                            Toast.makeText(SignUp.this, "Error!!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                Intent intenRegisterAccount = new Intent(SignUp.this, Login.class);
//                startActivity(intenRegisterAccount);
            }
        });

//        mLoginBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(getApplicationContext(),Login.class));
//            }
//        });
        

    }
}