package io.myproject.ProGest.serviceImplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.myproject.ProGest.model.ChecklistItem;
import io.myproject.ProGest.repository.ChecklistItemRepository;
import io.myproject.ProGest.service.ChecklistItemService;

public class ChecklistItemServiceImplm implements ChecklistItemService {
	
	@Autowired
	ChecklistItemRepository repo;

	@Override
	public List<ChecklistItem> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<ChecklistItem> findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findChecklistItemById(id);
	}

	@Override
	public ChecklistItem saveItem(ChecklistItem checklistItem) {
		// TODO Auto-generated method stub
		return repo.save(checklistItem);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	

}
