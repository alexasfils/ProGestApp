package io.myproject.ProGest.model;

import java.util.Date;
import java.util.List;

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
@Table(name="project")
public class Project {
	
	@Column (name = "id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "description")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User creator;
	
//	@ManyToMany(mappedBy = "user-id")
//	private List<User> members;
	
	@ManyToMany
    @JoinTable(
        name = "members",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks;
	
	public Project(String title, String description, Date createdAt, User creator) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.creator = creator;
    }
	

	

}
