package umn.ac.id.project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private Button btnRegisterAccount, mLoginBtn;
    EditText mFirstName, mLastName, mEmail, mPassword, mPNumber, mConfirm;
    CheckBox mAgree;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Boolean agree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnRegisterAccount = (Button) findViewById(R.id.button_registerAccount);
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mEmail = (EditText) findViewById(R.id.email);
        mPNumber = (EditText) findViewById(R.id.phone);
        mPassword = (EditText) findViewById(R.id.password);
        mConfirm = (EditText) findViewById(R.id.confirm_password);
        mAgree = (CheckBox) findViewById(R.id.agree);
        mLoginBtn = findViewById(R.id.button_singup);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }


        btnRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = mPassword.getText().toString().trim();
                String confirm = mConfirm.getText().toString().trim();
                String firstname = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String phone = mPNumber.getText().toString().trim();

                if(TextUtils.isEmpty(confirm)){
                    mConfirm.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(firstname)){
                    mFirstName.setError("Name is required");
                    return;

                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;

                }
                if(TextUtils.isEmpty(phone)){
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
                if (mAgree.isChecked()) {

                    if(password.equals(confirm)){
                        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                                    userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("fName", firstname);
                                    user.put("lName", lastName);
                                    user.put("email", email);
                                    user.put("phoneNum",phone);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG","onSuccess: user profile is created for"+userID);
                                        }
                                        public void onFailure(@NonNull Exception e){
                                            Log.d("TAG","onFailure:"+e.toString());
                                        }
                                    });
                                    startActivity(new Intent(SignUp.this, MainActivity.class));
                                }
                                else{
                                    Toast.makeText(SignUp.this, "Error!!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(SignUp.this, "Konfirmasi password tidak tepat", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}