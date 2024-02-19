package com.example.myclosetapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Settings extends AppCompatActivity {
    private Button saveChanges, logout;
    private EditText newUsername, newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize UI elements
        saveChanges = findViewById(R.id.saveChangesButton);
        logout = findViewById(R.id.logoutButton);
        newUsername = findViewById(R.id.editTextNewUsername);
        newPassword = findViewById(R.id.editTextNewPassword);

        // Save Changes Button Click Listener
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered new username and password
                String updatedUsername = newUsername.getText().toString().trim();
                String updatedPassword = newPassword.getText().toString().trim();

                // Get the current Firebase user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // Check if the user is logged in
                if (user != null) {
                    // Update the display name (username)
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(updatedUsername)
                            .build();
                    user.updateProfile(profileUpdates);

                    // Update the password if provided
                    if (!updatedPassword.isEmpty()) {
                        user.updatePassword(updatedPassword)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Settings.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Settings.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                    // Notify the user about the changes
                    Toast.makeText(Settings.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out the user and redirect to the opening screen
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Settings.this, OpeningScreen.class));
                finish(); // Close the current activity
            }
        });
    }
}