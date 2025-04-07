package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
	private long document;
	private String name;
	private int age;
	private String role;
	public Person(long document, String name, int age, String role) {
		super();
		this.document = document;
		this.name = name;
		this.age = age;
		this.role= role;
	
	}

}

