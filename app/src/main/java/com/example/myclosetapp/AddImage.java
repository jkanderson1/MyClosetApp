package com.example.myclosetapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

//working add image
public class AddImage extends AppCompatActivity {

    Closet closet = Closet.getInstance();
    StorageReference storageReference;
    LinearProgressIndicator progressIndicator;
    Uri image;
    MaterialButton uploadImage, selectImage;
    Button addItem, Back;
    ImageView imageView;
    //Method to inform the user to upload image
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    uploadImage.setEnabled(true);
                    image = result.getData().getData();
                    Glide.with(getApplicationContext()).load(image).into(imageView);
                }
            } else {
                Toast.makeText(AddImage.this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }

    });


    //method which holds the relationship between firebase and the screen
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        FirebaseApp.initializeApp(AddImage.this);
        storageReference = FirebaseStorage.getInstance().getReference();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressIndicator = findViewById(R.id.progress);
        //all buttons used
        imageView = findViewById(R.id.imageView);
        selectImage = findViewById(R.id.selectImage);
        uploadImage = findViewById(R.id.uploadImage);
        addItem = findViewById(R.id.addItem);

        //method that stores the item that the user has selected in firebase inside of the image folder
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        //method that directs the user to the next screen after uploading their image
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                startActivity(intent);
                finish();
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(image);
            }
        });
    }
    //method that upload the image from the users device to firebase and displays a message after the image is uploaded successfully
    //the method also will display a message if the image does not upload
    //
    void uploadImage(Uri file) {
        String id = UUID.randomUUID().toString();
        StorageReference ref = storageReference.child("images/" + id);
        ref.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                addPictureIDtoItem(id);
                Toast.makeText(AddImage.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddImage.this, "Failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressIndicator.setMax(Math.toIntExact(taskSnapshot.getTotalByteCount()));
                progressIndicator.setProgress(Math.toIntExact(taskSnapshot.getBytesTransferred()));
            }
        });

        Back = (Button) findViewById(R.id.backButton);
        //allow the user to return to the homescreen after pressing the back button
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Adds the ID of the picture to allow the picture to be shown again on later screens
    private void addPictureIDtoItem(String picID){
        Intent intent = getIntent();
        String name = getResources().getString(R.string.IDtoPass);
        String[] IdAndType= intent.getStringExtra(name).split(":");
        closet.addPictureID(IdAndType[0],IdAndType[1],picID);
    }
}