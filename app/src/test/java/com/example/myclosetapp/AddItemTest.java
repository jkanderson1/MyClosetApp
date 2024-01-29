package com.example.myclosetapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.widget.EditText;
import com.example.myclosetapp.AddItem;
import com.example.myclosetapp.AddItemActivity;
import com.example.myclosetapp.Clothing;
import com.google.android.material.textfield.TextInputEditText;
import org.junit.Before;
import org.junit.Test;

public class AddItemTest {

    private AddItem addItem;
    private AddItemActivity addItemActivity;

    @Before
    public void setUp() {
        addItemActivity = mock(AddItemActivity.class);
        addItem = new AddItem(addItemActivity);
    }

    @Test
    public void testGetColors() {
        // Mock TextInputEditText
        TextInputEditText colorInput = mock(TextInputEditText.class);
        when(addItemActivity.findViewById(R.id.addItemColorTextInputEditText)).thenReturn(colorInput);

        // Set up the text to be returned when getText is called
        String colorText = "Red";
        when(colorInput.getText()).thenReturn(new EditText(addItemActivity).getText().append(colorText));

        // Call the method to be tested
        addItem.getColors();

        // Verify that the correct methods are called
        verify(addItemActivity).findViewById(R.id.addItemColorTextInputEditText);
        verify(colorInput).getText();

        // Check if the colors are correctly set in the Clothing object
        assertEquals(1, addItem.getClothing().getColors().size());
        assertEquals(colorText, addItem.getClothing().getColors().get(0));
    }
}