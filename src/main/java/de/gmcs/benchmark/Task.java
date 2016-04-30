package de.gmcs.benchmark;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task<T> {

	private String name;
	private Supplier<T> dataSupplier;
	private Consumer<T> test;
	
	public Task(String name, Supplier<T> dataSupplier, Consumer<T> test) {
		this.name = name;
		this.dataSupplier = dataSupplier;
		this.test = test;
	}

	public String getName() {
		return name;
	}
	
	public T getData() {
		return dataSupplier.get();
	}
	
	public void execute(T data) {
		test.accept(data);
	}
}
