package de.gmcs.benchmark.result;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractResultWriter implements ResultWriter {

    private Writer writer;

    protected AbstractResultWriter(Writer writer) {
        this.writer = writer;
    }

    protected void write(String line) {
        try {
            writer.write(line);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
