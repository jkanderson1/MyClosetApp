package com.example.myclosetapp;

import static org.mockito.Mockito.when;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    @Mock
    FirebaseAuth mockAuth;
    @Mock
    FirebaseUser mockUser;

    private Login loginActivity;

    @Before
    public void setUp() {
        loginActivity = new Login();
        loginActivity.mAuth = mockAuth;
    }

    @Test
    public void onStart_userNotNull_shouldStartHomeScreenActivity() {
        when(mockAuth.getCurrentUser()).thenReturn(mockUser);

        loginActivity.onStart();

        // Verify that startActivity was called with the expected intent
        Mockito.verify(loginActivity).startActivity(Mockito.any(Intent.class));
        // Verify that finish was called
        Mockito.verify(loginActivity).finish();
    }

    @Test
    public void onStart_userNull_shouldNotStartHomeScreenActivity() {
        when(mockAuth.getCurrentUser()).thenReturn(null);

        loginActivity.onStart();

        // Verify that startActivity was not called
        Mockito.verify(loginActivity, Mockito.never()).startActivity(Mockito.any(Intent.class));
        // Verify that finish was not called
        Mockito.verify(loginActivity, Mockito.never()).finish();
    }
}