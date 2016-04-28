package de.gmcs.benchmark.options;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class OptionsBuilderTest {

	private OptionsBuilder subject;
	
	@Before
	public void setUp() {
		subject = OptionsBuilder.newInstance();
	}

	@Test
	public void testWithPrintStream() throws Exception {
		PrintStream printStream = mock(PrintStream.class);
		Options options = subject.withPrintStream(printStream).build();
		
		assertThat(options.getPrintStream(), is(sameInstance(printStream)));
	}
}
