package com.example.androidapplication;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class BrandNewFormTest {

    //IT19211824
    //Test cases for Brand new form for ordering a new car
    @Rule
    public ActivityTestRule<BrandNewForm> bActivityTestRule = new ActivityTestRule<BrandNewForm>(BrandNewForm.class);
    private BrandNewForm bActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Checkout.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        bActivity = bActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(bActivity.findViewById(R.id.checkPay));
        onView(withId(R.id.checkPay)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,500);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        bActivity = null;
    }
}