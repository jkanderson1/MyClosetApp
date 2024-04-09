package InventoryEspressoTest;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myclosetapp.Inventory;
import com.example.myclosetapp.R;
import com.example.myclosetapp.Settings;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
public class InventoryTest {
    @Rule
    public ActivityScenarioRule<Inventory> activityRule =
            new ActivityScenarioRule<Inventory>(Inventory.class);

    @Test
    public void SettingsButtonWorks()
    {
        Intents.init();
        onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click());
        intended(hasComponent(Settings.class.getName()));

        Intents.release();
    }

}
