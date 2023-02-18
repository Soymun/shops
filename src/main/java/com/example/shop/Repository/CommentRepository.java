package com.example.shop.Repository;

import com.example.shop.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByUserId(Long id);

    List<Comment> getCommentsByProductId(Long id);
}
