package de.gmcs.benchmark.it;

import static java.lang.Math.random;
import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import de.gmcs.benchmark.Benchmark;
import de.gmcs.benchmark.Task;
import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.options.OutputFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BenchmarkIT {

    @Parameters
    public static Collection<OutputFormat> data() {
        return asList(OutputFormat.values());
    }

    private static int numberOfMultiplyCalls;
    private static int numberOfShiftCalls;

    private Benchmark<Integer> subject;

    public BenchmarkIT(OutputFormat outputFormat) {
        numberOfMultiplyCalls = 0;
        numberOfShiftCalls = 0;
        subject = new Benchmark<>(new Options().withOutputFormat(outputFormat));
    }

    @Test
    public void testPerform() throws Exception {
        Task<Integer> task1 = new Task<Integer>("testTask_1", () -> getRandom(), i -> multiply(i));
        Task<Integer> task2 = new Task<Integer>("testTask_2", () -> getRandom(), i -> shift(i));
        List<Task<Integer>> tasks = Arrays.asList(task1, task2);
        subject.perform(tasks);
        assertThat(numberOfMultiplyCalls, is(75_000));
        assertThat(numberOfShiftCalls, is(75_000));
    }

    private static Integer getRandom() {
        return Integer.valueOf((int) (random() * 100.0));
    }

    private static void multiply(Integer i) {
        int result = i.intValue() * 2;
        assertThat(result, greaterThanOrEqualTo(0));
        numberOfMultiplyCalls++;
    }

    private static void shift(Integer i) {
        int result = i.intValue() << 1;
        assertThat(result, greaterThanOrEqualTo(0));
        numberOfShiftCalls++;
    }
}
