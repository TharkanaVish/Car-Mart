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
import static org.junit.Assert.assertNotNull;

//IT19118246
public class ListCarViewTest {
    @Rule
    public ActivityTestRule<ListCarView> mActivityTestRule = new ActivityTestRule<ListCarView>(ListCarView.class);
    private ListCarView mActivity = null;
    Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(ListCarView.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity(); }

    //IT19118246-Test to check the button click event of addFabButton in the ListCarViewActivity
    @Test
    public void testLaunchOfListCarViewOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.addFabButton));
        onView(withId(R.id.addFabButton)).perform(click());
        Activity nextActivity=getInstrumentation().waitForMonitorWithTimeout(monitor,50000);
        assertNotNull(nextActivity); }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }}