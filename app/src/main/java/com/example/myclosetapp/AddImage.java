package com.example.myclosetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclosetapp.databinding.ActivityAddImageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.FirebaseStorageKtxRegistrar;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddImage extends AppCompatActivity {

    ActivityAddImageBinding binding;
    Uri imageUri;
    StorageReference storageReference;

    ProgressDialog progressDialog;
    Button next;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        binding = ActivityAddImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        next.findViewById(R.id.NextButton);

        binding.selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectImage();
            }

        });

        binding.uploadImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddItem.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void uploadImage() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/" +fileName);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File...");
        progressDialog.show();

        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        binding.firebaseImage.setImageURI(null);
                        Toast.makeText(AddImage.this,"Successfully Uploaded", Toast.LENGTH_SHORT);
                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(AddImage.this,"Failed to Upload", Toast.LENGTH_SHORT);

                    }
                });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("Image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        int requestCode = 0;
        startActivityForResult(intent, requestCode = 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            binding.firebaseImage.setImageURI(imageUri);
        }
    }



}
