package de.gmcs.benchmark.time;

public class NanoSecondStopWatch extends AbstractStopWatch {

    @Override
    protected long getTime() {
        return System.nanoTime();
    }
    
    @Override
    protected String getUnit() {
    	return "ns";
    }
}
