package de.gmcs.benchmark;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {

	private Task<Object> subject;
	
	private Supplier<Object> supplier;
	private Consumer<Object> consumer;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		supplier = mock(Supplier.class);
		consumer = mock(Consumer.class);
		
		subject = new Task<>("task", supplier, consumer);
	}
	
	@Test
	public void testGetName() throws Exception {
		assertThat(subject.getName(), is("task"));
	}
	
	@Test
	public void testGetData() throws Exception {
		Object value = new Object();
		when(supplier.get()).thenReturn(value);
		
		assertThat(subject.getData(), is(sameInstance(value)));
	}

	@Test
	public void testExecute() throws Exception {
		Object value = new Object();
		
		subject.execute(value);
		
		verify(consumer).accept(value);
	}
}
