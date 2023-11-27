package com.example.mycloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {

    Button account, preferences, logout, deleteAccount;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        account.findViewById(R.id.AccountButton);
        preferences.findViewById(R.id.AccountButton);
        logout.findViewById(R.id.LogoutButton);
        deleteAccount.findViewById(R.id.deleteAccount);

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), Logout.class);
                startActivity(intent3);
                finish();
            }
        });
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), DeleteAccount.class);
                startActivity(intent3);
                finish();
            }
        });
             FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
             user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(),
                        "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent =
                        new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(Settings.this,
                        "Deletion Filed.", Toast.LENGTH_SHORT).show();
                    }
                 }
            });

    }


}