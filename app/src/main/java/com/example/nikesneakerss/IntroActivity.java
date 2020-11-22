package com.example.nikesneakerss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class IntroActivity extends AppCompatActivity {
    private Button login;
    private Button sign_Up;
    ViewFlipper view_Flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        view_Flipper = findViewById(R.id.viewFlipper);
        int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
        for (int i = 0; i<images.length; i++){
            flipperImages(images[i]);
        }
        login = findViewById(R.id.btnIntroLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));

            }
        });
        sign_Up = findViewById(R.id.btnSignup);
        sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,RegisterActivity.class));

            }
        });


    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        view_Flipper.addView(imageView);
        view_Flipper.setFlipInterval(4000);
        view_Flipper.setAutoStart(true);
        view_Flipper.setInAnimation(this, android.R.anim.slide_in_left);
        view_Flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}