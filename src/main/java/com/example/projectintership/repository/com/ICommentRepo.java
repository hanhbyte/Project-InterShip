package com.example.projectintership.repository.com;

import com.example.projectintership.model.entity.Comment;
import com.example.projectintership.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Long> {
  Iterable<Comment> findAllByProduct(Product product);
}
