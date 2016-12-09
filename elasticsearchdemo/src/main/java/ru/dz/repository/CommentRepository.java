package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Comment;

/**
 * Created by Vlad.M on 09.12.2016.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
