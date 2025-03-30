package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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
	public Object getDocument() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getAge() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

