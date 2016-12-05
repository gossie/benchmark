package de.gmcs.benchmark.it;

import de.gmcs.benchmark.annotations.Benchmark;
import de.gmcs.benchmark.annotations.Options;
import de.gmcs.benchmark.options.OutputFormat;

public class AnnotationBenchmarkIT {
	
	@Benchmark
	@Options(outputFormat=OutputFormat.HTML, warmupLoopsize=50_000, benchmarkLoopsize=100_000)
	public void benchmarkMultipliers() {
		
	}
}
