package com.example.tafebotappfinalversion;


import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BluetoothConnActivityTest {



    @Rule
    public ActivityTestRule<BluetoothConnActivity> mActivityTestRule = new ActivityTestRule<>(BluetoothConnActivity.class);


    @Test
    public void bluetoothConnActivityTestPass() {
        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("GET PAIRED DEVICES"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    }

    @Test
    public void bluetoothConnActivityTestFail() {
        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("GET DEVICES"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    }

    @Test
    public void bluetoothConnActivityTest2() {

        ViewInteraction listView = onView(
                allOf(withId(R.id.listView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));
    }

    @Test
    public void bluetoothConnActivityTest3() {

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_view), withText("Paired Devices"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Paired Devices")));

    }

    @Test
    public void FindItemsInTheListPass() throws Exception {
        ListView listview = (ListView) mActivityTestRule.getActivity().findViewById(R.id.listView);

        assertThat(listview.getCount(), is(0));
    }

    //Should have no items
    @Test
    public void FindItemsInTheListFail() throws Exception {
        ListView listview = (ListView) mActivityTestRule.getActivity().findViewById(R.id.listView);

        assertThat(listview.getCount(), is(1));
    }



}
