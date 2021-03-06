package com.example.nikesneakerss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView FirstName;
    private TextView LastName;
    private TextView Email;
    private TextView DateOfBirth;
    private Button btnUpdate;
    private FirebaseAuth firebaseAuth;
    private Button password;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirstName = (TextView)findViewById(R.id.tvAPN);
        LastName = (TextView) findViewById(R.id.tvALM);
        Email = (TextView) findViewById (R.id.tvAEML);
        DateOfBirth = (TextView) findViewById(R.id.tvADOB);
        btnUpdate = findViewById(R.id.btnAccUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UpdateProfile.class));
            }
        });
        password = findViewById(R.id.btnChangePassword);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UpdatePassword.class));
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                FirstName.setText(userProfile.getFirstName());
                LastName.setText(userProfile.getLastName());
                Email.setText(userProfile.getEmailAddress());
                DateOfBirth.setText(userProfile.getDateOfBirth());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}