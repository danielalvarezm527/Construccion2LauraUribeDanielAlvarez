package app.adapters.user.entity;

import app.adapters.person.entity.PersonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.domain.models.Person;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @JoinColumn(name = "person_id")
    @OneToOne
    private PersonEntity person;
    @Column(name = "user_name")
    private String userName;
    @Column(name ="password")
    private String password;

    public long getDocument() {
        return person.getDocument();
    }

    public String getName() {
        return person.getName();
    }

    public int getAge() {
        return person.getAge();
    }

    public String getRole() {
        return person.getRole();
    }
}


