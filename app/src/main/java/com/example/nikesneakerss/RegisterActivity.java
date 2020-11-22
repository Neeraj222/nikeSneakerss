package com.example.nikesneakerss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstName, lastName, emailAddress, password, dateOfBirth;
    private Button join_Us;
    private Button login;
    private FirebaseAuth firebaseAuth;
    String FirstName, LastName, email, Password, DateOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();



        join_Us.setOnClickListener(view -> {
            if(validate()){
                String user_Email = emailAddress.getText().toString().trim();
                String user_Password = password.getText().toString().trim();


                firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        sendEmailVerification();
                        Toast.makeText(RegisterActivity.this,"Account Created, Data Uploaded",Toast.LENGTH_LONG).show();

                        finish();
                        startActivity(new Intent(RegisterActivity.this, IntroActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
    private void setupUIViews(){
        firstName = findViewById(R.id.tvRegisterFirstName);
        lastName =  findViewById(R.id.tvRegisterLastName);
        emailAddress = findViewById(R.id.tvRegisterEmail);
        password =  findViewById(R.id.tvRegisterPassword);
        dateOfBirth = findViewById(R.id.tvDateOfBirth);
        join_Us =  findViewById(R.id.BtnRegisterJoinUs);

    }
    private Boolean validate(){
        Boolean result = false;

        FirstName = firstName.getText().toString();
        LastName = lastName.getText().toString();
        email = emailAddress.getText().toString();
        Password = password.getText().toString();
        DateOfBirth = dateOfBirth.getText().toString();
        if(email.isEmpty() || Password.isEmpty() || FirstName.isEmpty() || LastName.isEmpty() || DateOfBirth.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show();
        }else{
            result = true;
        }
            return result;
    }

    
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser !=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    sendUserData();
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Account Created, Please Check Your Email",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegisterActivity.this, IntroActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Error Sending Verification Email",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    private void sendUserData(){
        Log.d("register", "Sending user data...");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(FirstName, LastName, email, DateOfBirth);
        myRef.setValue(userProfile).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("register", "Successfully sent user data");
            } else {
                Log.d("register", "Failed to send user data");
            }
        });
    }

}