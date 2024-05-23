package io.myproject.ProGest.model;

import java.util.Date;

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
@Table(name="comment")
public class Comment {
	
	@Column (name = "id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "content")
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	

}
