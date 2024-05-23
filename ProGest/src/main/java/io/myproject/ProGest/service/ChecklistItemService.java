package io.myproject.ProGest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.myproject.ProGest.model.ChecklistItem;

@Service
public interface ChecklistItemService {
	
	public List<ChecklistItem> findAll();
	
	List<ChecklistItem> findById(Integer id);
	
	public ChecklistItem saveItem(ChecklistItem checklistItem);
	
	public void deleteById(Integer id);
	

}
