package com.example.myclosetapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Closet {
    private DatabaseReference closet = FirebaseDatabase.getInstance().getReference();
    private static Closet instance = new Closet();
    private ArrayList<String> ids;
    private Closet(){
        ids = new ArrayList<>();
    }

    public static Closet getInstance(){
        if (instance == null){
            instance = new Closet();
        }
        return instance;
    }
    public boolean addClothes(@NonNull Clothing item){
        final boolean[] success = {false};
        closet.child(item.getType()).child(item.getIdentification()).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ids.add(item.getIdentification());
                success[0] = true;
            }
        });
        return success[0];
    }
}
