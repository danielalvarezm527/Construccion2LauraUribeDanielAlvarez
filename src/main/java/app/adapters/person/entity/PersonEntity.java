package app.adapters.person.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {
	@Id
    private long document;
    private String name;
    private int age;
    private String role;
    
    public PersonEntity(long document, String name, int age, String role) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.role = role;
    }

	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getDocument() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDocument(Object document2) {
		// TODO Auto-generated method stub
		
	}

	public void setName(Object name2) {
		// TODO Auto-generated method stub
		
	}

	public void setAge(Object age2) {
		// TODO Auto-generated method stub
		
	}

	public void setRole(Object role2) {
		// TODO Auto-generated method stub
		
	}

	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}
}
