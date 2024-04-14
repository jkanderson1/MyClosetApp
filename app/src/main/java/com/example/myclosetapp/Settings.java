package com.example.myclosetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class Settings extends AppCompatActivity {

    public Button Preferences, logout, Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Preferences = findViewById(R.id.PrefButton);
        Preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Preferences.class);
                startActivity(intent);
                finish();
            }
        });
        logout = findViewById(R.id.LogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Intent intent  = new Intent(getApplicationContext(),
                        OpeningScreen.class); */
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Settings.this, OpeningScreen.class));
            }
        });

        Back = findViewById(R.id.backButton);
       Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Settings.this, HomeScreen.class);
               startActivity(intent);
               finish();
            }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



