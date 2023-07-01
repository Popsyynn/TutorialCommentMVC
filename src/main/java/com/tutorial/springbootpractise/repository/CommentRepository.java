package com.tutorial.springbootpractise.repository;

import com.tutorial.springbootpractise.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTutorialId(Long id);

    @Transactional
    void deleteByTutorialId(Long id);
}
