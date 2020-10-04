package com.example.androidapplication;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrandNewFormTest {

    @Rule
    public ActivityTestRule<BrandNewForm> bActivityTestRule = new ActivityTestRule<BrandNewForm>(BrandNewForm.class);

    private BrandNewForm bActivity = null;

    @Before
    public void setUp() throws Exception {
        bActivity = bActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(bActivity.findViewById(R.id.checkPay));
    }

    @After
    public void tearDown() throws Exception {
        bActivity = null;
    }
}