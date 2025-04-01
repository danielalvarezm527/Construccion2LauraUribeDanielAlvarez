package app.adapters.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.person.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
