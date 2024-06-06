package com.example.ubycalls;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.ubycalls", appContext.getPackageName());
    }

    @Test
    public void testCorrectLogin() {
        // Type text and then press the button.
        onView(withId(R.id.etUsername)).perform(typeText("correctUsername"));
        onView(withId(R.id.etPassword)).perform(typeText("correctPassword"));
        onView(withId(R.id.btnLogin)).perform(click());

        // Check if the result is displayed.
        onView(withId(R.id.result)).check(matches(withText("Login Success")));
    }

    @Test
    public void testWrongUsername() {
        onView(withId(R.id.etUsername)).perform(typeText("wrongUsername"));
        onView(withId(R.id.etPassword)).perform(typeText("correctPassword"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.result)).check(matches(withText("Login Failed")));
    }

    @Test
    public void testWrongPassword() {
        onView(withId(R.id.etUsername)).perform(typeText("correctUsername"));
        onView(withId(R.id.etPassword)).perform(typeText("wrongPassword"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.result)).check(matches(withText("Login Failed")));
    }

    @Test
    public void testWrongCredentials() {
        onView(withId(R.id.etUsername)).perform(typeText("wrongUsername"));
        onView(withId(R.id.etPassword)).perform(typeText("wrongPassword"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.result)).check(matches(withText("Login Failed")));
    }
}
