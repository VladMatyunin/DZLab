package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.User;

/**
 * Created by Vlad.M on 01.12.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}
