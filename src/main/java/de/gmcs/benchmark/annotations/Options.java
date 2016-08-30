package de.gmcs.benchmark.annotations;

import de.gmcs.benchmark.options.MeasuringUnit;
import de.gmcs.benchmark.options.OutputFormat;

public @interface Options {

	OutputFormat outputFormat() default OutputFormat.NONE;
	
	MeasuringUnit measuringUnit() default MeasuringUnit.MILLISECONDS;
	
	int benchmarkLoopsize() default 50_000;
	
	int warmupLoopsize() default -1;
}
