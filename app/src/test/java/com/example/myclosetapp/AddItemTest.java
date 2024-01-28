package com.example.myclosetapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.text.Editable;
import com.google.android.material.textfield.TextInputEditText; // Make sure to import the correct class
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
        TextInputEditText colorInput = mock(TextInputEditText.class);
        when(addItemActivity.findViewById(R.id.addItemColorTextInputEditText)).thenReturn(colorInput); // Corrected the ID

        String colorText = "Red";
        when(colorInput.getText()).thenReturn(new Editable.Factory().newEditable(colorText));

        addItem.getColors();

        // Verifying that the method is called
        verify(addItemActivity).findViewById(R.id.addItemColorTextInputEditText); // Corrected the ID
        verify(colorInput).getText();

        assertEquals(1, addItem.getClothing().getColors().size());
        assertEquals(colorText, addItem.getClothing().getColors().get(0));
    }

    @Test
    public void testGetColorsWithNullText() {
        TextInputEditText colorInput = mock(TextInputEditText.class);
        when(addItemActivity.findViewById(R.id.addItemColorTextInputEditText)).thenReturn(colorInput); // Corrected the ID

        when(colorInput.getText()).thenReturn(null);

        addItem.getColors();

        verify(addItemActivity).findViewById(R.id.addItemColorTextInputEditText); // Corrected the ID
        verify(colorInput).getText();

        assertTrue(addItem.getClothing().getColors().isEmpty());
    }
}