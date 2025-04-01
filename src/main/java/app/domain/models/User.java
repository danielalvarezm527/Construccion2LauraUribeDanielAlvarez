package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class User extends Person{
	private long userId;
	private String userName;
	private String password; 


	public User(long document, String name, int age, String userName, String password,long userId, String role) {
		super(document, name, age, role);
		this.userName = userName;
		this.password = password;
		this.userId = userId;

	}
}


