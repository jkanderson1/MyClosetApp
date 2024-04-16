package com.example.myclosetapp;

import android.net.Uri;

import junit.framework.TestCase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddImageTest extends TestCase {

    @Mock
    private StorageReference mockStorageReference;

    @Mock
    private UploadTask.TaskSnapshot mockTaskSnapshot;

    @Captor
    private ArgumentCaptor<OnSuccessListener<UploadTask.TaskSnapshot>> successListenerCaptor;

    private AddImage addImageActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        // Create an instance of AddImage
        addImageActivity = new AddImage();

        // Assign the mock StorageReference
        addImageActivity.storageReference = mockStorageReference;
    }

    public void testUploadImageSuccess() {
        // Mock a Uri for testing
        Uri mockUri = Uri.parse("content://mock/image");

        // Call the uploadImage method with the mock Uri
        addImageActivity.uploadImage(mockUri);

        // Verify that putFile method is called with the expected Uri
        verify(mockStorageReference).putFile(any(Uri.class));

        // Capture and trigger the onSuccess listener passed to putFile
        verify(mockStorageReference).putFile(any(Uri.class), (StorageMetadata) successListenerCaptor.capture());
        successListenerCaptor.getValue().onSuccess(mockTaskSnapshot);

        // Assert any expected behavior after successful upload
        // For example, you can check that a success message is displayed via Toast
        // (Assuming your implementation shows a Toast message for successful upload)
        assertEquals("Image Uploaded!!", ShadowToast.latestToastText);
    }

    // Helper class to simulate Toast messages in unit tests
    private static class ShadowToast {
        private static String latestToastText;

        static void makeText(AddImage addImageActivity, String text, int duration) {
            latestToastText = text;
        }
    }
}