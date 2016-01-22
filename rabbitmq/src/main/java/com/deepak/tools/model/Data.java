package com.deepak.tools.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final int age;
	private final List<String> someList;

	public Data(String name, int age, List<String> someList) {
		super();
		this.name = name;
		this.age = age;
		this.someList = someList;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", someList=");
		builder.append(someList);
		builder.append("]");
		return builder.toString();
	}



	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public List<String> getSomeList() {
		return someList;
	}

}
