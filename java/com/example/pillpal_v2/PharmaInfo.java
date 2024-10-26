package com.example.pillpal_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PharmaInfo extends AppCompatActivity {
    private EditText pharmaNameEditText, placeEditText;
    private EditText phoneNumber;
    private Button submitButton;
    private FirebaseFirestore db;
    String pharmaName,place,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_info);
        // Get Firestore instance
        db = FirebaseFirestore.getInstance();

        // Get views by ID
        pharmaNameEditText = findViewById(R.id.pharmaNameEditText);
        placeEditText = findViewById(R.id.address_input);
        phoneNumber = findViewById(R.id.phoneNumber);
        submitButton = findViewById(R.id.submitButton);

        // Set click listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                 pharmaName = pharmaNameEditText.getText().toString().trim();
                place = placeEditText.getText().toString().trim();
                 phone = phoneNumber.getText().toString().trim();
                email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                // Create a new document with email as the document ID
                Map<String, Object> pharmacyInfo = new HashMap<>();
                pharmacyInfo.put("pharmaName", pharmaName);
                pharmacyInfo.put("place", place);
                pharmacyInfo.put("phone", phone);

                // Add data to Firestore
                db.collection("pharmacyInfo").document(email)
                        .set(pharmacyInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Pharmacy Info added successfully.", Toast.LENGTH_SHORT).show();
                                // Open Pharmacy activity
                                startActivity(new Intent(getApplicationContext(),Pharmacy.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error adding Pharmacy Info. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });            }
        });
    }
}