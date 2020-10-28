package com.example.nikesneakerss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail, userPassword, userFirstName, userLastName;
    private Button join_Us;
    private Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();



        join_Us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String user_Email = userEmail.getText().toString().trim();
                    String user_Password = userPassword.getText().toString().trim();
                    String user_firstName = userFirstName.getText().toString().trim();
                    String user_lastName = userLastName.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Welcome To The Nike Family", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this,"Error Signing Up", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

    }
    private void setupUIViews(){
        userEmail = (EditText)findViewById(R.id.tvRegisterEmail);
        userPassword = (EditText) findViewById(R.id.tvRegisterPassword);
        userFirstName = (EditText)findViewById(R.id.tvRegisterFirstName);
        userLastName = (EditText) findViewById(R.id.tvRegisterLastName);
        join_Us = (Button) findViewById(R.id.BtnRegisterJoinUs);

    }
    private Boolean validate(){
        Boolean result = false;
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String firstName = userFirstName.getText().toString();
        String lastName = userLastName.getText().toString();
        if(email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show();
        }else{
            result = true;
        }
            return result;
    }

}