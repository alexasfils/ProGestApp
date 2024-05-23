package io.myproject.ProGest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.myproject.ProGest.model.Project;

@Repository
public interface TaskRepository extends JpaRepository<Project, Integer> {

}
