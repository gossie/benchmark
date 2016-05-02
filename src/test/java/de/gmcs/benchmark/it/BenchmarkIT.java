package de.gmcs.benchmark.it;

import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.gmcs.benchmark.Benchmark;
import de.gmcs.benchmark.Task;
import de.gmcs.benchmark.TaskGroup;
import de.gmcs.benchmark.it.matcher.BenchmarkMatchers;
import de.gmcs.benchmark.options.MeasuringUnit;
import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.options.OutputFormat;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BenchmarkIT {

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> parameters = new ArrayList<>();
        for (OutputFormat outputFormat : OutputFormat.values()) {
            for (MeasuringUnit measuringUnit : MeasuringUnit.values()) {
                parameters.add(new Object[] { outputFormat, measuringUnit });
            }
        }

        return parameters;
    }

    private static int blackHole;

    private Benchmark<Integer> subject;
    private StringWriter writer;
    private Matcher<String> valid;

    public BenchmarkIT(OutputFormat outputFormat, MeasuringUnit measuringUnit) {
        writer = new StringWriter();

        valid = getMatcher(outputFormat);

        subject = new Benchmark<>(new Options().withOutputFormat(outputFormat).withMeasuringUnit(measuringUnit).withWriter(writer));
    }

    @Test
    public void testPerform() throws Exception {
        MultiplyMultiplier multiplyMultiplier = new MultiplyMultiplier();
        ShiftMultiplier shiftMultiplier = new ShiftMultiplier();

        Task<Integer> task1 = createTask("multiply 1", 1, multiplyMultiplier);
        Task<Integer> task2 = createTask("shift 1", 1, shiftMultiplier);
        TaskGroup<Integer> taskGroup1 = createTaskGroup("group1", asList(task1, task2));

        Task<Integer> task3 = createTask("multiply 1000000", 1_000_000, multiplyMultiplier);
        Task<Integer> task4 = createTask("shift 1000000", 1_000_000, shiftMultiplier);
        TaskGroup<Integer> taskGroup2 = createTaskGroup("group2", asList(task3, task4));

        Task<Integer> task5 = createTask("multiply 1000000000", 1_000_000_000, multiplyMultiplier);
        Task<Integer> task6 = createTask("shift 1000000000", 1_000_000_000, shiftMultiplier);
        TaskGroup<Integer> taskGroup3 = createTaskGroup("group3", asList(task5, task6));

        subject.perform(asList(taskGroup1, taskGroup2, taskGroup3));

        assertThat(writer.getBuffer().toString(), is(valid));
        assertThat(blackHole, is(greaterThanOrEqualTo(0)));
    }

    private Matcher<String> getMatcher(OutputFormat outputFormat) {
        switch (outputFormat) {
            case HTML:
                return BenchmarkMatchers.validHtml();
            case JSON:
                return BenchmarkMatchers.validJson();
            default:
                return BenchmarkMatchers.validOutput();
        }
    }

    private TaskGroup<Integer> createTaskGroup(String name, List<Task<Integer>> tasks) {
        return new TaskGroup<Integer>(name, tasks);
    }

    private Task<Integer> createTask(String name, Integer number, ShiftMultiplier shiftMultiplier) {
        return new Task<Integer>(name, () -> number, i -> {
            blackHole = shiftMultiplier.timesTwo(i);
        });
    }

    private Task<Integer> createTask(String name, Integer number, MultiplyMultiplier multiplyMultiplier) {
        return new Task<Integer>(name, () -> number, i -> {
            blackHole = multiplyMultiplier.timesTwo(i);
        });
    }
}
