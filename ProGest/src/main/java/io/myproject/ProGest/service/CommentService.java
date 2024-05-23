package io.myproject.ProGest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.myproject.ProGest.model.Comment;

@Service
public interface CommentService {
	
	public List<Comment> findAll();
	
	public List<Comment> findById(Integer id);
	
	public Comment saveComment(Comment comment);
	
	public void deleteById(Integer id);
	
	

}
