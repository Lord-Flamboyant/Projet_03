
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.design.animation.Positioning;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

/***test instrumentalisé */

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }
    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    /***Test for watching if detail open when clicked */
    @Test
    public void neighbourDetailOpen() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    /***Test for matches if names is good*/
    @Test
    public void nameDetailNeighbourIsGood() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.titleNameNeighbour_view), withText("Jack"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Jack")));
    }

    /***Test for watchinf if on favorites tab is only favorites neighbours*/
    @Test
    public void favtabHaveOnlyFav() {
            ViewInteraction recyclerView = onView(
                    allOf(withId(R.id.list_neighbours), isDisplayed(),
                            withParent(withId(R.id.container))));
            recyclerView.perform(actionOnItemAtPosition(0, click()));

            ViewInteraction floatingActionButton = onView(
                    allOf(withId(R.id.addFav_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    2),
                            isDisplayed()));
            floatingActionButton.perform(click());

            ViewInteraction appCompatImageButton = onView(
                    allOf(withId(R.id.BackButton_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    1),
                            isDisplayed()));
            appCompatImageButton.perform(click());

            ViewInteraction recyclerView2 = onView(
                    allOf(withId(R.id.list_neighbours), isDisplayed(),
                            withParent(withId(R.id.container))));
            recyclerView2.perform(actionOnItemAtPosition(3, click()));

            ViewInteraction floatingActionButton2 = onView(
                    allOf(withId(R.id.addFav_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    2),
                            isDisplayed()));
            floatingActionButton2.perform(click());

            ViewInteraction appCompatImageButton2 = onView(
                    allOf(withId(R.id.BackButton_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    1),
                            isDisplayed()));
            appCompatImageButton2.perform(click());

            ViewInteraction recyclerView3 = onView(
                    allOf(withId(R.id.list_neighbours), isDisplayed(),
                            withParent(withId(R.id.container))));
            recyclerView3.perform(actionOnItemAtPosition(6, click()));

            ViewInteraction floatingActionButton3 = onView(
                    allOf(withId(R.id.addFav_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    2),
                            isDisplayed()));
            floatingActionButton3.perform(click());

            ViewInteraction appCompatImageButton3 = onView(
                    allOf(withId(R.id.BackButton_view),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    1),
                            isDisplayed()));
            appCompatImageButton3.perform(click());

            ViewInteraction tabView = onView(
                    allOf(withContentDescription("Favorites"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.tabs),
                                            0),
                                    1),
                            isDisplayed()));
            tabView.perform(click());

            ViewInteraction viewPager = onView(
                    allOf(withId(R.id.container),
                            childAtPosition(
                                    allOf(withId(R.id.main_content),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    1),
                            isDisplayed()));
            viewPager.perform(swipeLeft());
        }

        private static Matcher<View> childAtPosition (
        final Matcher<View> parentMatcher, final int position){

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