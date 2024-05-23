package io.myproject.ProGest.serviceImplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.myproject.ProGest.model.Comment;
import io.myproject.ProGest.repository.CommentRepository;
import io.myproject.ProGest.service.CommentService;

public class CommentServiceImplm implements CommentService{
	
	@Autowired
	CommentRepository repo;

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Comment> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
