package app.adapters.person.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Persons")
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
}
