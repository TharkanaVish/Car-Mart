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

public class paymentTest {

    //IT19167510
    //test cases for payment
    @Rule
    public ActivityTestRule<payment> pActivtyTestRule = new ActivityTestRule<payment>(payment.class);
    private payment pActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(paymentview.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        pActivity = pActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(pActivity.findViewById(R.id.proceed));
        onView(withId(R.id.proceed)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,500);
        assertNotNull(nextActivity);

        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        pActivity = null;

    }
}