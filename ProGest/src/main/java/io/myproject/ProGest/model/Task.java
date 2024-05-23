package io.myproject.ProGest.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
@Table(name="task")
public class Task {
	
	@Column (name = "id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "description")
	private String description;
	
	@Column (name = "estimated_hours")
	private Integer estimatedHours;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "created_at")
	private Date created_at;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "due_date")
	private Date due_date;
	
	@Enumerated(EnumType.STRING)
	private TaskState  state;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User assignedTo;
	
	@ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @OneToMany(mappedBy = "task")
    private List<ChecklistItem> checklistItems;

    

}
