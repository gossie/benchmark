package de.gmcs.benchmark.time;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MilliSecondStopWatchTest {

    private MilliSecondStopWatch subject;

    @Before
    public void setUp() {
        subject = new MilliSecondStopWatch();
    }

    @Test
    public void testEnd() throws Exception {
        subject.start();
        assertThat(subject.end(), endsWith(" ms"));
    }

    @Test(expected = IllegalStateException.class)
    public void testEnd_illegalState() throws Exception {
        subject.end();
    }
}
