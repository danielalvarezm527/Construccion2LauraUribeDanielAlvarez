package app.adapters.pets.repository;

import app.adapters.pets.entity.PetEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long>{

}
