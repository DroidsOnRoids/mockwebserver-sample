package frogermcs.io.githubclient.tests;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static frogermcs.io.githubclient.tests.mock.server.TestRequestMapper.setTestRequestMapper;

/**
 * * Created by MS on 01/06/2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MockWebServerTest {

    private static final boolean INITIAL_TOUCH_MODE = true;
    private static final boolean LAUNCH_ACTIVITY = false;

    private final MockWebServer server;

    public MockWebServerTest() {
        server = new MockWebServer();
    }

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class,
            INITIAL_TOUCH_MODE,
            LAUNCH_ACTIVITY);

    @Before
    public void setUpMockWebServer() throws Exception {
        setTestRequestMapper(server);
        server.start(12345);
        activityRule.launchActivity(null);

    }

    @After
    public void shutdownMockWebServer() throws Exception{
        server.shutdown();
    }

    @Test
    public void mockWebServerExampleTest() throws Exception {
        onView(withId(R.id.etUsername)).perform(typeText("afilipowicz"));
        onView(withId(R.id.btnShowRepositories)).perform(click());
        Thread.sleep(10000);
        onView(withId(R.id.rvRepositories))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(3, click()));
        onView(withText("Makor")).check(matches(isDisplayed()));
        Thread.sleep(10000);
    }
}
