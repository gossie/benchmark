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

public class Options {

    private Map<OutputFormat, Supplier<ResultWriter>> resultWriter = new HashMap<>();
    private Writer writer;
    private OutputFormat outputFormat;

    public Options() {
        writer = new OutputStreamWriter(System.out);
        outputFormat = OutputFormat.NONE;

        resultWriter.put(OutputFormat.NONE, () -> new SimpleResultWriter(writer));
        resultWriter.put(OutputFormat.HTML, () -> new HtmlResultWriter(writer));
        resultWriter.put(OutputFormat.JSON, () -> new JsonResultWriter(writer));
    }

    public Options withWriter(Writer out) {
        this.writer = out;
        return this;
    }

    public Options withOutputFormat(OutputFormat format) {
        this.outputFormat = format;
        return this;
    }

    public ResultWriter getWriter() {
        return resultWriter.get(outputFormat).get();
    }
}
