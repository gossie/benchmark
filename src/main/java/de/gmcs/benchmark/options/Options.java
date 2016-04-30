package de.gmcs.benchmark.options;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import de.gmcs.benchmark.result.HtmlResultWriter;
import de.gmcs.benchmark.result.JsonResultWriter;
import de.gmcs.benchmark.result.ResultWriter;
import de.gmcs.benchmark.result.SimpleResultWriter;
import de.gmcs.benchmark.time.MilliSecondStopWatch;
import de.gmcs.benchmark.time.NanoSecondStopWatch;
import de.gmcs.benchmark.time.StopWatch;

public class Options {

    private Map<OutputFormat, Supplier<ResultWriter>> resultWriter = new HashMap<>();
    private Map<MeasuringUnit, Supplier<StopWatch>> stopWatches = new HashMap<>();

    private Writer writer;
    private OutputFormat outputFormat;
    private MeasuringUnit measuringUnit;

    public Options() {
        writer = new OutputStreamWriter(System.out);
        outputFormat = OutputFormat.NONE;
        measuringUnit = MeasuringUnit.MILLISECONDS;

        resultWriter.put(OutputFormat.NONE, () -> new SimpleResultWriter(writer));
        resultWriter.put(OutputFormat.HTML, () -> new HtmlResultWriter(writer));
        resultWriter.put(OutputFormat.JSON, () -> new JsonResultWriter(writer));

        stopWatches.put(MeasuringUnit.MILLISECONDS, () -> new MilliSecondStopWatch());
        stopWatches.put(MeasuringUnit.NANOSECONDS, () -> new NanoSecondStopWatch());
    }

    public Options withWriter(Writer out) {
        this.writer = out;
        return this;
    }

    public Options withOutputFormat(OutputFormat format) {
        this.outputFormat = format;
        return this;
    }

    public Options withMeasuringUnit(MeasuringUnit unit) {
        this.measuringUnit = unit;
        return this;
    }

    public ResultWriter getWriter() {
        return resultWriter.get(outputFormat).get();
    }

    public StopWatch getStopWatch() {
        return stopWatches.get(measuringUnit).get();
    }
}
