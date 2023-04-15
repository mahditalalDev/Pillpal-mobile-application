package com.example.pillpal_v2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private TextInputLayout emailw;
    private TextInputLayout passwordw;
    private Button loginBtn;
    private TextView gotoRegister;

    private boolean valid = true;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private SharedPreferences sharedPreferences;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        gotoRegister = findViewById(R.id.gotoRegister);
        emailw = findViewById(R.id.textInputEmail);
        passwordw = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        EditText email = emailw.getEditText();
        EditText password = passwordw.getEditText();

        sharedPreferences = getSharedPreferences("PillPalPrefs", MODE_PRIVATE);

        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);
                if (valid) {
                    fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = authResult.getUser();
                                    if (user.isEmailVerified()) {
                                        checkUserAccessLevel(user.getUid());
                                    } else {
                                        Toast.makeText(Login.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("users").document(uid);

        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        String isPharma = documentSnapshot.getString("isPharma");
                        String isPatient = documentSnapshot.getString("isPatient");

                        if ("1".equals(isPharma)) {
                            if (sharedPreferences.getBoolean("isFirstTimePharmaLogin", true)) {
                                startActivity(new Intent(getApplicationContext(), PharmaInfo.class));
                                sharedPreferences.edit().putBoolean("isFirstTimePharmaLogin", false).apply();
                            } else {
                                startActivity(new Intent(getApplicationContext(), Pharmacy.class));
                            }
                            finish();
                        } else if ("1".equals(isPatient)) {
                            if (sharedPreferences.getBoolean("isFirstTimePatientLogin", true)) {
                                startActivity(new Intent(getApplicationContext(), PatientInfo.class));
                                sharedPreferences.edit().putBoolean("isFirstTimePatientLogin", false).apply();
                            } else {
                                startActivity(new Intent(getApplicationContext(), Patient.class));
                            }
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Unknown user access level", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Error retrieving user data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

;
}