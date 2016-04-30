package de.gmcs.benchmark.options;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

import de.gmcs.benchmark.result.HtmlResultWriter;
import de.gmcs.benchmark.result.JsonResultWriter;
import de.gmcs.benchmark.result.SimpleResultWriter;
import de.gmcs.benchmark.time.MilliSecondStopWatch;
import de.gmcs.benchmark.time.NanoSecondStopWatch;

public class OptionsTest {

    private Options subject;

    @Before
    public void setUp() {
        subject = new Options();
        assertThat(subject.getWriter(), is(instanceOf(SimpleResultWriter.class)));
    }

    @Test
    public void testWithWriterOutputFormat_none() throws Exception {
        assertThat(subject.withOutputFormat(OutputFormat.NONE).getWriter(), is(instanceOf(SimpleResultWriter.class)));
    }

    @Test
    public void testWithWriterOutputFormat_html() throws Exception {
        assertThat(subject.withOutputFormat(OutputFormat.HTML).getWriter(), is(instanceOf(HtmlResultWriter.class)));
    }

    @Test
    public void testWithWriterOutputFormat_json() throws Exception {
        assertThat(subject.withOutputFormat(OutputFormat.JSON).getWriter(), is(instanceOf(JsonResultWriter.class)));
    }

    @Test
    public void testWithWriter() throws Exception {
        Writer writer = mock(Writer.class);
        subject.withWriter(writer).getWriter().printBenchmarkStart();

        verify(writer).write(anyString());
    }

    @Test
    public void testWithMeasuringUnit_milliseconds() throws Exception {
        assertThat(subject.withMeasuringUnit(MeasuringUnit.MILLISECONDS).getStopWatch(), is(instanceOf(MilliSecondStopWatch.class)));
    }

    @Test
    public void testWithMeasuringUnit_nanoseconds() throws Exception {
        assertThat(subject.withMeasuringUnit(MeasuringUnit.NANOSECONDS).getStopWatch(), is(instanceOf(NanoSecondStopWatch.class)));
    }
}
