package de.gmcs.benchmark.time;

public abstract class AbstractStopWatch implements StopWatch {

    private long start;

    @Override
    public void start() {
        start = getTime();
    }

    @Override
    public String end() {
        if (start == 0) {
            throw new IllegalStateException("start() was not called");
        }
        return (getTime() - start) + " " + getUnit();
    }

    protected abstract long getTime();
    
    protected abstract String getUnit();
}
