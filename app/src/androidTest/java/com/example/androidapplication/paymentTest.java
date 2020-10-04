package com.example.androidapplication;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class paymentTest {

    //IT19167510
    //test cases for payment
    @Rule
    public ActivityTestRule<payment> pActivtyTestRule = new ActivityTestRule<payment>(payment.class);
    private payment pActivity = null;

    @Before
    public void setUp() throws Exception {
        pActivity = pActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(pActivity.findViewById(R.id.proceed));
    }

    @After
    public void tearDown() throws Exception {
    }
}