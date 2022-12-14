package umn.ac.id.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String adminID;
    CheckBox mSave;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    Boolean save;

    String password;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignup);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mSave = (CheckBox) findViewById(R.id.savelogin);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        save = loginPreferences.getBoolean("saveLogin", false);

        if (save == true) {
            mEmail.setText(loginPreferences.getString("email", ""));
            mPassword.setText(loginPreferences.getString("password", ""));
            mSave.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = mPassword.getText().toString().trim();
                email = mEmail.getText().toString().trim();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEmail.getWindowToken(), 0);

                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                if (mSave.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", email);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

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
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            adminID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("outlets").document(adminID);
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.exists()) {
                                        Toast.makeText(Login.this, "Logged In Successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intentLogin = new Intent(Login.this, MainActivity.class);
                                        intentLogin.putExtra("Name", documentSnapshot.getString("outlet_name"));
                                        startActivity(intentLogin);
                                        finish();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Error!!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                    btnLogin.setEnabled(true);
                                    FirebaseAuth.getInstance().signOut();

                                }
                            });

                        }else{
                            Toast.makeText(Login.this, "Error!!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            btnLogin.setEnabled(true);
                            FirebaseAuth.getInstance().signOut();

                        }
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignup = new Intent(Login.this, SignUp.class);
                startActivity(intentSignup);
                finish();
            }
        });
    }
}