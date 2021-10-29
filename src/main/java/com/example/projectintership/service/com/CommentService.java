package com.example.projectintership.service.com;

import com.example.projectintership.model.entity.Comment;
import com.example.projectintership.model.entity.Product;
import com.example.projectintership.repository.com.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  ICommentRepo iCommentRepo;
  public void createComment(Comment comment){
    iCommentRepo.save(comment);
  }

  public Iterable<Comment> findAllByProduct(Product product){
    return iCommentRepo.findAllByProduct(product);
  }
}
