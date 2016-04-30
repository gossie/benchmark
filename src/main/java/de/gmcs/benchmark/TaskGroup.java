package de.gmcs.benchmark;

import java.util.List;

public class TaskGroup<T> {

	private String name;
	private List<Task<T>> tasks;
	
	public TaskGroup(String name, List<Task<T>> tasks) {
		this.name = name;
		this.tasks = tasks;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Task<T>> getTasks() {
		return tasks;
	}
}
