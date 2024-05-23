package io.myproject.ProGest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.myproject.ProGest.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public List<Comment> findCommentById(Integer id);

}
