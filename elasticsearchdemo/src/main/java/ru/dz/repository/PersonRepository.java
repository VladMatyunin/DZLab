package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.People;

@Repository
public interface PersonRepository extends JpaRepository<People,Long>{
}
