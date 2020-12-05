package com.example.tafebotappfinalversion;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Instrumentation.ActivityMonitor;

import androidx.test.core.app.ActivityScenario;

import java.io.IOException;

import static java.util.regex.Pattern.matches;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ServoControlMainTest {

//    private String stringToBetyped;
//
//    @Rule
//    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MyActivity.class);
//
//    @Test
//    public void myTest() {
//        ActivityScenario scenario = rule.getScenario();
//        // Your test code goes here.
//    }

        @Test
    public void testIntent(){
        assertTrue("true != true", true);
        assertFalse("false != false", false);

        final String extraName = "IamAnExtra";
        final String extraValue = "IamAValue";
        Intent intent = new Intent();
        intent.putExtra(extraName, extraValue);

        assertTrue("hasExtra==false", intent.hasExtra(extraName));
    }

    @Test
    public void activityResult_DisplaysContactsPhoneNumber() {
        // Build the result to return when the activity is launched.
        Intent resultData = new Intent();
        String phoneNumber = "123-345-6789";
        resultData.putExtra("phone", phoneNumber);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(toPackage("com.android.contacts")).respondWith(result);

        // User action that results in "contacts" activity being launched.
        // Launching activity expects phoneNumber to be returned and displayed.
        onView(withId(R.id.pickButton)).perform(click());

        // Assert that the data we set up above is shown.
        onView(withId(R.id.phoneNumber)).check(matches(withText(phoneNumber)));
    }


    }

