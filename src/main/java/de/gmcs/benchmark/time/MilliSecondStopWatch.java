package de.gmcs.benchmark.time;

public class MilliSecondStopWatch extends AbstractStopWatch {

    @Override
    protected long getTime() {
        return System.currentTimeMillis();
    }
}
