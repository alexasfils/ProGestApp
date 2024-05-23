package io.myproject.ProGest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.myproject.ProGest.model.ChecklistItem;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Integer>{
	public List<ChecklistItem> findChecklistItemById(Integer id);
}
