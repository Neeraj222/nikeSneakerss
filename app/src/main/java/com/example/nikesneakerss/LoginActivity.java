package com.example.nikesneakerss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.etLoginEmail);
        Password = findViewById(R.id.etLoginPassword);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if(user != null){
//            finish();
//            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//        }

        Login = findViewById(R.id.btnSingIn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });


    }
    private void validate(String userEmail, String userPassword){
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEmailVerification();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Failed, Please Try Again", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();
        if(emailFlag){
            finish();
            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
        }else{
            Toast.makeText(this,"Please Verify Your Email",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}