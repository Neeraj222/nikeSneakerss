package com.example.nikesneakerss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail, userPassword, userFirstName, userLastName;
    private Button joinUs;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();
        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //database
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
            }
        });
    }
    private void setupUIViews(){
        userEmail = (EditText)findViewById(R.id.tvRegisterEmail);
        userPassword = (EditText) findViewById(R.id.tvRegisterPassword);
        userFirstName = (EditText)findViewById(R.id.tvRegisterFirstName);
        userLastName = (EditText) findViewById(R.id.tvRegisterLastName);
        joinUs = (Button) findViewById(R.id.BtnRegisterJoinUs);

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