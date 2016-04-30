package de.gmcs.benchmark.it.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.json.JSONObject;

public class BenchmarkMatchers {

    public static Matcher<String> validJson() {
        return new TypeSafeMatcher<String>() {

            @Override
            public void describeTo(Description arg0) {

            }

            @Override
            protected boolean matchesSafely(String json) {
                try {
                    new JSONObject(json);
                    return true;
                } catch (JSONException e) {
                    return false;
                }
            }
        };
    }

    public static Matcher<String> validHtml() {
        return new TypeSafeMatcher<String>() {

            @Override
            public void describeTo(Description arg0) {

            }

            @Override
            protected boolean matchesSafely(String html) {
                return true;
            }
        };
    }

    public static Matcher<String> validOutput() {
        return new TypeSafeMatcher<String>() {

            @Override
            public void describeTo(Description arg0) {

            }

            @Override
            protected boolean matchesSafely(String html) {
                return true;
            }
        };
    }
}
