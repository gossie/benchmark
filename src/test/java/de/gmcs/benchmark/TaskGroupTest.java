package de.gmcs.benchmark;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class TaskGroupTest {
	
	private TaskGroup<Object> subject;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		subject = new TaskGroup<Object>("taskGroup", asList(mock(Task.class), mock(Task.class)));
	}

	@Test
	public void testGetName() throws Exception {
		assertThat(subject.getName(), is("taskGroup"));
	}

	@Test
	public void testGetTasks() throws Exception {
		assertThat(subject.getTasks(), hasSize(2));
	}	
}
