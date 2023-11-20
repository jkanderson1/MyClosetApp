package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    Button account, preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        account.findViewById(R.id.AccountButton);
        preferences.findViewById(R.id.AccountButton);

        account.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Preferences.class);
                startActivity(intent2);
                finish();
            }
        });
    }


}