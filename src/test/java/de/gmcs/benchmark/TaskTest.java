package de.gmcs.benchmark;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {

    private Task<Object> subject;

    private Function<Integer, Object> warmupSupplier;
    private Function<Integer, Object> benchmarkSupplier;
    private Consumer<Object> consumer;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        warmupSupplier = mock(Function.class);
        benchmarkSupplier = mock(Function.class);
        consumer = mock(Consumer.class);

        subject = new Task<>("task", warmupSupplier, benchmarkSupplier, consumer);
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(subject.getName(), is("task"));
    }

    @Test
    public void testGetWarmupData() throws Exception {
        Object value = new Object();
        when(warmupSupplier.apply(0)).thenReturn(value);

        assertThat(subject.getWarmupData(0), is(sameInstance(value)));
    }

    @Test
    public void testGetBenchmarkData() throws Exception {
        Object value = new Object();
        when(benchmarkSupplier.apply(0)).thenReturn(value);

        assertThat(subject.getBenchmarkData(0), is(sameInstance(value)));
    }

    @Test
    public void testExecute() throws Exception {
        Object value = new Object();

        subject.execute(value);

        verify(consumer).accept(value);
    }
}
