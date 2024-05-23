package io.myproject.ProGest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name="checklistItem")
public class ChecklistItem {
	
	@Column (name = "id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "isdone")
	private Boolean isDone;
	
	@ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
		
	

}
