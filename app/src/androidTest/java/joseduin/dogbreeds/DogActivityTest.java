package joseduin.dogbreeds;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DogActivityTest {

    @Rule
    public ActivityTestRule<DogsActivity> mActivityTestRule = new ActivityTestRule<>(DogsActivity.class);

    @Test
    public void dogActivityTest() {
        ViewInteraction relativeLayout = onView(
                allOf(withParent(allOf(withId(R.id.slideBreed),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0))),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerImages),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(31, click()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navegar hacia arriba"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navegar hacia arriba"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.collapsingToolbar),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.slideBreed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerBreed),
                                        1),
                                0),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction relativeLayout2 = onView(
                allOf(withParent(allOf(withId(R.id.slideBreed),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0))),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerImages),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imagePreview),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imagePreview),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
