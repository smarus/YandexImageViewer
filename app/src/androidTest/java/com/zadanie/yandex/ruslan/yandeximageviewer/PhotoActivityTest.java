package com.zadanie.yandex.ruslan.yandeximageviewer;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zadanie.yandex.ruslan.yandeximageviewer.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class PhotoActivityTest {
	@Rule
	public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void shouldBeAbleToLaunchMainScreen() {
		onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
		onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
	}

	@Test
	public void shouldBeAbleToScrollViewPhoto() throws InterruptedException {
		Thread.sleep(3000);
		onView(withId(R.id.list_photos)).perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
	}
}
